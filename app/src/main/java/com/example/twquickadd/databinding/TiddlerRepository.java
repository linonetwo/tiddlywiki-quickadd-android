package com.example.twquickadd.databinding;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.twquickadd.room.Tiddler;
import com.example.twquickadd.room.TiddlerDAO;
import com.example.twquickadd.room.TiddlerDB;

import java.util.List;

public class TiddlerRepository {
    private TiddlerDAO mTiddlerDAO;
    public LiveData<List<Tiddler>> tiddlersList;

    public TiddlerRepository(Application application) {
        TiddlerDB db = TiddlerDB.getInstance(application);
        mTiddlerDAO = db.getTiddlerDAO();
        tiddlersList = mTiddlerDAO.getAllTiddlers();
    }

    void insertTiddler(Tiddler tiddler) {
        TiddlerDB.databaseWriteExecutor.execute(() -> {
            mTiddlerDAO.insertTiddler(tiddler);
        });
    }
}
