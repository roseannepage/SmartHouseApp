package com.example.roe.smarthouseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Fridge extends AppCompatActivity {

    private TextView fridgeTemp;
    private TextView freezerTemp;

    private String[] fridgeTemps = { "2.0℃", "2.5℃", "3.0℃", "3.5℃", "4.0℃", "4.5℃", "5.0℃"};
    private String[] freezerTemps = {"-17.0℃","-17.5℃","-18.0℃","-18.5℃","-19.0℃","-19.5℃","-20.0℃"};

    private Spinner fridgeSpinner;
    private Spinner freezerSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fridge);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fridgeTemps);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, freezerTemps);

        fridgeTemp = (TextView) findViewById(R.id.fridgeTemp);
        freezerTemp = (TextView) findViewById(R.id.freezerTemp);

        fridgeSpinner = (Spinner) findViewById(R.id.fridgeSettings);
        fridgeSpinner.setAdapter(adapter);

        freezerSpinner = (Spinner) findViewById(R.id.freezerSettings);
        freezerSpinner.setAdapter(adapter2);

        fridgeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fridgeTemp.setText(fridgeTemps[position]);
                Toast.makeText(Fridge.this, "Fridge temperature is now set to " + fridgeTemps[position] + "℃", Toast.LENGTH_SHORT)
                        .show();
            }//end onItemSelected function

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                /*fridgeTemp.setText(fridgeTemps.toString());
                Toast.makeText(Fridge.this, "Fridge temperature is at " + fridgeTemps.toString() + "℃", Toast.LENGTH_LONG)
                        .show();*/
            }//end onNothingSelected function
        });//end ItemSelectedListener

        freezerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                freezerTemp.setText(freezerTemps[position]);
                Toast.makeText(Fridge.this, "Freezer temperature is now set to " + freezerTemps[position] + "℃", Toast.LENGTH_LONG)
                        .show();
            }//end onItemSelected function

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                /*freezerTemp.setText(freezerTemp.toString());
                Toast.makeText(Fridge.this, "Freezer temperature is at " + freezerTemps.toString() + "℃", Toast.LENGTH_SHORT)
                        .show();*/
            }//end onNothingSelected function
        });//end ItemSelectedListener

    }//end onCreate function

}//end Fridge class
