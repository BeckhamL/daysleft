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
    private static String COL3 = "difference";
    //private static String[] columnNames = {COL1, COL2, COL3};

    public dataBaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL1 + " TEXT PRIMARY KEY," +
                COL2 + " TEXT," +
                COL3 + " Long)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String item, String formattedDate, long difference) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item);
        contentValues.put(COL2, formattedDate);
        contentValues.put(COL3, difference);

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

    public void deleteEvent(String event) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL1 + " = '" + event + "'";
        db.execSQL(query);
    }

    public void updateEvent(String newEvent, String oldEvent, String newFormattedDate, long difference) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + newEvent + "', " + COL2 + " = '" + newFormattedDate + "', " + COL3 + " = '" + difference + "' WHERE " + COL1 + " = '" + oldEvent + "';";
        sqLiteDatabase.execSQL(query);
    }
}
