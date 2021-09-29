package com.example.twquickadd.databinding;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.twquickadd.room.Tiddler;
import com.example.twquickadd.room.TiddlerDAO;

import java.util.List;

public class TiddlerViewModel extends ViewModel {
    public final LiveData<List<Tiddler>> tiddlersList;

    public TiddlerViewModel(TiddlerDAO tiddlerDao) {
        tiddlersList = tiddlerDao.getAllTiddlers();
    }
}
