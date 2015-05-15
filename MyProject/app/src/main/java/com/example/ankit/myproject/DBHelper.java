package com.example.ankit.myproject;

/**
 * Created by Ankit on 15-05-2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String MESSAGES_TABLE_NAME = "MESSAGES";
    public static final String MESSAGES_ID = "MESSAGE_ID";
    public static final String MESSAGES_TYPE = "MESSAGE_TYPE";
    public static final String MESSAGES_DATA = "MESSAGE_DATA";
    public static final String MESSAGES_TIMESTAMP = "MESSAGE_TIMESTAMP";

    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table " + MESSAGES_TABLE_NAME + " " + "(" + MESSAGES_ID + " string primary key, " + MESSAGES_TYPE + " text, " + MESSAGES_DATA + " text, " + MESSAGES_TIMESTAMP + " text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + MESSAGES_TABLE_NAME);
        onCreate(db);
    }


    public boolean insertMessage(String id, String type, String data, String timestamp)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = getMessage(id);
        if (c == null) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(MESSAGES_ID, id);
            contentValues.put(MESSAGES_DATA, data);
            contentValues.put(MESSAGES_TIMESTAMP, timestamp);
            contentValues.put(MESSAGES_TYPE, type);

            db.insert(MESSAGES_TABLE_NAME, null, contentValues);
        }
        return true;
    }
    public Cursor getMessage(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + MESSAGES_TABLE_NAME + " where MESSAGE_ID=" + id + "", null);
        return res;
    }
    public Cursor getAllMessage(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("select * from " + MESSAGES_TABLE_NAME , null);
        return res;
    }


}

