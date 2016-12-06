package com.example.roe.smarthouseapp;

import android.app.Activity;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.ToggleButton;



public class lr_lamp1_activity extends Fragment {

    protected static final String ACTIVITY_NAME = "Lr _lamp1_Activity";
    ToggleButton lamp1OnOffTggl;
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
    public static void lr_lamp1_activity() {

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
        View rootView = inflater.inflate(R.layout.activity_lr_lamp1_activity, container, false);

        lamp1OnOffTggl = (ToggleButton) rootView.findViewById(R.id.toggleButton);

        lamp1OnOffTggl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast;
                String text;
                int duration;

                text = "Lamp is now " + lamp1OnOffTggl.getText();
                duration = Toast.LENGTH_SHORT;
                toast = Toast.makeText(getActivity(), text, duration);
                toast.show();



                Log.i(ACTIVITY_NAME, "User clicked lamp toggle");
            }
        });

        return rootView;
    }
}
