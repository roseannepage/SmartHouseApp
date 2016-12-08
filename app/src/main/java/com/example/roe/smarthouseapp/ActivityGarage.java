package com.example.roe.smarthouseapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

/**
 * This is activity garage that allows the user to open and close the
 * garage door and turn a light off and on. This class is written
 * by Narges Karimi Tabar.
 */
public class ActivityGarage extends AppCompatActivity {
    /** Object to hold activity name   */
    protected static final String ACTIVITY_NAME = "ActivityGarage";
    /** Attribute that holds an object of type Switch  */
    Switch mySwitch;
    /** Attribute that holds an object of type ImageButton  */
    ImageButton doorClosed;
    /** Attribute that holds an object of type ImageButton  */
    ImageButton doorOpened;
    /** Attribute that holds an object of type ImageButton  */
    ImageButton yellowBulb;
    /** Attribute that holds an object of type ImageButton  */
    ImageButton whiteBulb;

    /**
     * This functions sets the main funtionalities of different objects
     * like imagebutton and switch.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage);
        Log.i(ACTIVITY_NAME, "In OnCreate()");

        mySwitch = (Switch) findViewById(R.id.switchLight);
        //set the switch to on
        //  mySwitch.setChecked(true);

        // attaching the listerning to check if the state changes
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            /**
             * Switching the switch button to turn the light off and on
             * @param buttonView
             * @param isChecked
             */
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Context context = getApplicationContext();
                yellowBulb = (ImageButton) findViewById(R.id.garageLightOn);
                if (isChecked) { // if the switch is checked
                    CharSequence text = "Light On"; // the text message that will show in the toast
                    yellowBulb.setVisibility(View.VISIBLE);
                    int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                    Toast toast = Toast.makeText(context, text, duration); // making Toast object toast
                    // toast.show(); // displaying toast object on bottom of screen
                    mySwitch.setChecked(true);

                } else { // if the switch is not checked
                    whiteBulb = (ImageButton) findViewById(R.id.garageLight);
                    CharSequence text = "Light Off";
                    yellowBulb.setVisibility(View.INVISIBLE);
                    whiteBulb.setVisibility(View.VISIBLE);

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    //  toast.show(); // displaying toast object on bottom of screen
                    mySwitch.setChecked(false);
                }

            }
        });

        Button openButton = (Button) findViewById(R.id.openDoor);
        doorClosed = (ImageButton) findViewById(R.id.doorButtonCLosed);
        //set the switch to on
        // mySwitch.setChecked(true);

        // attaching the listerning to check if the state changes
        openButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Setting the light to turn on when the garage door is opened
             * @param v
             */
            @Override
            public void onClick(View v) {
                //set the switch to on
                CharSequence text = "Door Opened";
                doorClosed.setVisibility(View.GONE);
                doorOpened.setVisibility(View.VISIBLE);
                int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                toast.show();
                mySwitch.setChecked(true);
            }
        });

        Button closeButton = (Button) findViewById(R.id.closeDoor);
        doorOpened = (ImageButton) findViewById(R.id.doorButtonOpened);
        closeButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Setting the light to turn off when the garage door is closed
             * @param v
             */
            @Override
            public void onClick(View v) {
                //set the switch to on
                CharSequence text = "Door Closed";
                doorClosed.setVisibility(View.VISIBLE);
                doorOpened.setVisibility(View.INVISIBLE);
                int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                toast.show();
                mySwitch.setChecked(false);
            }
        });

        Button stopButton = (Button) findViewById(R.id.stopDoor);
        doorOpened = (ImageButton) findViewById(R.id.doorButtonOpened);
        stopButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Creating a toast message when the door is getting closed
             * @param v
             */
            @Override
            public void onClick(View v) {
                //set the switch to on
                CharSequence text = "Door opening/closing Stopped";
                int duration = Toast.LENGTH_SHORT; // setting the duration the toast is on screen for
                Toast toast = Toast.makeText(ActivityGarage.this, text, duration); // making Toast object toast
                toast.show();
            }
        });
    }
}
