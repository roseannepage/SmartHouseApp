package com.example.roe.smarthouseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class AutoTemperatureActivity extends AppCompatActivity {
    protected static ImageButton frontTempUp;
    protected static SeekBar frontSeek;
    protected static ImageButton frontTempDown;
    protected static TextView frontDegrees;
    protected static int frontTemp = 25;


    protected static ImageButton rearTempUp;
    protected static SeekBar rearSeek;
    protected static ImageButton rearTempDown;
    protected static TextView rearDegrees;
    protected static int rearTemp = 25;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_temperature);

        /* Instantiating the front car attributes */
        frontTempUp = (ImageButton) findViewById(R.id.front_temp_up);
        frontSeek = (SeekBar) findViewById(R.id.front_temp_bar);
        frontTempDown = (ImageButton) findViewById(R.id.front_temp_down);
        frontDegrees = (TextView) findViewById(R.id.front_degrees);

        /* Setting the degree indicator to the current front temp */
        frontDegrees.setText("Front Temperature: " + String.valueOf(frontTemp) + "C");

        /* listener for the front temp up button to set degree indicator and seek bar to proper values
        *  if 50C, display Toast telling user max temp */
        frontTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frontTemp < 50) {
                    frontTemp++;
                    frontSeek.setProgress(frontTemp);
                    frontDegrees.setText("Front Temperature: " + String.valueOf(frontTemp) + "C");
                } else {
                    Toast.makeText(getApplicationContext(), "Maximum Front Temperature!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /* listener for the front temp down button to set degree indicator and seek bar to proper values
         * if 0C, display Toast telling user min temp */
        frontTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (frontTemp > 0) {
                    frontTemp--;
                    frontSeek.setProgress(frontTemp);
                    frontDegrees.setText("Front Temperature: " + String.valueOf(frontTemp) + "C");
                } else {
                    Toast.makeText(getApplicationContext(), "Minimum Front Temperature!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /* listener for the front seek bar to set the degree indicator to the proper value */
        frontSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                frontTemp = progress;
                frontDegrees.setText("Front Temperature: " + String.valueOf(frontTemp) + "C");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        /* Instantiating the rear car attributes */
        rearTempUp = (ImageButton) findViewById(R.id.rear_temp_up);
        rearSeek = (SeekBar) findViewById(R.id.rear_temp_bar);
        rearTempDown = (ImageButton) findViewById(R.id.rear_temp_down);
        rearDegrees = (TextView) findViewById(R.id.rear_degrees);

        /* Setting the degree indicator to the current rear temp */
        rearDegrees.setText("Rear Temperature: " + String.valueOf(rearTemp) + "C");

         /* listener for the rear temp up button to set degree indicator and seek bar to proper values
          * if 50C, display Toast telling user max temp */
        rearTempUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rearTemp < 50) {
                    rearTemp++;
                    rearSeek.setProgress(rearTemp);
                    rearDegrees.setText("Rear Temperature: " + String.valueOf(rearTemp) + "C");
                } else {
                    Toast.makeText(getApplicationContext(), "Maximum Rear Temperature!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /* listener for the rear temp down button to set degree indicator and seek bar to proper values
         * if 0C, display Toast telling user min temp */
        rearTempDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rearTemp > 0) {
                    rearTemp--;
                    rearSeek.setProgress(rearTemp);
                    rearDegrees.setText("Rear Temperature: " + String.valueOf(rearTemp) + "C");
                } else {
                    Toast.makeText(getApplicationContext(), "Minimum Rear Temperature!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /* listener for the rear seek bar to set the degree indicator to the proper value */
        rearSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rearTemp = progress;
                rearDegrees.setText("Rear Temperature: " + String.valueOf(rearTemp) + "C");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
}
