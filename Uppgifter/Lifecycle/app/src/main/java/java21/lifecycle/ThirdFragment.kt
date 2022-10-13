package java21.lifecycle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ThirdFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_third, container, false)

        val nameText: TextView = view.findViewById(R.id.data_Name)
        val dateText: TextView = view.findViewById(R.id.data_BDay)
        val idText: TextView = view.findViewById(R.id.data_ID)
        val officeText: TextView = view.findViewById(R.id.data_Office)
        val remoteText: TextView = view.findViewById(R.id.data_Remote)

        //Prevents data from showing before it has been submitted
        if (MainActivity.name.isNotBlank()) {
            nameText.text = MainActivity.name
            dateText.text = MainActivity.birthdate
            idText.text = MainActivity.employee_id.toString()
            officeText.text = MainActivity.officeLocation
            if (MainActivity.isRemote){
                remoteText.text = "Currently working remotely"
            }
            else {
                remoteText.text = "Not working remotely"
            }
        }
        return view
    }
}