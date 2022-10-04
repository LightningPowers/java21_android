package java21.shake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int buttonClicks;
    private boolean activated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instances an element with its given id
        Button b = (Button) findViewById(R.id.button);
        TextView tv = (TextView) findViewById(R.id.text_box);

        //Lambda on click event for button
        b.setOnClickListener( e -> {
            activated = !activated;
            Toast.makeText(this, Boolean.toString(activated), Toast.LENGTH_SHORT).show();

            //Changes button text if program is on/off
            if (activated){
                b.setText("Turn off!");
            }
            else if (!activated){
                b.setText("Start!");
            }

            buttonClicks++;
            tv.setText("Clicked: " + buttonClicks + " times!");
            Log.i("buttonTest", "Button has been clicked " + buttonClicks + " times.");
        });
    }
}