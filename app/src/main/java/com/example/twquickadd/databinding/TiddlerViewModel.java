package com.example.twquickadd.databinding;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.twquickadd.room.Tiddler;

import java.util.ArrayList;
import java.util.List;


public class TiddlerViewModel extends AndroidViewModel {
    private final MutableLiveData<List<Tiddler>> mTiddlersListLiveData = new MutableLiveData(new ArrayList<Tiddler>());
    private TiddlerRepository mRepository;
    private Application mApplication;

    public TiddlerViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
        mRepository = new TiddlerRepository(application);
        // TODO: why this is null? we should use List from repository
        // mRepository.tiddlersList.getValue()
    }

    public MutableLiveData<List<Tiddler>> getAllTiddlers() {
        return mTiddlersListLiveData;
    }
    public void addNewTiddler(Tiddler tiddler) {
        List<Tiddler> prevData = mTiddlersListLiveData.getValue();
        prevData.add(tiddler);
        mTiddlersListLiveData.setValue(prevData);
    }
}
