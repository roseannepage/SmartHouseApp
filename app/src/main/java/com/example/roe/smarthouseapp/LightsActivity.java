package com.example.roe.smarthouseapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class LightsActivity extends AppCompatActivity {
    protected ImageButton offLight;
    protected ImageButton lowLight;
    protected ImageButton highLight;
    protected TextView highIndicator;
    protected TextView lowIndicator;
    protected TextView offIndicator;

    protected static boolean off;
    protected static boolean low;
    protected static boolean high;

    protected static RadioGroup radioGroup;
    private static int radioID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);

        offLight = (ImageButton) findViewById(R.id.off_lights);
        offIndicator = (TextView) findViewById(R.id.off_indicator);
        offLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high = false;
                low = false;
                off = true;
                setVis();
                Toast.makeText(getApplicationContext(), "Headlights off", Toast.LENGTH_SHORT).show();
            }
        });

        lowLight = (ImageButton) findViewById(R.id.low_lights);
        lowIndicator = (TextView) findViewById(R.id.low_indicator);
        lowLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high = false;
                low = true;
                off = false;
                setVis();
                Toast.makeText(getApplicationContext(), "Headlights low", Toast.LENGTH_SHORT).show();
            }
        });

        highLight = (ImageButton) findViewById(R.id.high_lights);
        highIndicator = (TextView) findViewById(R.id.high_indicator);
        highLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                high = true;
                low = false;
                off = false;
                setVis();
                Toast.makeText(getApplicationContext(), "Headlights high", Toast.LENGTH_SHORT).show();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioID = checkedId;
            }
        });

        radioGroup.check(radioID);

        setVis();
    }

    private void setVis() {
        if (high) {
            highIndicator.setVisibility(View.VISIBLE);
            lowIndicator.setVisibility(View.INVISIBLE);
            offIndicator.setVisibility(View.INVISIBLE);
        } else if (low) {
            lowIndicator.setVisibility(View.VISIBLE);
            highIndicator.setVisibility(View.INVISIBLE);
            offIndicator.setVisibility(View.INVISIBLE);
        } else {
            offIndicator.setVisibility(View.VISIBLE);
            highIndicator.setVisibility(View.INVISIBLE);
            lowIndicator.setVisibility(View.INVISIBLE);
        }
    }
}
