package com.example.roe.smarthouseapp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class OdometerActivity extends AppCompatActivity {

    protected static TextView tripInfo;
    protected static TextView totalDistInfo;
    protected static int trip;
    protected static int totalDist;
    protected static ImageButton resetTrip;

    private String tripPreInfo = "Trip Distance Travelled: ";
    private String totalDistPreInfo = "Total Distance Travelled: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odometer);

        /* */
        tripInfo = (TextView) findViewById(R.id.trip_info);
        tripInfo.setText(tripPreInfo + String.valueOf(trip) + "km");

        /* */
        totalDistInfo = (TextView) findViewById(R.id.total_distance_info);
        totalDistInfo.setText(totalDistPreInfo + String.valueOf(totalDist) + "km");

        /* */
        resetTrip = (ImageButton) findViewById(R.id.reset_trip);
        resetTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip = 0;
                tripInfo.setText(tripPreInfo + trip + "km");
                DriveActivity.driving = false;
            }
        });

    }
}
