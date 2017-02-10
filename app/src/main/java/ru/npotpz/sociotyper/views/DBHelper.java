package ru.npotpz.sociotyper.views;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Art on 26.01.2017.
 */

public class DBHelper extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "sociopermdata.db";
    private static final int DATABASE_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor getReininData(String type){
        SQLiteDatabase db = this.getWritableDatabase();

        String request = "SELECT reinin_signs.name,\n" +
                " reinin_signs.bind,\n" +
                "    sum(CASE WHEN types.type_name = '"+type+"' THEN\n" +
                "        1\n" +
                "    ELSE\n" +
                "        0 \n" +
                "    END)as `is_type`\n" +
                "   FROM reinin_signs\n" +
                "LEFT JOIN `reinin_data` on (`reinin_signs`.`name` = `reinin_data`.`reinin_pick`)\n" +
                "LEFT JOIN `types` on (`reinin_data`.`type_id` = `types`.`id`)\n" +
                "group by reinin_signs.name\n" +
                "order by reinin_signs.bind";

        Cursor res = db.rawQuery(request, null);
        return res;
    }

    public Cursor getTypes(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT type_name FROM types", null);
        return res;
    }

}