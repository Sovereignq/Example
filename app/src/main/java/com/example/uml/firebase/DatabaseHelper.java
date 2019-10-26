package com.example.uml.firebase;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;

import com.example.uml.activity.MainActivity;

import static android.content.ContentValues.TAG;
import static androidx.core.content.PermissionChecker.checkSelfPermission;
import static com.example.uml.firebase.User.*;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Users_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create Users table
        //db.execSQL(User.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Upgrading database


    public long insertUser(User user) {
        // get writable database as we want to write data
        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_NAME, (user.getUser(user.getId())).getname());
        values.put(User.COLUMN_TELEGRAMNICKNAME, (user.getUser(user.getId())).getTelegramNickname());
        values.put(User.COLUMN_EMAIL, (user.getUser(user.getId())).getEmail());
        values.put(User.COLUMN_TELEPHONE, (user.getUser(user.getId())).getTelephone());

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }


    public List<User> getAllUsers() {
        List<User> Users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME + " ORDER BY " +
                User.COLUMN_EMAIL + " DESC";

        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User User = new User();
                User.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
                User.setTelegramNickname(cursor.getString(cursor.getColumnIndex(User.COLUMN_TELEGRAMNICKNAME)));
                User.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));

                Users.add(User);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return Users list
        return Users;
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + User.TABLE_NAME;
        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateUser(User user) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);

        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, (user.getUser(user.getId())).getname());
        values.put(User.COLUMN_TELEGRAMNICKNAME, (user.getUser(user.getId())).getTelegramNickname());
        values.put(User.COLUMN_EMAIL, (user.getUser(user.getId())).getEmail());
        values.put(User.COLUMN_TELEPHONE, (user.getUser(user.getId())).getTelephone());

        // updating row
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    public void deleteUser(User User) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);
        db.delete(User.TABLE_NAME, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(User.getId())});
        db.close();
    }
}