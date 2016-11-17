package com.example.roe.smarthouseapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class LivingRoomActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LivingRoomActivity";
    LivingRoomInfoDialogFragment infoDialog;
    ImageButton compasBtn, plusBtn, minusBtn, infoBtn;
    ListView lrList;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        compasBtn = (ImageButton) findViewById(R.id.lrCompasBtn);
        plusBtn = (ImageButton) findViewById(R.id.lrPlusBtn);
        minusBtn = (ImageButton) findViewById(R.id.lrMinusBtn);
        infoBtn = (ImageButton) findViewById(R.id.lrInfoBtn);
        infoDialog = new LivingRoomInfoDialogFragment();
        lrList = (ListView)findViewById(R.id.listviewlr) ;



        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.show(getFragmentManager(),  "");
                Log.i(ACTIVITY_NAME, "User clicked Info Icon");
            }
        });

        compasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    public static class LivingRoomInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage("Living Room Activity created by Roseanne Page. \n\n\n" +
                    "Clicking on the compas icon brings you back to the welcome page.\n\n" +
                    "Clicking on the + icon allows you to add an item to the list.\n\n" +
                    "Clicking on the - icon allows you to remove an item from the list.\n\n" +
                    "Clicking on a listed item brings you to a more detailled view and options for said item.")

                    .setNeutralButton("close dialog", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }//end of WelcomeInfoDialogFragment

}
