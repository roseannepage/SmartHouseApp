package com.example.roe.smarthouseapp;


import android.app.Activity;
import android.os.Bundle;

import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.support.design.widget.Snackbar;

public class lr_tv_activity extends Fragment {

    protected static final String ACTIVITY_NAME = "LivingRoom _TV_Activity";
    ToggleButton tvOnOffTggl;
    EditText channelEditText;
    ImageButton channelUpBtn, channelDwnBtn, volumeUpBtn, volDwnBtn, infoBtn;
    ImageView enterBtn;
    int volume;
    int channel;


    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public static void lr_tv_activity() {

    }



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_lr_tv_activity, container, false);


        tvOnOffTggl = (ToggleButton) rootView.findViewById(R.id.toggleButton);
        channelEditText = (EditText) rootView.findViewById(R.id.channeledittext);
        enterBtn = (ImageView) rootView.findViewById(R.id.imageView);
        channelUpBtn = (ImageButton) rootView.findViewById(R.id.imageButton2);
        channelDwnBtn = (ImageButton) rootView.findViewById(R.id.imageButton3);
        volumeUpBtn = (ImageButton) rootView.findViewById(R.id.imageButton5);
        volDwnBtn = (ImageButton) rootView.findViewById(R.id.imageButton4);
        volume = 10;
        channel = 00;



        tvOnOffTggl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String text;
                int duration;

                text = "Tv is now " + tvOnOffTggl.getText();
                duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(getActivity(), text, duration);
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
                toast = Toast.makeText(getActivity(), text, duration);
                toast.show();
                channel= Integer.parseInt(channelEditText.getText().toString());



                Log.i(ACTIVITY_NAME, "User clicked TV enter button");
            }
        });

        volumeUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(volume< 20){
                    volume ++;
                    Snackbar snackbar = Snackbar
                    .make(getActivity().findViewById(android.R.id.content), "Volume at level " + volume, Snackbar.LENGTH_LONG);

                    snackbar.show();

                }

                if(volume==20){

                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), "Volume at max level 20", Snackbar.LENGTH_LONG);

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
                            .make(getActivity().findViewById(android.R.id.content), "Volume at level " + volume, Snackbar.LENGTH_LONG);

                    snackbar.show();

                }

                if(volume==0){

                    Snackbar snackbar = Snackbar
                            .make(getActivity().findViewById(android.R.id.content), "Volume muted", Snackbar.LENGTH_LONG);

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


        return rootView;
    }



    @Override
    public void onResume() {
        super.onResume();
        Log.i(ACTIVITY_NAME, "In onResume()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(ACTIVITY_NAME, "In onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(ACTIVITY_NAME, "In onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(ACTIVITY_NAME, "In onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }


}
