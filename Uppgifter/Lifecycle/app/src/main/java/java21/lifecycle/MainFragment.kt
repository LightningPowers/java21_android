package java21.lifecycle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)

        val username: EditText = view.findViewById(R.id.usernameText)
        val passWord: EditText = view.findViewById(R.id.passwordText)
        val submitBtn: Button = view.findViewById(R.id.loginButton)

        submitBtn.setOnClickListener{ view ->
            verifyLogin(username.text.toString(), passWord.text.toString())
            Log.i("loginButton", ("clicked login" + MainActivity.Companion.loggedIn))}
            if (MainActivity.Companion.loggedIn){
                //Toast.makeText(this@MainFragment.requireActivity(), "Logged In!", Toast.LENGTH_LONG).show()
                Log.i("loginButton", "Successfully Logged In!")
            }
            else {
                //Toast.makeText(this@MainFragment.requireActivity(), "Incorrect Login!", Toast.LENGTH_LONG).show()
                Log.i("loginButton", "Incorrect Login!")
            }

        return view
    }

    private fun verifyLogin (userName: String, passWord: String) {
        Log.i("loginInfo", "Username: $userName Password: $passWord")

        if (userName == "Jonas" && passWord == "Persson") {
            MainActivity.Companion.loggedIn = true
        }
    }
}
