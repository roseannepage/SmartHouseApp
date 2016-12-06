package com.example.roe.smarthouseapp;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class lr_lamp2_activity extends Fragment {

    protected static final String ACTIVITY_NAME = "Lr _lamp2_Activity";
    SeekBar slider;
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
    public static void lr_lamp2_activity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();

        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_lr_lamp2_activity, container, false);



        slider = (SeekBar)rootView.findViewById(R.id.seekBar);

        slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int levels = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                levels = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast toast;
                String text;
                int duration;

                text = "Lamp is now at level " + levels;
                duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(getActivity(), text, duration);
                toast.show();



                Log.i(ACTIVITY_NAME, "User changed lamp2 dimmer");
            }

        });

        return rootView;
    }
}
