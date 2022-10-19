package com.example.apiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ConvertResultFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_convert_result, container, false)

        val currency1Text: TextView = view.findViewById(R.id.convertResultCur1Text)
        val currency2Text: TextView = view.findViewById(R.id.convertResultCur2Text)
        val amountText: TextView = view.findViewById(R.id.convertResultAmountText)
        val valueText: TextView = view.findViewById(R.id.convertResultValueText)

        if (MainActivity.firstCurrency.isNotBlank()){
            currency1Text.text = MainActivity.firstCurrency
            currency2Text.text = MainActivity.secondCurrency
            amountText.text = MainActivity.outputAmount.toString()
            valueText.text = MainActivity.outputValue.toString()
        }

        return view
    }
}