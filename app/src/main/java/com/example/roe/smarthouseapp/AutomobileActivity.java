package com.example.roe.smarthouseapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class AutomobileActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "AutomobileActivity";
    AutomobileActivity.AutomobileInfoDialogFragment infoDialog;
    ImageButton  infoBtn;
    ListView autolrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);

        infoBtn = (ImageButton) findViewById(R.id.lrInfoBtn);
        infoDialog = new AutomobileActivity.AutomobileInfoDialogFragment();


        autolrList = (ListView)findViewById(R.id.autolistviewlr) ; // get list view object

        String[] values = new String[] {"Temperature", "Fuel Level", "Radio", "GPS", "Lights", "Odometer", "Drive"}; // define array values to show in ListView

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_auto_listview, values); // define adapter to make the list

        autolrList.setAdapter(adapter); // set the adapter to make the list

        autolrList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                Intent intent;
                switch (item) {
                    case "Temperature":
                        intent = new Intent(AutomobileActivity.this, AutoTemperatureActivity.class);
                        startActivity(intent);
                        break;
                    case "Fuel Level":
                        intent = new Intent(AutomobileActivity.this, FuelLevelActivity.class);
                        startActivity(intent);
                        break;
                    case "Radio":
                        intent = new Intent(AutomobileActivity.this, RadioActivity.class);
                        startActivity(intent);
                        break;
                    case "GPS":
                        intent = new Intent(android.content.Intent.ACTION_VIEW,
                                Uri.parse("http://maps.google.com/maps"));
                        startActivity(intent);
                        break;
                    case "Lights":
                        intent = new Intent(AutomobileActivity.this, LightsActivity.class);
                        startActivity(intent);
                        break;
                    case "Drive":
                        intent = new Intent(AutomobileActivity.this, DriveActivity.class);
                        startActivity(intent);
                        break;
                    case "Odometer":
                        intent = new Intent(AutomobileActivity.this, OdometerActivity.class);
                        startActivity(intent);
                }
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.show(getFragmentManager(),  "");
                Log.i(ACTIVITY_NAME, "User clicked Info Icon");
            }
        });

    }

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

    public static class AutomobileInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage("Welcome to the Automobile page!\n" +
                    "Clicking on any of the items in the list\n" +
                    "brings you to their respective page.\n\n" +
                    "" +
                    "Use the back button to go back\n\n" +
                    "" +
                    "Scott C.")

                    .setNeutralButton("close dialog", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }//end of WelcomeInfoDialogFragment
}
