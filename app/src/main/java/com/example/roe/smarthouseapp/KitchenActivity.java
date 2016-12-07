package com.example.roe.smarthouseapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class KitchenActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "KitchenActivity";

    //ArrayList<String> chatMessages = new ArrayList<>();
    ListView listView;
    View view;
    ArrayAdapter<String> adapter;
    String[] options = {"Main Light", "Microwave", "Fridge"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_kitchen);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KitchenActivity.this);
                builder.setTitle("INFORMATION");
                builder.setMessage("The Kitchen Smart Environment remote can wirelessly control appliances in your kitchen:\n\n Main Light: turn the light on or off, and dim the light to desired brightness.\n\n Microwave: Enter desired cook time, reset clock, and start or stop your microwave.\n\n Fridge: Set the temperature of both the fridge and the freezer.\n\n Add Device: Enter any new devices that are compatible with this application.");

                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DO NOTHING
                    }//end onClick function
                });//end PositiveButton

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //DO NOTHING
                    }//end onClick function
                });//end NegativeButton

                // Create the AlertDialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }//end onClick function
        });//end FloatingActionButton*/




        //LayoutInflater inflater = Kitchen.this.getLayoutInflater();
        //view = inflater.inflate(R.layout.activity_kitchen, null);

        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(listView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, options);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>arg, View view, int position, long id){

                switch(position){
                    case 0:
                        Intent intent = new Intent(KitchenActivity.this, MainLight.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent2 = new Intent(KitchenActivity.this, Microwave.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(KitchenActivity.this, Fridge.class);
                        startActivity(intent3);
                        break;
                }//end switch

            }//end onItemClick function
        });//end ItemClickListener

    }//end onCreate function



    @Override
    protected void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }
}