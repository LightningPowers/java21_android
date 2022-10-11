package java21.kotlintest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    val PI = 3.14 //Same as final(Java) or const(JS)
    var foo: Int = 5
    var bar: String = "MonkaS"
    private var b = false
    var aList: ArrayList<String> = ArrayList() //Empty arraylist
    var aList2: ArrayList<String> = arrayListOf("Hey", "Goodbye")
    var arr = Array<Int>(4){1;2;3;4} //Specified datatype array
    var arr1 = arrayOf(1, 2, 24, "Jonas", 'C') //Dynamic array
    var arr2 = arrayOf("Swag", "Skate") //Adaptive array
    var arr3 = floatArrayOf(21.4f, 213.42f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn : Button = findViewById(R.id.button)

        btn.setOnClickListener ({ v -> addition(2) })

        Log.i("test", aList2[1].toString())
        foo = 13
        arr[3] = 5
        aList.add("Penguin")
        aList.clear()

        if (foo > 10){
            Toast.makeText(this, "YES", Toast.LENGTH_LONG).show()
        }
        else if (foo > 8){
            Toast.makeText(this, "NO", Toast.LENGTH_LONG).show()
        }
        else {
            Toast.makeText(this, "NICE", Toast.LENGTH_LONG).show()
        }

        var result = if (foo > 11){
            50
        }
        else {
            100
        }

        //Switch case
        when(result) {
            1 -> Log.i("whenTest", Integer.toString(result))
            2 -> Log.i("whenTest", Integer.toString(result))
            3 -> Log.i("whenTest", Integer.toString(result))
            4 -> {
                Log.i("whenTest", Integer.toString(result))
                Log.i("whenTest", Integer.toString(result))
            }
        }

        //While loop
        var count = 0
        while (count < 5){
            count++
            Log.i("whileTest", Integer.toString(count))
        }

        //Do while loop
        do {
            count++
            Log.i("whileTest", Integer.toString(count))
        } while (count < 10)

        //Foreach loop
        for (x in arr){
            Log.i("foreachTest", Integer.toString(x))
        }

        //Foreach loop with ranges operator
        for (i in 0 .. arr.size){
            Log.i("foreachTest2", arr1[i].toString())
        }

        //Check class
        Log.i("classTest", ("test" is String).toString())

        checkType(false)
        isString('1')
    }
}
    //Function that can also have public/private etc.
    //Functions parameters can have default values if not given
    private fun addition(num1: Int, num2: Int = 5): Int {
        var result: Int = num1 + num2
        Log.i("functionTest", result.toString())

        return result
    }

    //Shows the given datatype class
    //Any means whatever datatype
    fun checkType (x : Any) {
        var type = x.javaClass.kotlin.toString()
        Log.i("functionTest", type)
    }

    fun isString (x : Any): Boolean {
        var result = x is String

        Log.i("functionTest", result.toString())

        return result
    }