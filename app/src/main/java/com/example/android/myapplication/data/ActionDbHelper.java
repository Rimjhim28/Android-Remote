package com.example.android.myapplication.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 10-09-2017.
 */

public class ActionDbHelper  extends SQLiteOpenHelper {

    private  static final String LOG_TAG = ActionDbHelper.class.getName();

    private  static final String DATA_BASE_NAME = "remote.db";

    private  static final int DATA_BASE_VERSION = 1;


    public ActionDbHelper(Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ACTIONS_TABLE =  "CREATE TABLE " + ActionContract.ActionEntry.TABLE_NAME + " ("
                + ActionContract.ActionEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ActionContract.ActionEntry.COLUMN_ACTION + " TEXT NOT NULL, "
                + ActionContract.ActionEntry.COLUMN_TIME + " TEXT NOT NULL);";
        db.execSQL(SQL_CREATE_ACTIONS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}