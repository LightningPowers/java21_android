package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import org.json.simple.parser.JSONParser


class MainActivity : AppCompatActivity() {

    companion object {
        //Used in weather
        var cityName: String = ""
        var countryCode: String = ""

        //Used in convert
        var baseCurrency: String = "eur"
        var targetCurrency: String = "sek"
        var amount: Int = 10
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    public fun callConvertApi(): Boolean{
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
            val url = "\"https://api.currencyscoop.com/v1/convert?api_key=${apikey}" +
                    "&from=${currency1}&to=${currency2}&amount=${amount}"

            val queue = Volley.newRequestQueue(this)

            val JsonRequest = JsonObjectRequest(Request.Method.GET, url, null, { response: JSONObject? ->
                // Display the first 500 characters of the response string.
                Log.w("weather", response.toString())

                try {
                    // Simple-json lib
                    val parser = JSONParser()
                    val response: Any = response?.get("response") ?:
                    Log.w("responseTest", response.toString())

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