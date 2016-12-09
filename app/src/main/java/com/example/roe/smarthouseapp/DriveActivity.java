package com.example.roe.smarthouseapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class DriveActivity extends AppCompatActivity {
    protected static Switch tripSwitch;
    protected static boolean driving;
    protected static ImageButton oilCheck;
    protected static boolean oilClean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drive);

        /**/
        tripSwitch = (Switch) findViewById(R.id.drive_switch);

        /**/
        oilCheck = (ImageButton) findViewById(R.id.oil_check_button);
        oilCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oilClean = true;
                oilCheck.setVisibility(View.INVISIBLE);
                Toast.makeText(getApplicationContext(), "Oil's All Good!", Toast.LENGTH_SHORT).show();
            }
        });

        /* listener for the drive switch */
        tripSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tripSwitch.isChecked() && oilClean) {
                    /* user turned the drive switch on and oil is clean */
                    final EditText input = new EditText(getApplicationContext());
                    input.setTextColor(Color.BLACK);
                    input.setFilters(new InputFilter[] {
                            new InputFilter.LengthFilter(4), DigitsKeyListener.getInstance()
                    });
                    input.setKeyListener(DigitsKeyListener.getInstance());

                    AlertDialog.Builder alert = new AlertDialog.Builder(DriveActivity.this);
                    alert.setTitle("How many kilometers do you want to tripSwitch?")
                            .setView(input)
                            .setPositiveButton("Start Trip", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (!input.getText().toString().equals("")) {
                                        /* user started trip */
                                        OdometerActivity.trip = Integer.parseInt(input.getText().toString());
                                        driving = true;
                                    } else {
                                        /* user didn't enter anything in the dialog */
                                        Toast.makeText(getApplicationContext(), "Nothing was entered!", Toast.LENGTH_SHORT).show();
                                        driving = false;
                                        tripSwitch.setChecked(driving);
                                    }
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    /* user cancelled the trip */
                                    tripSwitch.setChecked(false);
                                }
                            })
                            .create()
                            .show();
                } else if (!oilClean) {
                    /* user clicked drive switch but oil not clean */
                    Toast.makeText(getApplicationContext(), "Clean your oil!", Toast.LENGTH_SHORT).show();
                    tripSwitch.setChecked(false);
                } else {
                    /* user turned the drive switch off and finished the trip */
                    OdometerActivity.totalDist += OdometerActivity.trip;

                    /* removing the amount of litres used from the trip.
                     * 8.9L/100km fuel efficiency, therefore 0.089L = 1km */
                    FuelLevelActivity.litres -= OdometerActivity.trip * 0.089;

                    Toast.makeText(getApplicationContext(), "Trip Complete!", Toast.LENGTH_SHORT).show();
                    driving = false;

                    if ((OdometerActivity.totalDist % 6000) == 0) {
                        /* 6000km travelled */
                        AlertDialog.Builder alert = new AlertDialog.Builder(DriveActivity.this);
                        alert.setMessage("Check Oil!")
                                .create()
                                .show();

                        oilClean = false;
                        oilCheck.setVisibility(View.VISIBLE);
                    }

                }
            }
        });

        tripSwitch.setChecked(driving);


    }
}
