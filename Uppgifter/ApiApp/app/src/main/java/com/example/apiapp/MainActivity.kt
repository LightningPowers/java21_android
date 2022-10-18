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
}