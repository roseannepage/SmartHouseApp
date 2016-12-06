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
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.TextView;

import java.util.ArrayList;


import static com.example.roe.smarthouseapp.R.id.listviewlr;
import static com.example.roe.smarthouseapp.R.id.message_text;


public class LivingRoomActivity extends AppCompatActivity {

    protected static final String ACTIVITY_NAME = "LivingRoomActivity";
    LivingRoomInfoDialogFragment infoDialog;
    ImageButton plusBtn, minusBtn, infoBtn;
    ListView lrList;
    ArrayList<String> array = new ArrayList<String>();
    protected SQLiteDatabase db;
    ListAdapter messageAdapter;
    Cursor results1;
    boolean istablet;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room);

        plusBtn = (ImageButton) findViewById(R.id.lrPlusBtn);
        minusBtn = (ImageButton) findViewById(R.id.lrMinusBtn);
        infoBtn = (ImageButton) findViewById(R.id.lrInfoBtn);
        infoDialog = new LivingRoomInfoDialogFragment();
        lrList = (ListView)findViewById(R.id.listviewlr);

        if(findViewById(R.id.frameid) != null){
            istablet = true;
        }else{
            istablet = false;
        }


        messageAdapter = new ListAdapter( this );
        lrList.setAdapter (messageAdapter);
        lr_databaseHelper dbHelper = new lr_databaseHelper( this );
        db = dbHelper.getWritableDatabase();
        results1 = db.rawQuery("Select * from LIVINGROOM", null);
        results1.moveToFirst();


        while (!results1.isAfterLast()) {
            array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:”" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

            for(int i =0; i < results1.getColumnCount(); i++){
                results1.getColumnName(i);
            }

            results1.moveToNext(); //move the cursor to the next row
        }

        if(array.size()<=5){
            minusBtn.setClickable(false);
            minusBtn.setAlpha(.5f);
            plusBtn.setClickable(true);
            plusBtn.setAlpha(1f);
        }else {
            plusBtn.setClickable(false);
            plusBtn.setAlpha(.5f);
            minusBtn.setClickable(true);
            minusBtn.setAlpha(1f);
        }

        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateDialog();
                Log.i(ACTIVITY_NAME, "User clicked Plus Button");
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSQL = "DELETE FROM LIVINGROOM WHERE ID = 6";

                db.execSQL(strSQL);

                minusBtn.setClickable(false);
                minusBtn.setAlpha(.5f);
                plusBtn.setClickable(true);
                plusBtn.setAlpha(1f);
                array.clear();
                lrList.clearChoices();
                results1 = db.rawQuery("Select * from LIVINGROOM", null);
                results1.moveToFirst();


                while (!results1.isAfterLast()) {
                    array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:”" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()

        Log.i(ACTIVITY_NAME, "User clicked Minus Button");
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

    private class ListAdapter extends ArrayAdapter<String> {


        public ListAdapter(Context ctx) {

            super(ctx, 0);
        }

        public int getCount() {
            return array.size();
        }

        public String getItem(int position){
            return array.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = LivingRoomActivity.this.getLayoutInflater();
            View result = null ;

                result = inflater.inflate(R.layout.lr_list_row, null);

            TextView message = (TextView)result.findViewById(message_text);
            message.setText(   getItem(position)  ); // get the string at position

            if(message.getText().toString().equalsIgnoreCase("Smart tv")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "tv");
                        if(istablet){

                            lr_tv_activity fragment = new lr_tv_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentholder, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_tv_activity.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }
            if(message.getText().toString().equalsIgnoreCase("Smart lamp")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "Lamp1");
                        if(istablet){

                            lr_lamp1_activity fragment = new lr_lamp1_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentholder, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_lamp1_activity.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }

            if(message.getText().toString().equalsIgnoreCase("Smart lamp dimmer")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "Lamp2");
                        if(istablet){

                            lr_lamp2_activity fragment = new lr_lamp2_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.fragmentholder, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_lamp2_activity.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }
            return result;

        }
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
    }

    public void onCreateDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogview = inflater.inflate(R.layout.tv_customdialog, null);

        builder.setView(dialogview);

        final android.app.AlertDialog b = builder.create();
        Button button1 = (Button)dialogview.findViewById(R.id.button1);
        Button button2 = (Button)dialogview.findViewById(R.id.button2);
        Button button3 = (Button)dialogview.findViewById(R.id.button3);
        Button button4 = (Button)dialogview.findViewById(R.id.button4);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart tv', 0);" ;

                db.execSQL(strSQL);
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
                plusBtn.setClickable(false);
                plusBtn.setAlpha(.5f);
                minusBtn.setClickable(true);
                minusBtn.setAlpha(1f);
                b.dismiss();
                array.clear();
                results1 = db.rawQuery("Select * from LIVINGROOM", null);
                results1.moveToFirst();


                while (!results1.isAfterLast()) {
                    array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:”" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String strSQL = "UPDATE LIVINGROOM SET MESSAGE = 'Smart lamp' WHERE ID = 6";
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart lamp', 0);" ;

                db.execSQL(strSQL);

                plusBtn.setClickable(false);
                plusBtn.setAlpha(.5f);
                minusBtn.setClickable(true);
                minusBtn.setAlpha(1f);
                b.dismiss();
                array.clear();
                results1 = db.rawQuery("Select * from LIVINGROOM", null);
                results1.moveToFirst();


                while (!results1.isAfterLast()) {
                    array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:”" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String strSQL = "UPDATE LIVINGROOM SET MESSAGE = 'Smart lamp' WHERE ID = 6";
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart lamp dimmer', 0);" ;

                db.execSQL(strSQL);

                plusBtn.setClickable(false);
                plusBtn.setAlpha(.5f);
                minusBtn.setClickable(true);
                minusBtn.setAlpha(1f);
                b.dismiss();
                array.clear();
                results1 = db.rawQuery("Select * from LIVINGROOM", null);
                results1.moveToFirst();


                while (!results1.isAfterLast()) {
                    array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:”" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        b.show();
    }

}
