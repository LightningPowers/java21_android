package java21.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.Toast
import java.text.FieldPosition
import kotlin.math.log

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)

        //Accessing the list of countries
        val countryList: Array<String> = resources.getStringArray(R.array.country_list_form)
        Log.i("test", countryList[1])

        //Get access to spinner (for dropdown menu)
        val spinner: Spinner = view.findViewById(R.id.spinner)

            spinner.adapter = activity?.let { ArrayAdapter.createFromResource(it, R.array.country_list_form, android.R.layout.simple_spinner_item) } as SpinnerAdapter
            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    Log.i("spinnerTest", "NOTHING SELECTED")
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val item = parent?.getItemAtPosition(position).toString()
                    Log.i("spinnerTest", (parent?.getItemAtPosition(position).toString() + " SELECTED"))
                }
        }

        return view
    }
}