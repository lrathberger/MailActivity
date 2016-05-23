package com.example.lukas.mailanalyse;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lukas on 23.05.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME ="mail.db ";
    public static final int DB_VERSION=1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tbl_contact.SQL_CREATE);
        db.execSQL(tbl_mail.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
