package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.simple.parser.JSONParser

class ConvertFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_convert, container, false)

        //var result =
        callConvertApi()
        //Log.i("apiCallTest", result.toString())

        return view
    }

    private fun callConvertApi(): Boolean{
        var returnValue: Boolean = false

        if (MainActivity.baseCurrency.isBlank() || MainActivity.targetCurrency.isBlank() || MainActivity.amount == 0){
            returnValue = false
            Log.i("testConvertApi", returnValue.toString())
        }
        else {
            returnValue = true
            Log.i("testConvertApi", returnValue.toString())

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
                Log.w("weather", response.toString())

                try {
                    // Simple-json lib
                    val parser = JSONParser()
                    //val answer: Any
                    val answer: Any = response!!.get("response")
                    Log.w("responseTest", answer.toString())

                } catch (e: Exception) {
                    Log.e("weather", e.message.toString())
                }

            },
                { error: VolleyError ->
                    Log.w("weather", "error")
                })


            queue.add(JsonRequest)
        }

        return returnValue
    }
}