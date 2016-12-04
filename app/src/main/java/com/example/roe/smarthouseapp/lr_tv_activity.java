package com.example.roe.smarthouseapp;


import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.support.design.widget.Snackbar;

public class lr_tv_activity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LivingRoom _TV_Activity";
    ToggleButton tvOnOffTggl;
    EditText channelEditText;
    ImageButton channelUpBtn, channelDwnBtn, volumeUpBtn, volDwnBtn, infoBtn;
    ImageView enterBtn;
    int volume;
    int channel;
    TVInfoDialogFragment infoDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr_tv_activity);

        tvOnOffTggl = (ToggleButton) findViewById(R.id.toggleButton);
        channelEditText = (EditText) findViewById(R.id.channeledittext);
        enterBtn = (ImageView) findViewById(R.id.imageView);
        channelUpBtn = (ImageButton) findViewById(R.id.imageButton2);
        channelDwnBtn = (ImageButton) findViewById(R.id.imageButton3);
        volumeUpBtn = (ImageButton) findViewById(R.id.imageButton5);
        volDwnBtn = (ImageButton) findViewById(R.id.imageButton4);
        volume = 10;
        channel = 00;
        infoBtn = (ImageButton) findViewById(R.id.TVInfoBtn);
        infoDialog = new TVInfoDialogFragment();

        tvOnOffTggl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String text;
                int duration;

                text = "Tv is now " + tvOnOffTggl.getText();
                duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();



                Log.i(ACTIVITY_NAME, "User clicked TV toggle");
            }
        });

        enterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String text;
                int duration;

                text = "Tv channel changed to " + channelEditText.getText();
                duration = Toast.LENGTH_LONG;
                toast = Toast.makeText(getApplicationContext(), text, duration);
                toast.show();



                Log.i(ACTIVITY_NAME, "User clicked TV enter button");
            }
        });

        volumeUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(volume< 20){
                    volume ++;
                    Snackbar snackbar = Snackbar
                    .make(findViewById(android.R.id.content), "Volume at level " + volume, Snackbar.LENGTH_LONG);

                    snackbar.show();

                }

                if(volume==20){

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Volume at max level 20", Snackbar.LENGTH_LONG);

                    snackbar.show();

                }
            }
        });

        volDwnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(volume > 0){
                    volume --;
                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Volume at level " + volume, Snackbar.LENGTH_LONG);

                    snackbar.show();

                }

                if(volume==0){

                    Snackbar snackbar = Snackbar
                            .make(findViewById(android.R.id.content), "Volume muted", Snackbar.LENGTH_LONG);

                    snackbar.show();

                }
            }
        });

        channelUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(channel<999) {
                    channel++;
                    channelEditText.setText(String.valueOf(channel));

                }
                if(channel == 999){
                    channel = 00;
                    channelEditText.setText("00");
                }
            }
        });

        channelDwnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(channel>00) {
                    channel--;
                    channelEditText.setText(String.valueOf(channel));

                }
                if(channel == 00){
                    channel = 999;
                    channelEditText.setText("999");
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

    public static class TVInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage("TV Activity created by Roseanne Page. \n\n\n" +
                    "Clicking on the Toggle button turns the TV on/off.\n\n" +
                    "Clicking on the TV channel number allows you to change it. You must then press the enter button change the channel on the TV.\n\n" +
                    "Clicking on the Channel up and down buttons allow you to move through the channels one channel at a time. No need to press the enter button for these.\n\n" +
                    "Clicking on the volume + and - buttons allows you to change the TV volume. 0 is mute and 20 is max.")

                    .setNeutralButton("close dialog", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }
}
