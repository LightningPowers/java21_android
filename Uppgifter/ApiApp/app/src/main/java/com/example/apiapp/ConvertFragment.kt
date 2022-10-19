package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.timerTask

class ConvertFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_convert, container, false)

        val cur1Text: EditText = view.findViewById(R.id.convertBaseCurrencyTextfield)
        val cur2Text: EditText = view.findViewById(R.id.convertTargetCurrencyTextfield)
        val amountText: EditText = view.findViewById(R.id.convertAmountTextfield)
        val btnConvert: Button = view.findViewById(R.id.convertButton)
        val fm: FragmentManager = parentFragmentManager

        //To convert result fragment
        btnConvert.setOnClickListener{
            //Only progresses if there are inputs in all fields
            if (cur1Text.text.toString() != "Base Currency" || cur2Text.text.toString() != "Target Currency"
                || amountText.text.toString() != "Amount"){

                Log.i("convertSubmitTest", cur1Text.text.toString())

                //Assigning textinput to global variables
                MainActivity.baseCurrency = cur1Text.text.toString().uppercase()
                MainActivity.targetCurrency = cur2Text.text.toString().uppercase()
                MainActivity.amount = Integer.parseInt(amountText.text.toString())

                callConvertApi()

                //Delays transition to next fragment so api can be called in time
                val timer = Timer()
                timer.schedule(timerTask { fm.beginTransaction().replace(R.id.fragment_container_view, ConvertResultFragment())
                    .addToBackStack("2").commit() }, 2500)
            }
        }

        return view
    }

    private fun callConvertApi(){
        var isCorrect: Boolean = false

        if (MainActivity.baseCurrency.isBlank() || MainActivity.targetCurrency.isBlank() || MainActivity.amount == 0){
            isCorrect = false
            Log.i("testConvertApi", isCorrect.toString())
        }
        else {
            isCorrect = true
            Log.i("testConvertApi", isCorrect.toString())

            var currency1: String = MainActivity.baseCurrency
            var currency2: String = MainActivity.targetCurrency
            var amount: Int = MainActivity.amount

            val apikey = "64e1bf3a3d7ee7d878c70bec407ec1a1"
            val url = "https://api.currencyscoop.com/v1/convert?api_key=${apikey}" +
                    "&from=${currency1}&to=${currency2}&amount=${amount}"

            val queue = Volley.newRequestQueue(activity)

            val JsonRequest = JsonObjectRequest(
                Request.Method.GET, url, null, { response: JSONObject? ->
                // Display the first 500 characters of the response string.
                Log.w("initialResponse", response.toString())

                try {
                    val answer: String = response!!.get("response").toString().replace("\"","")
                    Log.w("responseTest", answer)

                    val stringArray: Array<String> = answer.split(",").toTypedArray()

                    //Getting values from array
                    val resultCur1 = stringArray[2].substring(stringArray[2].indexOf(":")+1).uppercase()
                    val resultCur2 = stringArray[3].substring(stringArray[3].indexOf(":")+1).uppercase()
                    val resultAmount = stringArray[4].substring(stringArray[4].indexOf(":")+1)
                    val resultValue = stringArray[5].substring(stringArray[5].indexOf(":")+1).replace("}", "")

                    Log.i("responseTest", "From: $resultCur1 To: $resultCur2 Amount: $resultAmount Value: $resultValue")

                    //Assigning values to result variables
                    MainActivity.firstCurrency = resultCur1
                    MainActivity.secondCurrency = resultCur2
                    MainActivity.outputAmount = Integer.parseInt(resultAmount)

                    val df = DecimalFormat("#.###")
                    MainActivity.outputValue = df.format(resultValue.toFloat()).toFloat()

                } catch (e: Exception) {
                    Log.e("responseException", e.message.toString())
                }
            },
                { error: VolleyError ->
                    Log.w("volleyError", "error")
                })

            queue.add(JsonRequest)
        }
    }
}