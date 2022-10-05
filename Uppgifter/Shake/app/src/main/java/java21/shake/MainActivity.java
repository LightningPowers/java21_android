package java21.shake;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SensorManager sm = null;
    TextView xAxisText, yAxisText, zAxisText = null;
    List list;
    boolean activated = false;

    SensorEventListener sel = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
        public void onSensorChanged(SensorEvent event) {
            //Formatting amount of decimals
            DecimalFormat df = new DecimalFormat("#.###");

            //Sets values from each axis into an array
            float[] values = event.values;

            //Sets text with data from each axis from array
            xAxisText.setText("X-Axis: " + df.format(values[0]));
            yAxisText.setText("Y-Axis: " + df.format(values[1]));
            zAxisText.setText("Z-Axis: " + df.format(values[2]));
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
}