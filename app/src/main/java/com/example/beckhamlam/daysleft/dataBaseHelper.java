package com.example.beckhamlam.daysleft;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dataBaseHelper extends SQLiteOpenHelper {

    private static String TABLE_NAME = "events_table";
    private static String COL1 = "item_id";
    private static String COL2 = "formattedDate";

    public dataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " TEXT PRIMARY KEY," +
                COL2 + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String formattedDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, formattedDate);

        //Log.d(TAG, "addDate: adding " + item + "to" + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor data = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;

    }
}
