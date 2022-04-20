package com.example.myownproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private Bean data = new Bean();
    private String sql_id="";
    private int s_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        exitapp ex=exitapp.getInstance();
        ex.addActivity(this);
        s_id=getIntent().getIntExtra("id",0);
        sql_id=""+s_id;

        EditText t_tit = findViewById(R.id.text_title);
        data = query_diary1();
        String str0 = data.getName();
        t_tit.setText(str0);
        EditText t_con = findViewById(R.id.text_content);
        String str1 = data.getText();
        t_con.setText(str1);
    }

    public void back0(View view){
        startActivity(new Intent(MainActivity2.this,MainActivity.class));
    }

    public void save_diary(View view){

        EditText t_tit = findViewById(R.id.text_title);
        EditText t_con = findViewById(R.id.text_content);
        String title0 = t_tit.getText().toString();
        String content0 = t_con.getText().toString();
        if(s_id == -666) {
            insert_diary(title0,content0);
        }
        else {
            update_diary(title0, content0, s_id);
        }
        startActivity(new Intent(MainActivity2.this,MainActivity.class));

    }

    public void delete_diary0(View view){
        delete_diary(s_id);
        startActivity(new Intent(MainActivity2.this,MainActivity.class));
    }

    public Bean query_diary1() {
        Bean data = new Bean();
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase db = help.getReadableDatabase();
        if(db.isOpen()){
            Cursor cu = db.rawQuery("select * from diaries where _id=?", new String[]{sql_id});
            while (cu.moveToNext()) {
                int hellid = cu.getColumnIndex("_id");
                int helltitle = cu.getColumnIndex("title");
                int hellcontent = cu.getColumnIndex("content");
                int helid = cu.getInt(hellid);
                String heltitle = cu.getString(helltitle);
                String helcontent = cu.getString(hellcontent);
                data.setName(heltitle);
                data.setId(helid);
                data.setText(helcontent);
            }
            cu.close();
            db.close();
        }
        return data;
    }

    public void insert_diary(String t,String c) {
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase db = help.getWritableDatabase();

        if(db.isOpen())
        {
            String sql = "insert into diaries(title,content) values(?,?)";
            db.execSQL(sql, new Object[]{t,c});
        }
        db.close();
    }

    public void update_diary(String t,String c,int x) {
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase db = help.getWritableDatabase();

        if(db.isOpen())
        {
            String sql = "update diaries set title =?, content=? where _id=?";
            db.execSQL(sql, new Object[]{t, c, x});
        }
        db.close();
    }

    public void delete_diary(int x) {
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase db = help.getWritableDatabase();

        if(db.isOpen() && x!=-666)
        {
            String sql = "delete from diaries where _id=?";
            db.execSQL(sql, new Object[]{x});
        }
        db.close();
    }
}