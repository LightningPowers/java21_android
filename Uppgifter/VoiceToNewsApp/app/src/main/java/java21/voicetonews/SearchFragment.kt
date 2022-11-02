package java21.voicetonews

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*

class SearchFragment : Fragment() {

    // Creating a constant value
    private val resultInputCode = 1

    lateinit var recBtn: Button
    lateinit var recOutputText: TextView
    lateinit var errorText: TextView
    lateinit var searchBtn: Button

    // Temp variable for storing search term
    //private var recordedSearchTerm: String = ""

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        recBtn = view.findViewById(R.id.searchRecordButton)
        recOutputText = view.findViewById(R.id.searchOutputText)
        errorText = view.findViewById(R.id.searchStatusText)
        searchBtn = view.findViewById(R.id.searchSubmitButton)

        //Todo: Test api call
        callApi("Volvo", "publishedAt")

        // For backstack
        val fm: FragmentManager = parentFragmentManager

        errorText.text = ""
        var selectedSortMethod: String = ""

        // Access dropdown items & spinner (dropdown)
        val sortOptions: Array<String> = view.resources.getStringArray(R.array.SortingOptions)
        val sortingDropdown: Spinner = view.findViewById(R.id.sortByDropdown)

        sortingDropdown.adapter = activity?.let { ArrayAdapter.createFromResource(it, R.array.SortingOptions, android.R.layout.simple_spinner_item) } as SpinnerAdapter

        sortingDropdown.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Log.i("testDropdown", "Nothing selected!")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.i("testDropdown", sortOptions[position])

                //Set sortByMethod to local variable based on input
                when (sortOptions[position]) {
                    "Relevancy" -> {
                        selectedSortMethod = "relevancy"
                    }
                    "Popularity" -> {
                        selectedSortMethod = "popularity"
                    }
                    "Recency" -> {
                        selectedSortMethod = "publishedAt"
                    }
                }
            }
        }

        // Record button
        recBtn.setOnClickListener {
            // Declaring speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // Setting language model and free form model as intent
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

            // Setting system default language as target language
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())

            // Specifying message that shows in window
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // Calling start activity for result method, passing the resultInputCode
            try {
                startActivityForResult(intent, resultInputCode)
            } catch (e: Exception) {
                Toast.makeText(context, e.message.toString(), Toast.LENGTH_SHORT).show()
                errorText.text = e.message.toString()
            }
        }

        // Search Button
        searchBtn.setOnClickListener{
            if (MainActivity.searchTerm.isNotBlank()){
                errorText.text = ""
                MainActivity.sortByMethod = selectedSortMethod
                Log.i("submitTest", "Search term: ${MainActivity.searchTerm}, Sort method: ${MainActivity.sortByMethod}")

                // Makes api call and get the returned arraylist of beans
                MainActivity.beanList = ApiHelper.jsonToBeans()

                // Backstack
                fm.beginTransaction().replace(R.id.fragmentContainerView, ResultFragment()).addToBackStack("1").commit()
            }
            else {
                errorText.text = "Record something before submitting!"
            }
        }

        return view
    }

    // Retrieving data from recording
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Checking request code with the resultInputCode
        if (requestCode == resultInputCode) {
            // Checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // Extracting the data from result array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // Displaying output as text in text output box
                recOutputText.text = Objects.requireNonNull(res)[0]

                // Storing output as search term
                MainActivity.searchTerm = Objects.requireNonNull(res)[0]
            }
        }
    }

    // Calling news Api
    fun callApi (searchTerm: String, sortingMethod: String) {

        val apiKey: String = "a387816e461540d59289caccadd582d5"
        val url = "https://newsapi.org/v2/everything?q=$searchTerm&sortBy=$sortingMethod&pageSize=20&apiKey=$apiKey"
        val url1 = "https://newsapi.org/v2/everything?q=Volvo&sortBy=publishedAt&pageSize=20&apiKey=a387816e461540d59289caccadd582d5"
        val url2 = "https://api.openweathermap.org/data/2.5/weather?q=lund,SE&appid=e6176132ad6e88a57a819231231195e2"
        val url3 = "https://newsdata.io/api/1/news?apikey=pub_1301641852cdfd0619f7c1efe2bffcd2f0554&q=volvo"

        val queue = Volley.newRequestQueue(activity)

        val JsonRequest = JsonObjectRequest(
            Request.Method.GET, url3, null, { response: JSONObject? ->
                // Display the first 500 characters of the response string.
                Log.d("initialResponse", response.toString())

                try {
                    Log.i("test", "hejhopp")
                    //if (response != null) {
                        //ApiHelper.jsonToArticles(response)
                   //}

                    // https://github.com/SirSalsa/java21_android/blob/main/Uppgifter/ApiApp/app/src/main/java/com/example/apiapp/WeatherFragment.kt
                    //Getting location
                    /*
                    val cityName: String = response!!.get("name").toString().replace("\"","")
                    val country: String = response.get("sys").toString().replace("\"","")
                    val countryArray: Array<String> = country.split(",").toTypedArray()
                    val outputCountryCode: String = countryArray[2].substring(countryArray[2].indexOf(":")+1)

                    //Getting description
                    val weather: String = response.get("weather").toString().replace("\"","")
                    val weatherArray: Array<String> = weather.split(",").toTypedArray()
                    val description: String = weatherArray[1].substring(weatherArray[1].indexOf(":")+1)*/

                    //Todo: handle json response and add to arraylist of beans


                } catch (e: Exception) {
                    Log.e("responseException", e.message.toString())
                }
            },
            { error: VolleyError ->
                Log.w("volleyError", "VolleyError: " + error.message.toString())
               // Log.w("volleyStacktrace", "volleyError Stacktrace " + error.stackTraceToString())
            })

        queue.add(JsonRequest)

    }
}