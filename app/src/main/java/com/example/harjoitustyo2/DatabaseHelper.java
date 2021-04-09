package com.example.harjoitustyo2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DatabaseHelper extends SQLiteOpenHelper {

    static String name = "database";
    static int version = 1;
    JSONFileControl jsonFileControl;


    String createTableUser = "CREATE TABLE if not exists \"user\" (\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"name\"\tTEXT,\n" +
            "\t\"username\"\tTEXT,\n" +
            "\t\"password\"\tTEXT,\n" +
            "\t\"municipality\"\tTEXT,\n" +
            "\t\"height\"\tINTEGER,\n" +
            "\t\"weight\"\tINTEGER,\n" +
            "\t\"gender\"\tTEXT,\n" +
            "\t\"birthyear\"\tINTEGER,\n" +
            "\tPRIMARY KEY(\"id\")\n" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(createTableUser);
    }

    public boolean isLoginValid(String username, String password){
        String sql = "Select count(*) from user where username = '" + username + "' and password = '" + password + "'";
        SQLiteStatement statement = getReadableDatabase().compileStatement(sql);
        long l = statement.simpleQueryForLong();
        statement.close();
        return l == 1;
    }

    public void insertUser(User user, Context context){
        jsonFileControl = new JSONFileControl();

        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user.getUsername());
        contentValues.put("name", user.getName());
        contentValues.put("password", user.getPassword());
        contentValues.put("municipality", user.getMunicipality());
        contentValues.put("height", user.getHeight());
        contentValues.put("weight", user.getWeight());
        contentValues.put("gender", user.getGender());
        contentValues.put("birthyear", user.getBirthyear());

        getWritableDatabase().insert("user", "", contentValues);

        //Adding first weight to Weight Data File
        String weightValue = String.valueOf(user.getWeight());
        jsonFileControl.writeLogWeight(weightValue, context, user.getUsername());

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkUsername(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        try (Cursor cursor = myDB.rawQuery("Select * from user where username = ?", new String[]{username})) {

            return cursor.getCount() > 0;
        }

    }
    public String getHeight(String username){
        System.out.println("USERNAME getHEight: " + username);
        String sql = "Select height FROM user WHERE username = '"+ username + "'";
        String height;
        try (Cursor cursor = getReadableDatabase().rawQuery(sql, null)) {
            cursor.moveToFirst();
            height = cursor.getString(0);
        }

        return height;
    }

    public String getName(String username) throws Exception{
        String sql = "Select name from user where username = '" + username + "'";
        System.out.println(username);
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        String name = cursor.getString(0);
        System.out.println("DATABASE PALAUTTAA NIMEN: " + name);
        return name;
    }









}
