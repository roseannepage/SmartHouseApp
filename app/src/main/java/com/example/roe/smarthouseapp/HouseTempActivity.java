package com.example.roe.smarthouseapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;




import java.util.ArrayList;

/**
 * This is the activity for the house setting. This class is
 * written by Narges Karimi Tabar.
 */
public class HouseTempActivity extends AppCompatActivity {
    /**
     * Attribute to hold object of type Listview
     */
    ListView lv;
    /**
     * Attribute to hold object of type ImageButton
     */
    ImageButton addTemp;
    /**
     * Attribute to hold object of type Spinner
     */
    Spinner spinner1;
    /**
     * Attribute to hold object of type Spinner
     */
    Spinner spinner2;
    /**
     * Attribute to hold object of type Spinner
     */
    Spinner spinner3;
    /**
     * Attribute to hold object of type ArrayAdapter of type String
     */
    ArrayAdapter<String> listArrayAdapter;
    /**
     * Attribute to hold object of type ArrayAdapter of type String
     */
    ArrayAdapter<String> daysArrayAdapter;
    /**
     * Attribute to hold object of type ArrayAdapter of type String
     */
    ArrayAdapter<String> degreesAdapter;
    /**
     * Attribute to hold collection of days
     */
    String[] days = {"DAY", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    /**
     * Attribute to hold collection of degrees
     */
    String[] degrees = {"TEMP", "10°C", "11°C", "12°C", "13°C", "14°C", "15°C", "16°C", "17°C", "18°C", "19°C", "20°C", "21°C", "22°C", "23°C", "24°C", "25°C", "26°C", "27°C", "28°C", "29°C", "30°C"};
    /**
     * Attribute to hold collection of times
     */
    String[] times = {"TIME", "1:00AM", "2:00AM", "3:00AM", "4:00AM", "5:00AM", "6:00AM", "7:00PM", "8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "12:00PM", "13:00PM", "14:00PM", "15:00PM", "16:00PM", "17:00PM", "18:00PM", "19:00PM", "20:00PM", "21:00PM", "22:00PM", "23:00PM", "12:00AM"};
    ArrayList<String> list = new ArrayList<String>();

    /**
     * This function initializes objects of type spinner, listview, and button. Clicking
     * on each spinner will open up a list of data (day, time and temperature).
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_temp);
        spinner1 = (Spinner) findViewById(R.id.spinnerDay);
        spinner2 = (Spinner) findViewById(R.id.spinnerTemp);
        spinner3 = (Spinner) findViewById(R.id.spinnerTime);
        lv = (ListView) findViewById(R.id.listviewTemp);
        addTemp = (ImageButton) findViewById(R.id.tempAdd);

        /**
         * Clicking on plus button will add the selected items to the listview
         */
        addTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected1 = (String) spinner1.getSelectedItem();
                String selected3 = (String) spinner3.getSelectedItem();
                String selected2 = (String) spinner2.getSelectedItem();
                //adding to listview
                list.add(selected1 + " " + selected3 + " " + selected2);
                listArrayAdapter.notifyDataSetChanged(); //notifying adaptor
            }
        });

        /**setting adapter for the listview */
        listArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        lv.setAdapter(listArrayAdapter); //setting adapter for the listview

        /** settings days into dropdownlist for spinner1 */
        daysArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days);
        daysArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(daysArrayAdapter);

        /** settings degrees into dropdownlist for spinner3 */
        degreesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, times);
        degreesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner3.setAdapter(degreesAdapter);

        /** settings degrees into dropdownlist for spinner2 */
        degreesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, degrees);
        degreesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(degreesAdapter);

    }
}





