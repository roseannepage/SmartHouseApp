package com.example.roe.smarthouseapp;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "MainActivity";
    Toolbar toolBar;
    ImageButton livingRoomBtn, kitchenBtn, houseBtn, autoBtn, infoBtn;
    WelcomeInfoDialogFragment welcomeInfoDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up the toolbar
        toolBar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        livingRoomBtn = (ImageButton)findViewById(R.id.toLivingRoomButton);
        kitchenBtn = (ImageButton)findViewById(R.id.toKitchenButton);
        houseBtn = (ImageButton)findViewById(R.id.toHouseButton);
        autoBtn = (ImageButton)findViewById(R.id.toAutomobileButton);
        infoBtn = (ImageButton)findViewById(R.id.infoButton);
        welcomeInfoDialog = new WelcomeInfoDialogFragment();

        livingRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LivingRoomActivity.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked Living Room Icon");
            }
        });

        kitchenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KitchenActivity.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked Kitchen Icon");
            }
        });

        houseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HouseActivity.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked House Icon");
            }
        });

        autoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AutomobileActivity.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked Automobile Icon");
            }
        });

        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                welcomeInfoDialog.show(getFragmentManager(),  "");
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

    public static class WelcomeInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage(R.string.maininfo)

                    .setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }//end of WelcomeInfoDialogFragment
}
