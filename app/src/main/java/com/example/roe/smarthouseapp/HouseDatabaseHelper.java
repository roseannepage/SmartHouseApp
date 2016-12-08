package com.example.roe.smarthouseapp;

/**
 * Created by Narges on 2016-12-04.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HouseDatabaseHelper extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "HouseSettings.db";
    private static int VERSION_NUM = 1;
    public final static String KEY_ID="ID";
    public final static String KEY_ITEM= "ITEMS";
    public final static String TABLE_NAME="HouseSettingItemsTable";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NAME + "( "
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_ITEM + " text not null"+");";

    /* Write a constructor that opens a database file “Chats.db” */
    public HouseDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Create a table with a column for id of integers that autoincrement, and a column for MESSAGE as strings */
        db.execSQL(DATABASE_CREATE);
        Log.i("HouseDatabaseHelper", "Calling onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.i("HouseDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion+ " newVersion=" + newVersion);
    }
}