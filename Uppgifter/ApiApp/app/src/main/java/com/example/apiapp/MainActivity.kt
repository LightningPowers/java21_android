package com.example.apiapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        //Used in weather
        var cityName: String = ""
        var countryCode: String = ""

        //Used in convert url request
        var baseCurrency: String = ""
        var targetCurrency: String = ""
        var amount: Int = 0

        //Used in convert local data
        var firstCurrency: String = ""
        var secondCurrency: String = ""
        var outputAmount: Int = 0
        var outputValue: Float = 0.0f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}