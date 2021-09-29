package com.example.twquickadd.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Tiddler.class}, version = 1, exportSchema = false)
public abstract class TiddlerDB extends RoomDatabase {
    public abstract TiddlerDAO getTiddlerDAO();

    private static TiddlerDB INSTANCE;

    public static synchronized TiddlerDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TiddlerDB.class, "Tiddlers").build();
        }
        return INSTANCE;
    }
}
