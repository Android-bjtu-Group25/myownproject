package com.example.myownproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteropenhelp extends SQLiteOpenHelper {

    private static SQLiteOpenHelper mInstance;
    public static synchronized SQLiteOpenHelper getmInstance(Context context){
        if (mInstance == null){
            mInstance = new SQLiteropenhelp(context, "hellodb.db", null, 1);
        }
        return mInstance;
    }

    private SQLiteropenhelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql1 = "create table accounts(_id integer primary key autoincrement, name text,password text)";

        sqLiteDatabase.execSQL(sql1);

        String sql2 = "create table diaries(_id integer primary key autoincrement, title text,content text)";

        sqLiteDatabase.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
