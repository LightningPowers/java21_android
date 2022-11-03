package java21.voicetonews

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.timerTask


class SearchFragment : Fragment() {

    // For saving data between sessions
    private val sharedPrefFile: String = "MySharedPref"

    // Creating a constant value
    private val resultInputCode = 1

    lateinit var recBtn: Button
    lateinit var recOutputText: TextView
    lateinit var errorText: TextView
    lateinit var searchBtn: Button

    @RequiresApi(Build.VERSION_CODES.O)
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

        // For backstack
        val fm: FragmentManager = parentFragmentManager

        // To save data between sessions
        val sharePref: SharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        // Sets output text to saved entry (if it exists), otherwise uses default
        recOutputText.text = sharePref.getString("recOutput", R.string.recordOutput_placeholder.toString())

        // Enables search to work with restored stored data
        if (recOutputText.text.toString() != R.string.recordOutput_placeholder.toString()){
            MainActivity.searchTerm =
                sharePref.getString("recOutput", R.string.recordOutput_placeholder.toString()).toString()
        }

        // Reset error text
        errorText.text = ""

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
                Log.i("submitTest", "Search term: ${MainActivity.searchTerm}")

                callApi()

                val timer = Timer()
                timer.schedule(timerTask { fm.beginTransaction().replace(R.id.fragmentContainerView, ResultFragment())
                    .addToBackStack("1").commit() }, 2500)
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

        val sharePref: SharedPreferences = requireContext().getSharedPreferences(sharedPrefFile, MODE_PRIVATE)

        // Checking request code with the resultInputCode
        if (requestCode == resultInputCode) {
            // Checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // Extracting the data from result array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // Displaying output as text in text output box
                recOutputText.text = Objects.requireNonNull(res)[0]

                // Saving input data
                sharePref.edit().putString("recOutput", recOutputText.text.toString()).apply()

                // Storing output as search term
                MainActivity.searchTerm = Objects.requireNonNull(res)[0]
            }
        }
    }

    // Calling news Api
    @RequiresApi(Build.VERSION_CODES.O)
    fun callApi () {
        val apiKey: String = "pub_1301641852cdfd0619f7c1efe2bffcd2f0554"
        val url = "https://newsdata.io/api/1/news?apikey=${apiKey}&q=${MainActivity.searchTerm}"
        Log.d("test", url)

        val queue = Volley.newRequestQueue(activity)

        val JsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response: JSONObject? ->
                // Display the first 500 characters of the response string.
                Log.d("initialResponse", response.toString())

                try {
                    Log.i("test", "hejhopp")
                    if (response != null) {
                        MainActivity.beanList = ApiHelper.jsonToArticles(response)
                    }

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