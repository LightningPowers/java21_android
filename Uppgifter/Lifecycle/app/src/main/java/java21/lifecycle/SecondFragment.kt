package java21.lifecycle

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import android.widget.TextClock
import android.widget.TextView
import android.widget.Toast
import java.text.FieldPosition
import java.util.jar.Attributes.Name
import kotlin.math.log

class SecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_second, container, false)

        val nameText: TextView = view.findViewById(R.id.formName)
        val bdayText: TextView = view.findViewById(R.id.formBday)
        val eIDText: TextView = view.findViewById(R.id.formID)


        val saveBtn: Button = view.findViewById(R.id.formButton)
        val errorTxt: TextView = view.findViewById(R.id.formErrorText)

        //Accessing the list of countries
        val countryList: Array<String> = resources.getStringArray(R.array.country_list_form)
        Log.i("test", countryList[1])

        var selectedCountry: String = ""

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
                    selectedCountry = (parent?.getItemAtPosition(position).toString())
                }
        }
        //todo: get boolean from checkbox

        saveBtn.setOnClickListener{
            var isValid: Boolean = checkFields(nameText.text.toString(), bdayText.text.toString(),
            eIDText.text.toString(), selectedCountry)
            if (!isValid) {
                errorTxt.text = "Please set input in all fields!"
                Log.i("saveButton", "Rejected field input")
            }
            else {
                errorTxt.text = ""
                Log.i("saveButton", "Accepted field input")
            }
        }

        return view
    }

    private fun checkFields(name: String, birthDate: String, employeeID: String, officeLocation: String) :Boolean {
        var areFieldsFilled: Boolean = true

        //Checks for invalid (or empty) inputs
        if (name == "Name" || name.isEmpty() || birthDate == "Date of Birth (YYYY/MM/DD" || birthDate.isEmpty()
            || employeeID == "Employee ID" || employeeID.isEmpty() || officeLocation.isBlank()){
            areFieldsFilled = false
        }

        //If no errors are found, saves the input data
        if (areFieldsFilled){
            MainActivity.name = name
            MainActivity.birthdate = birthDate
            MainActivity.employee_id = Integer.parseInt(employeeID)
            MainActivity.officeLocation = officeLocation
        }

        return areFieldsFilled
    }
}