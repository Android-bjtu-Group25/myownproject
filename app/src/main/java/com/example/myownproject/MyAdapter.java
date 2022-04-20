package com.example.myownproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintSet;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private ArrayList<Bean> data;
    private Context context;

    public MyAdapter(ArrayList<Bean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }
        TextView textview = view.findViewById(R.id.tv);
        textview.setText(data.get(i).getName());

        Log.e("leo","getView"+ i);

        return view;
    }
}
