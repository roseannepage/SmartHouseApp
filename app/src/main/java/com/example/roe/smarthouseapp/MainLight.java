package com.example.roe.smarthouseapp;

import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;

public class MainLight extends AppCompatActivity {

    SeekBar seekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_light);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbar.setMax(255);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {

            String light = "off";

            @Override
            public void onClick(View view) {
                if(light == "off") {
                    Snackbar.make(view, "Light is turned On", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    light = "on";
                }//end if statement

                else if(light == "on") {
                    Snackbar.make(view, "Light is turned Off", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    light = "off";
                }//end if statement
            }//end onClick function
        });//end FloatingActionButton

        float curBrightnessValue = 0;

        try {
            curBrightnessValue = Settings.System.getInt(
                    getContentResolver(),
                    Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }

        int screen_brightness = (int) curBrightnessValue;
        seekbar.setProgress(screen_brightness);

        if(Settings.System.canWrite(this) != false) {

            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progress = 0;

                @Override
                public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                    progress = progressValue;
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                    // Do something here,
                    // if you want to do anything at the start of
                    // touching the seekbar
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Settings.System.putInt(getContentResolver(),
                            Settings.System.SCREEN_BRIGHTNESS,
                            progress);
                }
            });
        }

    }//end onCreate function

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

}//end MainLight class
