package com.example.roe.smarthouseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class lr_databaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "LIVINGROOM";
    private static final String DATABASE_NAME = "LIVINGROOM.db";
    private static final int VERSION_NUM = 9;



    public lr_databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY, MESSAGE TEXT, USED INTEGER);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (ID, MESSAGE, USED) VALUES (1, 'Smart tv', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (ID, MESSAGE, USED) VALUES (2, 'Smart lamp', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (ID, MESSAGE, USED) VALUES (3, 'Smart lamp dimmer', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (ID, MESSAGE, USED) VALUES (4,'Smart lamp color dimmer', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (ID, MESSAGE, USED) VALUES (5,'Smart window', 0);"  );

        Log.i("ChatDatabaseHelper", "Calling onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

}
