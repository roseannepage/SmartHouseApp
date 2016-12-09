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
import android.app.Fragment;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.roe.smarthouseapp.R.id.message_text;

/**
 * living room activity. Populates a listview and allows you to add a signle new item or remove that item.
 * @author Roseanne Page
 */
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
    int list1, list2, list3, list4, list5, list6;


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


//populating the list
        messageAdapter = new ListAdapter( this );
        lrList.setAdapter (messageAdapter);
        lr_databaseHelper dbHelper = new lr_databaseHelper( this );
        db = dbHelper.getWritableDatabase();
        results1 = db.rawQuery("Select * from LIVINGROOM order by USED DESC", null);
        results1.moveToFirst();
        list6=0;

        while (!results1.isAfterLast()) {
            if(results1.getInt(results1.getColumnIndex("ID"))==1){
                list1 = results1.getInt(results1.getColumnIndex("USED"));
            }
            if(results1.getInt(results1.getColumnIndex("ID"))==2){
                list2 = results1.getInt(results1.getColumnIndex("USED"));
            }
            if(results1.getInt(results1.getColumnIndex("ID"))==3){
                list3 = results1.getInt(results1.getColumnIndex("USED"));
            }
            if(results1.getInt(results1.getColumnIndex("ID"))==4){
                list4 = results1.getInt(results1.getColumnIndex("USED"));
            }
            if(results1.getInt(results1.getColumnIndex("ID"))==5){
                list5 = results1.getInt(results1.getColumnIndex("USED"));
            }
            if(results1.getInt(results1.getColumnIndex("ID"))==6){
                list6 = results1.getInt(results1.getColumnIndex("USED"));
            }
            array.add(results1.getString( results1.getColumnIndex( "MESSAGE") ));
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
            Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

            for(int i =0; i < results1.getColumnCount(); i++){
                results1.getColumnName(i);
            }

            results1.moveToNext(); //move the cursor to the next row
        }

        //disabling or enabling the + - buttons
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

    /**
     * list adapter. checks what message is stored for the item being added and adds the right clicklistener for it
     */
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

            final TextView message = (TextView)result.findViewById(message_text);
            message.setText(   getItem(position)  ); // get the string at position

            if(message.getText().toString().contains("Smart tv")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        if(message.getText().toString().contains("2")){
                            list6 ++;

                        }else{
                            list1 ++;

                        }
                        Bundle arguments = new Bundle();
                        Context context = v.getContext();
                        arguments.putString("Origin", "tv");
                        if(istablet){

                            lr_tv_activity fragment = new lr_tv_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.lritemlist_detail_container, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_fragmentholder.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }
            if(message.getText().toString().equalsIgnoreCase("Smart lamp")|| message.getText().toString().equalsIgnoreCase("Smart lamp 2")) {

                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(message.getText().toString().contains("2")){
                            list6 ++;
                        }else{
                            list2 ++;
                        }

                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "lamp1");
                        if(istablet){

                            lr_lamp1_activity fragment = new lr_lamp1_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.lritemlist_detail_container, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_fragmentholder.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }

            if(message.getText().toString().contains("Smart lamp dimmer")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(message.getText().toString().contains("2")){
                            list6 ++;
                        }else{
                            list3 ++;
                        }

                        Bundle arguments = new Bundle();
                        Context context = v.getContext();
                        arguments.putString("Origin", "lamp2");
                        if(istablet){

                            lr_lamp2_activity fragment = new lr_lamp2_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.lritemlist_detail_container, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_fragmentholder.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }

            if(message.getText().toString().contains("Smart lamp color dimmer")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(message.getText().toString().contains("2")){
                            list6 ++;
                        }else{
                            list4 ++;
                        }

                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "lamp3");
                        if(istablet){

                            lr_lamp3_activity fragment = new lr_lamp3_activity();
                            fragment.setArguments(arguments);
                            getFragmentManager().beginTransaction()
                                    .replace(R.id.lritemlist_detail_container, fragment)
                                    .commit();
                        }else{

                            Intent intent = new Intent(context, lr_fragmentholder.class);
                            intent.putExtras(arguments);
                            context.startActivity(intent);
                        }
                    }
                });
            }

            if(message.getText().toString().contains("Smart window")) {
                result.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                            list5 ++;


                        Context context = v.getContext();
                        Bundle arguments = new Bundle();
                        arguments.putString("Origin", "window");
                         if(istablet){

                         lr_blinds_activity fragment = new lr_blinds_activity();
                         fragment.setArguments(arguments);
                         getFragmentManager().beginTransaction()
                         .replace(R.id.lritemlist_detail_container, fragment)
                         .commit();
                         }else{

                        Intent intent = new Intent(context, lr_fragmentholder.class);
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
        if(findViewById(R.id.frameid) != null){
            istablet = true;
        }else{
            istablet = false;
        }
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

    /**
     * on destroy updates the database with times the items where clicked on
     */
    @Override
    protected void onDestroy() {


        String strSQL = "UPDATE LIVINGROOM SET USED = " + list1 + " WHERE ID = 1";
        db.execSQL(strSQL);
        strSQL = "UPDATE LIVINGROOM SET USED = " + list2 + " WHERE ID = 2";
        db.execSQL(strSQL);
        strSQL = "UPDATE LIVINGROOM SET USED = " + list3 + " WHERE ID = 3";
        db.execSQL(strSQL);
        strSQL = "UPDATE LIVINGROOM SET USED = " + list4 + " WHERE ID = 4";
        db.execSQL(strSQL);
        strSQL = "UPDATE LIVINGROOM SET USED = " + list5 + " WHERE ID = 5";
        db.execSQL(strSQL);

        if(array.size()==6){
            strSQL = "UPDATE LIVINGROOM SET USED = " + list6 + " WHERE ID = 6";
            db.execSQL(strSQL);
        }



        super.onDestroy();
        Log.i(ACTIVITY_NAME, "In onDestroy()");
    }

    /**
     * dialog fragment for info button
     * @author Roseanne Page
     */
    public static class LivingRoomInfoDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
            alertBuilder.setMessage(R.string.livingroominfo)

                    .setNeutralButton("close dialog", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dismiss();
                        }
                    });

            return alertBuilder.create();
        }
    }

    /**
     * dialog for when you add a button
     * @author Roseanne Page
     */
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
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart tv 2', 0);" ;

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
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
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
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart lamp 2', 0);" ;

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
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
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
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart lamp dimmer 2', 0);" ;

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
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String strSQL = "UPDATE LIVINGROOM SET MESSAGE = 'Smart lamp' WHERE ID = 6";
                String strSQL = "INSERT INTO LIVINGROOM (ID, MESSAGE, USED) VALUES (6, 'Smart lamp color dimmer 2', 0);" ;

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
                    Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + results1.getString( results1.getColumnIndex( "MESSAGE") ) );
                    Log.i(ACTIVITY_NAME, "Cursor’s  column count =" + results1.getColumnCount() );

                    for(int i =0; i < results1.getColumnCount(); i++){
                        results1.getColumnName(i);
                    }

                    results1.moveToNext(); //move the cursor to the next row
                }
                messageAdapter.notifyDataSetChanged(); //this restarts the process of getCount()/ getView()
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        b.show();
    }

}
