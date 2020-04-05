package com.hfad.loginactivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "Credentials";
    public static final int DB_VERSION = 1;
    DatabaseHelper(Context context){
        super(context, DB_NAME, null ,DB_VERSION);
    }
    public static void insertEntry(SQLiteDatabase db, String username, String password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("USERNAME",username);
        contentValues.put("PASSWORD",password);
        db.insert("CREDENTIALS",null,contentValues);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE CREDENTIALS (" +
                "   _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "   USERNAME TEXT," +
                "   PASSWORD TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion){

    }
}
