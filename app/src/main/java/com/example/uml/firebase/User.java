package com.example.uml.firebase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class User {
    public static final String TABLE_NAME = "users";

    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TELEGRAMNICKNAME = "telegramNickname";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_TELEPHONE = "telephone";

    private int id;
    private String name;
    private String telegramNickname;
    private String email;
    private String telephone;
    public User(String name, String telegramNickname, String email, String telephone) {
        this.name = name;
        this.telegramNickname = telegramNickname;
        this.email = email;
        this.telephone = telephone;
    }

    public User(){

    }
    public String getname() {
        return name;
    }
    public int getId() {
        return id;
    }
    public String getTelephone(){
        return telephone;
    }
    public String setTelephone(String telephone){
        this.telephone = telephone;
        return this.telephone;
    }

    public String getTelegramNickname() {
        return telegramNickname;
    }

    public void setTelegramNickname(String telegramNickname) {
        this.telegramNickname = telegramNickname;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public long insertUser(User user) {
        // get writable database as we want to write data
        SQLiteDatabase db = SQLiteDatabase.openDatabase("testDatabase.sqlite" , null, SQLiteDatabase.OPEN_READWRITE);

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(User.COLUMN_NAME, user.name);
        values.put(User.COLUMN_EMAIL, user.email);
        values.put(User.COLUMN_TELEGRAMNICKNAME, user.telegramNickname);
        values.put(User.COLUMN_TELEPHONE, user.telephone);

        // insert row
        long id = db.insert(User.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public User getUser(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = SQLiteDatabase.openDatabase("storage/emulated/0/Download/testDatabase.sqlite" , null, SQLiteDatabase.OPEN_READWRITE);

        /*Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_NAME, User.COLUMN_TELEGRAMNICKNAME, User.COLUMN_EMAIL, User.COLUMN_TELEPHONE},
                User.COLUMN_NAME + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);*/
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_NAME, User.COLUMN_TELEGRAMNICKNAME, User.COLUMN_EMAIL, User.COLUMN_TELEPHONE},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

       // cursor.moveToFirst();
//this checks to make sure you don't have an empty set
        if(!cursor.isAfterLast())
        {
            do{
                User user = new User();
                user.setName(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)));
                user.setTelegramNickname(cursor.getString(cursor.getColumnIndex(User.COLUMN_TELEGRAMNICKNAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                user.setTelephone(cursor.getString(cursor.getColumnIndex(User.COLUMN_TELEPHONE)));
                System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+user.getId() + " "  + user.getname());
            }while(cursor.moveToNext());
        } else{
            //Log.v("MyTag", "There are no countries in the data set");
        }

        if (cursor != null) {
         System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!CUrsor");
            cursor.moveToFirst();
        }
//System.out.println(db.getAttachedDbs());
        // prepare User object
        assert cursor != null;
        User user = new User(cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TELEGRAMNICKNAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TELEPHONE)));

        // close the db connection
        cursor.close();

        return user;
    }
}
