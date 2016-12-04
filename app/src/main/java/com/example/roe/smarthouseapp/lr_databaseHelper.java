package com.example.roe.smarthouseapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class lr_databaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "LIVINGROOM";
    private static final String DATABASE_NAME = "LIVINGROOM.db";
    private static final int VERSION_NUM = 4;



    public lr_databaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL("CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, MESSAGE TEXT, USED INTEGER);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Smart tv', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Smart lamp', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Smart lamp dimmer', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Smart lamp color dimmer', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Smart window', 0);"  );
        database.execSQL("INSERT INTO " + TABLE_NAME + " (MESSAGE, USED) VALUES ('Nothing assigned', 0);"  );
        Log.i("ChatDatabaseHelper", "Calling onCreate");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
    }

}
