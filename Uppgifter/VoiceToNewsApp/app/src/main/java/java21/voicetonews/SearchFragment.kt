package java21.voicetonews

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class SearchFragment : Fragment() {

    // Creating a constant value
    private val resultInputCode = 1

    lateinit var recBtn: Button
    lateinit var recOutputText: TextView
    lateinit var errorText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_search, container, false)

        recBtn = view.findViewById(R.id.searchRecordButton)
        recOutputText = view.findViewById(R.id.searchOutputText)
        errorText= view.findViewById(R.id.searchStatusText)

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
        return view
    }

    // Retrieving data from recording
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
            }
        }
    }
}