package com.example.apiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        val btnWeather: Button = view.findViewById(R.id.mainButtonWeather)
        val btnConvert: Button = view.findViewById(R.id.mainButtonConvert)
        val fm: FragmentManager = parentFragmentManager

        //To weather fragment
        btnWeather.setOnClickListener{
            fm.beginTransaction().replace(R.id.fragment_container_view, WeatherFragment()).addToBackStack("1").commit()
        }

        //To convert fragment
        btnConvert.setOnClickListener{
            fm.beginTransaction().replace(R.id.fragment_container_view, ConvertFragment()).addToBackStack("1").commit()
        }

        return view
    }
}