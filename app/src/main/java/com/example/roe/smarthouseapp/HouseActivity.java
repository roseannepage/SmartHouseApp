package com.example.roe.smarthouseapp;

/**
 * Created by Narges on 2016-11-24.
 */

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class HouseActivity extends AppCompatActivity {
    private static final int ACTIVITY_0 = 0;
    private static final int ACTIVITY_1 = 1;
    private static final int ACTIVITY_2 = 2;
    protected static final String ACTIVITY_NAME = "HouseActivity";
    //ImageButton goBackButton;
    ImageButton addButton;
    ImageButton deleteButton;
    ImageButton infoButton;
    HouseInfoDialogFragment infoDialog;


    ArrayAdapter<String> adapter;       //
    EditText editText;
    ArrayList<String> itemList;     //
    String[] items;
    String itemSelected;

    // HouseAdapter messageAdapter;

    ArrayList<String> list;

    ArrayList<String> arrayList2;
    ListView listView2;
    SQLiteDatabase database2;
    ArrayAdapter<String> listViewAdapter2;
    ImageButton addButton2;
    ////////////////////////////////////////////////////
    ListView houseListView;
    ArrayList<String> values;
    ContentValues cValues;
    HouseDatabaseHelper helper;
    SQLiteDatabase myDB;


    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_house);



       /* goBackButton = (ImageButton) findViewById(R.id.houseBackButton);  */

      /*  deleteButton = (ImageButton) findViewById(R.id.houseDeleteButton);*/
        infoButton = (ImageButton) findViewById(R.id.houseInfoButton);
        infoDialog = new HouseInfoDialogFragment();

        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.show(getFragmentManager(), "");
                Log.i(ACTIVITY_NAME, "InfoButton clicked!");
            }
        });

      /*  goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(ACTIVITY_NAME, "Exiting...");
                finish();
            }
        });  */

// storing string resources into Array
        // String[] items={"Garage","House Temperature","Outside Temperature"};
        items = getResources().getStringArray(R.array.ItemsInHouseSetting);

        // Binding resources Array to ListAdapter
        //  this.setlistadapter(new ArrayAdapter<String>(this, R.layout.house_list_item, R.id.txtview, items));

        itemList = new ArrayList<String>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.house_list_item, R.id.txtview, itemList);

        houseListView = (ListView) findViewById(R.id.houseListView);
        houseListView.setAdapter(adapter);



//adding to the listview
        editText = (EditText) findViewById(R.id.txtInput);
        addButton = (ImageButton) findViewById(R.id.houseAddButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newItem = editText.getText().toString();
                // add new item to arraylist
                itemList.add(newItem);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });





        // listening to single list item on click
        houseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // selected item
                itemSelected = (String) (houseListView.getItemAtPosition(position));
                Log.i(ACTIVITY_NAME, itemSelected);
                // Launching new Activity on selecting single List Item
                //  Intent i = new Intent(getApplicationContext(), SingleListItem.class);

                // sending data to new activity
                //   i.putExtra("Item", itemSelected);
                //   startActivity(i);

                int duration = Toast.LENGTH_LONG;
                Toast toast = Toast.makeText(getApplicationContext(), itemSelected + " selected ", duration); //this is the ListActivity
                toast.show();

                if (position == 0) {
                    //code specific to first list item
                    //  Intent myIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                    Log.i(ACTIVITY_NAME, "TEST OBJECT 0");
                    Intent intent = new Intent(HouseActivity.this, ActivityGarage.class);
                    startActivity(intent);
                }
                if (position == 1) {
                    Log.i(ACTIVITY_NAME, "TEST OBJECT 1");
                    Intent intent = new Intent(getApplicationContext(), HouseTempActivity.class);
                    // sending data to new activity
                    //  i.putExtra("Item", itemSelected);
                    startActivity(intent);
                }
                if (position == 2) {
                    //  Intent myIntent = new Intent(getApplicationContext(), WeatherForecast.class);
                    Log.i(ACTIVITY_NAME, "TESE OBJECT 2");
                    //  myIntent.putExtra("Item", itemSelected);
                    //  startActivity(myIntent);
                    Intent intent = new Intent(HouseActivity.this, WeatherForecast.class);
                    startActivity(intent);
                }
            }
        });

      /*  deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // add new item to arraylist
                itemList.remove(itemSelected);
                // notify listview of data changed
                adapter.notifyDataSetChanged();
            }

        });   */

        // Pass the item position as the requestCode parameter, so on the `Activity`'s
        // return you can examine it, and determine which activity were you in prior.
        //   startActivityForResult(intent, position);


     /*   livingRoomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LivingRoomActivity.class);
                startActivity(intent);
                Log.i(ACTIVITY_NAME, "User clicked Living Room Icon");
            }
        });  */

// Set what happens when a list view item is clicked
  /*   houseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override


                TextView clickedView = (TextView) view;
                Toast.makeText(getApplicationContext(), "selected Item Name is " + clickedView.getText(), Toast.LENGTH_LONG).show();
            }

        });
*/

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


    public static class HouseInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

            alertDialog.setMessage("Welcome to House Settings Activities!+" +
                    "\nPlease click on any of the items in the list to go to the selected activity." +
                    "\nYou can add additional items to the list and remove those items." +
                    "\n\nThis page is written by Narges Karimi Tabar.")

                    .setNeutralButton("Exit Info", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertDialog.create();
        }
    }//end of WelcomeInfoDialogFragment


}


