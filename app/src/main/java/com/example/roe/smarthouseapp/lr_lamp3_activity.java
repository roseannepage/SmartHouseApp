package com.example.roe.smarthouseapp;

import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


public class lr_lamp3_activity extends Fragment {

    protected static final String ACTIVITY_NAME = "Lr _lamp3_Activity";
    SeekBar slider;
    Spinner dropDwn;

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
    public static void lr_lamp3_activity() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_lr_lamp3_activity, container, false);





        slider = (SeekBar) rootView.findViewById(R.id.seekBar);
        dropDwn = (Spinner) rootView.findViewById(R.id.dropDown);



        dropDwn.setOnItemSelectedListener(new DropDownListener() {

        });
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


                Log.i(ACTIVITY_NAME, "User changed lamp3 dimmer");
            }

        });

        return rootView;
    }

    public class DropDownListener implements OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    getText(R.string.colour) + " " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }


    }


}
