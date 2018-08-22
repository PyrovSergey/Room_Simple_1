package com.example.pyrov.room_simple_1;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.pyrov.room_simple_1.db.AppDatabase;

public class App extends Application {

    private static App instance;

    private static Context context;

    private static AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
//        database = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "database")
//                .allowMainThreadQueries()
//                .build();

        database = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database").build();
    }

    public static App getInstance() {
        return instance;
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    public static Context getContext() {
        return context;
    }
}
