package java21.shake;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sm = null;
    TextView xAxisText, yAxisText, zAxisText = null;
    List list;
    boolean activated = false;
    ProgressBar xAxisBar, yAxisBar, zAxisBar = null;
    //Formatting amount of decimals
    DecimalFormat df = new DecimalFormat("#.###");

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {

            //Sets values from each axis into an array
            float[] values = event.values;

            //Sets text with data from each axis from array
            xAxisText.setText("X-Axis: " + df.format(values[0]));
            yAxisText.setText("Y-Axis: " + df.format(values[1]));
            zAxisText.setText("Z-Axis: " + df.format(values[2]));

            movementToProgressBar(values[0], xAxisBar);
            movementToProgressBar(values[1], yAxisBar);
            movementToProgressBar(values[2], zAxisBar);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adding sensor manager
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Connects an element with its given id
        Button b = (Button) findViewById(R.id.button);
        xAxisText = (TextView) findViewById(R.id.x_axis_text);
        yAxisText = (TextView) findViewById(R.id.y_axis_text);
        zAxisText = (TextView) findViewById(R.id.z_axis_text);

        xAxisBar = (ProgressBar) findViewById(R.id.xAxisBar);
        yAxisBar = (ProgressBar) findViewById(R.id.yAxisBar);
        zAxisBar = (ProgressBar) findViewById(R.id.zAxisBar);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);

        //Lambda on click event for button
        b.setOnClickListener( e -> {
            Toast.makeText(this, Boolean.toString(activated), Toast.LENGTH_SHORT).show();

            //Changes button text if program is on/off
            if (!activated){
                b.setText("Turn off!");
                if(list.size()>0){
                    sm.registerListener(sel, (Sensor) list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
                }else{
                    Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
                }
                activated = true;
            }
            else {
                b.setText("Start!");
                sm.unregisterListener(sel);
                activated = false;
            }
        });
    }

    //Method to show movement on the progressbar
    private void movementToProgressBar(Float positionalValue, ProgressBar progressBar){
        int number = Math.round(positionalValue);

        if (-10 >= number){
            progressBar.setProgress(0);
        }
        else if (number >= -10 && number < -8){
            progressBar.setProgress(10);
        }
        else if (number >= -8 && number < -6){
            progressBar.setProgress(20);
        }
        else if (number >= -6 && number < -4){
            progressBar.setProgress(30);
        }
        else if (number >= -4 && number < -2){
            progressBar.setProgress(40);
        }
        else if (number >= -2 && number < 2){
            progressBar.setProgress(50);
        }
        else if (number >= 2 && number < 4){
            progressBar.setProgress(60);
        }
        else if (number >= 4 && number < 6){
            progressBar.setProgress(70);
        }
        else if (number >= 4 && number < 6){
            progressBar.setProgress(80);
        }
        else if (number >= 6 && number < 8){
            progressBar.setProgress(90);
        }
        else if (number >= 8 && number < 10){
            progressBar.setProgress(100);
        }
    }
}