package com.example.roe.smarthouseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class FuelLevelActivity extends AppCompatActivity {
    protected static double litres = 25; // 60 litre tank
    protected static TextView distanceDisplay;
    protected static ProgressBar fuelBar;
    protected static ImageButton refuel;
    private String distancePreInfo = "Distance Remaining: ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuel_level);

        distanceDisplay = (TextView) findViewById(R.id.distance_display);
        fuelBar = (ProgressBar) findViewById(R.id.fuel_bar);

        fuelBar.setProgress((int) litres);
        setDistanceRemaining();

        /*  */
        refuel = (ImageButton) findViewById(R.id.refuel_button);
        refuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litres = 60;
                fuelBar.setProgress((int) litres);
                setDistanceRemaining();

                Toast.makeText(getApplicationContext(), "All fueled up!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private double getDistanceRemaining() {
        /* 8.9L/100km fuel efficiency, therefore 0.089L = 1km, therefore litres / 0.089L = km remaining */
        return litres / .089;
    }

    private void setDistanceRemaining() {
        distanceDisplay.setText(distancePreInfo + String.format("%.2f", getDistanceRemaining()) + "km");
    }

}
