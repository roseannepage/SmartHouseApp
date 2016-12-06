package com.example.roe.smarthouseapp;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class lr_fragmentholder extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr_fragmentholder);




        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            String origin = getIntent().getStringExtra("Origin");

            Bundle arguments = new Bundle();
            if(origin.equalsIgnoreCase("lamp2")){
                arguments.putString(lr_lamp2_activity.ARG_ITEM_ID,
                        getIntent().getStringExtra(lr_lamp2_activity.ARG_ITEM_ID));
                Fragment fragment = new lr_lamp2_activity();
                fragment.setArguments(arguments);//check origin
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentholder, fragment)
                        .commit();
            }
            if(origin.equalsIgnoreCase("lamp1")){
                arguments.putString(lr_lamp2_activity.ARG_ITEM_ID,
                        getIntent().getStringExtra(lr_lamp2_activity.ARG_ITEM_ID));
                Fragment fragment = new lr_lamp2_activity();
                fragment.setArguments(arguments);//check origin
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentholder, fragment)
                        .commit();
            }
            if(origin.equalsIgnoreCase("tv")){
                arguments.putString(lr_lamp2_activity.ARG_ITEM_ID,
                        getIntent().getStringExtra(lr_lamp2_activity.ARG_ITEM_ID));
                Fragment fragment = new lr_tv_activity();
                fragment.setArguments(arguments);//check origin
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentholder, fragment)
                        .commit();
            }
            if(origin.equalsIgnoreCase("lamp3")){
                arguments.putString(lr_lamp3_activity.ARG_ITEM_ID,
                        getIntent().getStringExtra(lr_lamp3_activity.ARG_ITEM_ID));
                Fragment fragment = new lr_lamp3_activity();
                fragment.setArguments(arguments);//check origin
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentholder, fragment)
                        .commit();
            }
            if(origin.equalsIgnoreCase("window")){
                arguments.putString(lr_blinds_activity.ARG_ITEM_ID,
                        getIntent().getStringExtra(lr_blinds_activity.ARG_ITEM_ID));
                Fragment fragment = new lr_blinds_activity();
                fragment.setArguments(arguments);//check origin
                getFragmentManager().beginTransaction()
                        .add(R.id.fragmentholder, fragment)
                        .commit();
            }


        }
    }


}
