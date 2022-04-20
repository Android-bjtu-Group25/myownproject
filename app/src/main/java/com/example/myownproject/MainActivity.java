package com.example.myownproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Bean> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_title);
        exitapp ex=exitapp.getInstance();
        ex.addActivity(this);

        createDB();

        /*for(int i = 0; i < 10; i++){
            String t=("title" + i);
            String c=("content" + i);
            insert_diary(t,c);
        }*/

        data = query_diary();

        ListView listview = findViewById(R.id.text_list);
        listview.setAdapter(new MyAdapter(data,this));
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent pr =new Intent(MainActivity.this,MainActivity2.class);
                pr.putExtra("id",data.get(i).getId());
                //Log.e("pxy","getid"+ pr.getIntExtra("id",0));
                startActivity(pr);
            }
        });
        /*TextView t_con = findViewById(R.id.text_content);
        //t_con.setBackgroundResource(R.color.paper);
        String str = "我生于新千年，与新世纪的祖国一路同行、成长，见证了复兴的道路伴随\n" +
                "着的坎坷和荆棘，既有难以预料的天灾，也有他国的挑衅阻拦。2008年的\n" +
                "汶川大地震堪称国殇;2001年美国侦察机飞抵海南岛撞毁中国战机，2018\n" +
                "年，山姆大叔的军舰又在南海虎视眈眈，还抡起了贸易战的大棒……";
        t_con.setText(str);*/
    }

    public void exit_diary(View view){

        exitapp ex=exitapp.getInstance();
        ex.onTerminate();
    }

    public void add_diary(View view){

        Intent pr =new Intent(MainActivity.this,MainActivity2.class);
        pr.putExtra("id",-666);
        startActivity(pr);

    }

    public void createDB() {
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase readDB = help.getReadableDatabase();
        if(readDB.isOpen()){
            readDB.close();
        }
    }

    public ArrayList<Bean> query_diary() {
        ArrayList<Bean> data = new ArrayList<>();
        SQLiteOpenHelper help = SQLiteropenhelp.getmInstance(this);
        SQLiteDatabase db = help.getReadableDatabase();
        if(db.isOpen()){
            Cursor cu = db.rawQuery("select * from diaries", null);
            while (cu.moveToNext())
            {
                Bean bean =new Bean();

                int hellid = cu.getColumnIndex("_id");
                int helltitle = cu.getColumnIndex("title");
                int hellcontent = cu.getColumnIndex("content");
                int helid = cu.getInt(hellid);
                String heltitle = cu.getString(helltitle);
                String helcontent = cu.getString(hellcontent);
                bean.setName(heltitle);
                bean.setId(helid);
                bean.setText(helcontent);
                data.add(bean);
            }
            cu.close();
            db.close();
        }
        return data;
    }


}
