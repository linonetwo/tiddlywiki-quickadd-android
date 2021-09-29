package com.example.twquickadd.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Tiddler.class}, version = 1, exportSchema = false)
public abstract class TiddlerDB extends RoomDatabase {
    public abstract TiddlerDAO getTiddlerDAO();

    private static TiddlerDB INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized TiddlerDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TiddlerDB.class, "Tiddlers").build();
        }
        return INSTANCE;
    }
}
