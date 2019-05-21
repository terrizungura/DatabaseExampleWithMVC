package com.example.ceta.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "people_tbl";
    private static final String TABLE_PROJ = "projects_tbl";
    private static final String COL1= "ID";
    private static final String COL2= "name";
    private static final String COL3= "surname";
    private static final String COL4= "cell";
    private static final String COL5="DOB";
    private static final String COL6= "age";
    private static final String KEY_PROJECT= "project";
    private static final String PROV= "province";




    public DatabaseHelper( Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COL2 + " TEXT, "+ COL3 + " TEXT," + COL4 + " NUMBER, "+ COL5 + " TEXT, "+COL6 + " NUMBER)";

        String createTownTable = "CREATE TABLE "
                + TABLE_PROJ + "(" + KEY_PROJECT + " TEXT,"+ COL1 + " INT,"+ PROV + " TEXT );";
        db.execSQL(createTable);
        db.execSQL(createTownTable);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        db.execSQL("DROP TABLE " + TABLE_PROJ);
        onCreate(db);
    }

    public boolean addData(String item1, String item2, int item3, String item4, int item5){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,item1);
        contentValues.put(COL3,item2);
        contentValues.put(String.valueOf(COL4),item3);
        contentValues.put(COL5,item4);
        contentValues.put(String.valueOf(COL6),item5);

        Log.d(TAG, "addData: Adding " + item1 + ","+ item2 + ","+ item3 + ","+ item4 + ","+ item5);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean addProjects(String item1, int item2, String item3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_PROJECT,item1);
        contentValues.put(String.valueOf(COL1),item2);
        contentValues.put(PROV,item3);

        Log.d(TAG, "addProjects: Adding " + item1 + ","+ item2 + ","+ item3 );
        long result = db.insert(TABLE_PROJ, null, contentValues);

        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String qryStat = "Select * FROM "+ TABLE_NAME + " INNER JOIN " + TABLE_PROJ + " ON "
                + TABLE_NAME +"."+ COL1 + " = " + TABLE_PROJ +"." + COL1 + ";";
        Cursor data = db.rawQuery(qryStat, null);
        return data;
    }
}
