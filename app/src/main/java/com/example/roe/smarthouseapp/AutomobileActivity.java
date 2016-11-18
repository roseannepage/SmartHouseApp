package com.example.roe.smarthouseapp;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class AutomobileActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "AutomobileActivity";
    AutomobileActivity.AutomobileInfoDialogFragment infoDialog;
    ImageButton compasBtn, infoBtn;
    ListView lrList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_automobile);

        compasBtn = (ImageButton) findViewById(R.id.lrCompasBtn);
        infoBtn = (ImageButton) findViewById(R.id.lrInfoBtn);
        infoDialog = new AutomobileActivity.AutomobileInfoDialogFragment();
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

    public static class AutomobileInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage("Roseanne: \"I made this\"\n" +
                                    "Scott:    \"You made this\"\n" +
                                    "Scott:     *looks around*\n" +
                                    "Scott:    \"I made this\"")

                    .setNeutralButton("close dialog", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }//end of WelcomeInfoDialogFragment
}
