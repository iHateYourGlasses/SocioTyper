package ru.npotpz.sociotyper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Art on 26.01.2017.
 */

class DBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "sociopermdata.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getReininData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT *  FROM reinin_signs", null);
        return res;
    }

}