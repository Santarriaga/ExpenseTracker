package com.example.expensetracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "invoices";

    public DatabaseHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: attempting to create database");
        String sqlStatement = "CREATE TABLE invoices(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "value TEXT," +
                "date TEXT," +
                "iconName TEXT," +
                "isExpense INTEGER);";
        sqLiteDatabase.execSQL(sqlStatement);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insert(SQLiteDatabase db, Invoice invoice){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", invoice.getTitle());
        contentValues.put("value", invoice.getValue());
        contentValues.put("date", invoice.getDate());
        contentValues.put("iconName", invoice.getIconName());

        if(invoice.isExpense()){
            contentValues.put("isExpense", 1);
        }
        else {
            contentValues.put("isExpense", -1);
        }

        db.insert("invoices", null, contentValues);
    }

    public void delete(SQLiteDatabase database, int bookId){
        database.delete("invoices", "id=?", new String[]{String.valueOf(bookId)});
    }


}
