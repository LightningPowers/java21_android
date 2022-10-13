package java21.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        var loggedIn: Boolean = true

        //Form variables used in 2nd and 3rd fragment
        var name: String = ""
        var birthdate: String = ""
        var employee_id: Int = 0
        var officeLocation: String = ""
        var isRemote: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menu buttons, always visible
        val mainBtn: Button = findViewById(R.id.button_main)
        val secBtn: Button = findViewById(R.id.button_second)
        val trdBtn: Button = findViewById(R.id.button_third)

        val mainFragment = MainFragment()
        val secondFragment = SecondFragment()
        val thirdFragment = ThirdFragment()

        mainBtn.setOnClickListener {
            //Need to successfully log in to change tab (fragment)
            if (loggedIn){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, mainFragment).commit()
            Log.i("btnMain","Changed to main fragment")}
        }

        secBtn.setOnClickListener {
            if (loggedIn){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, secondFragment).commit()
            Log.i("btnSecond","Changed to second fragment")}
        }

        trdBtn.setOnClickListener {
            if (loggedIn){
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view, thirdFragment).commit()
            Log.i("btnThird","Changed to third fragment")}
        }
    }
}