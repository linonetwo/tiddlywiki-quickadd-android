package com.example.twquickadd.databinding;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.twquickadd.room.Tiddler;

import java.util.List;


public class TiddlerViewModel extends AndroidViewModel {
    private final LiveData<List<Tiddler>> mTiddlersList;
    private TiddlerRepository mRepository;
    private Application mApplication;

    public TiddlerViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
        mTiddlersList = mRepository.tiddlersList;
    }

    public LiveData<List<Tiddler>> getAllTiddlers() {
        return mTiddlersList;
    }
}
