package com.example.perfectinvernadero;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class magnetico extends AppCompatActivity
        implements SensorEventListener {
    TextView x,y,z;
    Sensor sensor;
    SensorManager sm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetico);

        x = (TextView) findViewById(R.id.x);
        y = (TextView) findViewById(R.id.y);
        z = (TextView) findViewById(R.id.z);

        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sm.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        x.setText(String.valueOf(event.values[0]));
        y.setText(String.valueOf(event.values[1]));
        z.setText(String.valueOf(event.values[2]));
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}