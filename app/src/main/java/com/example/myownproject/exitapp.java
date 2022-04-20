package com.example.myownproject;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class exitapp  extends Application {
    private static exitapp instance0;
    public static synchronized exitapp getInstance(){
        if (instance0 == null){
            instance0 = new exitapp();
        }
        return instance0;
    }
    private exitapp(){

    }
    private List<Activity> activities = new ArrayList<>();
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        for (Activity activity : activities) {
            activity.finish();
        }

        System.exit(0);
    }
}

