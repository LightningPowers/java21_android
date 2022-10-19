package com.example.apiapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.util.*
import kotlin.concurrent.timerTask
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_weather, container, false)

        val cityText: TextView = view.findViewById(R.id.weatherCityTextfield)
        val countryText: TextView = view.findViewById(R.id.weatherCountryTextField)
        val searchBtn: Button = view.findViewById(R.id.weatherButton)
        val fm: FragmentManager = parentFragmentManager

        searchBtn.setOnClickListener{
            //Only progresses if there are inputs in all fields
            if (cityText.text.toString() != "City Name" || countryText.text.toString() != "Country Code"){

                //Assigning textinput to global variables
                MainActivity.cityName = cityText.text.toString()
                MainActivity.countryCode = countryText.text.toString()

                callWeatherApi()

                //Delays transition to next fragment so api can be called in time
                val timer = Timer()
                timer.schedule(timerTask { fm.beginTransaction().replace(R.id.fragment_container_view, WeatherResultFragment())
                    .addToBackStack("2").commit() }, 2500)
            }
        }
        return view
    }

    private fun callWeatherApi(){
        val inputCity: String = MainActivity.cityName
        val inputCountry: String = MainActivity.countryCode

        val apikey = "e6176132ad6e88a57a819231231195e2"
        val url = "https://api.openweathermap.org/data/2.5/weather?q="+"${inputCity},${inputCountry}" +
                "&appid=${apikey}"

        val queue = Volley.newRequestQueue(activity)

        val JsonRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response: JSONObject? ->
                // Display the first 500 characters of the response string.
                Log.w("initialResponse", response.toString())

                try {
                    //Getting location
                    val cityName: String = response!!.get("name").toString().replace("\"","")
                    val country: String = response.get("sys").toString().replace("\"","")
                    val countryArray: Array<String> = country.split(",").toTypedArray()
                    val outputCountryCode: String = countryArray[2].substring(countryArray[2].indexOf(":")+1)
                    MainActivity.location = "$cityName, $outputCountryCode"

                    //Getting description
                    val weather: String = response.get("weather").toString().replace("\"","")
                    val weatherArray: Array<String> = weather.split(",").toTypedArray()
                    val description: String = weatherArray[1].substring(weatherArray[1].indexOf(":")+1)
                    MainActivity.description = description

                    //Getting temperature
                    val temp: String = response.get("main").toString().replace("\"","")
                    val tempArray: Array<String> = temp.split(",").toTypedArray()
                    val outputTemperature: String = tempArray[0].substring(tempArray[0].indexOf(":")+1)

                    //Todo: Convert kelvin to celsius
                    val kelvinTemp: Float = outputTemperature.toFloat()
                    val temperature: Int = (kelvinTemp - 273.15f).roundToInt()
                    MainActivity.temperature = "$temperature C°"

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