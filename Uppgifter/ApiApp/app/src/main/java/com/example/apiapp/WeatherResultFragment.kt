package com.example.apiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WeatherResultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_weather_result, container, false)

        val locationText: TextView = view.findViewById(R.id.weatherResultLocationText)
        val descText: TextView = view.findViewById(R.id.weatherResultDescText)
        val tempText: TextView = view.findViewById(R.id.weatherResultTempText)

        locationText.text = MainActivity.location
        descText.text = MainActivity.description
        tempText.text = MainActivity.temperature

        return view
    }
}