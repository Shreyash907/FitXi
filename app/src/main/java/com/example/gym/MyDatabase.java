package com.example.gym;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String U_ID = "u_id";
    private static final String U_NAME = "u_name";
    private static final String U_CONTACT = "u_contact";
    private static final String U_PASSWORD = "u_password";
    private static final String U_AGE = "u_age";
    private static final String U_HEIGHT = "u_height";
    private static final String U_WEIGHT = "u_weight";
    private static final String U_GENDER = "u_gender";
    public MyDatabase(@Nullable Context context) {
        super(context, "fitXi.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE UserDetails (u_name VARCHAR(255), u_contact VARCHAR(15) PRIMARY KEY," +
                "u_password VARCHAR(255), u_age VARCHAR(15), u_height VARCHAR(15), " +
                "u_weight VARCHAR(15) , u_gender VARCHAR(15))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists UserDetails");
        onCreate(db);
    }

    public boolean addUser(String u_name, String u_contact, String u_password, String u_age,
                        String u_height, String u_weight, String u_gender)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(U_NAME, u_name);
        values.put(U_CONTACT, u_contact);
        values.put(U_PASSWORD, u_password);
        values.put(U_AGE, u_age);
        values.put(U_HEIGHT, u_height);
        values.put(U_WEIGHT, u_weight);
        values.put(U_GENDER, u_gender);

        long result = db.insert("UserDetails", null, values);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean checkUser(String u_name, String u_contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from UserDetails where u_name = ? and u_contact = ?", new String[]{u_name, u_contact});

        if(cursor.getCount() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
