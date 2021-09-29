package com.example.twquickadd.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TiddlerDAO {
    @Insert
    void insertTiddler(Tiddler... tiddlers);

    @Update
    void updateTiddler(Tiddler... tiddlers);

    @Delete
    void deleteStudents(Tiddler... tiddlers);

    @Query("DELETE FROM Tiddler")
    void deleteAllTiddlers();

    @Query("SELECT * FROM Tiddler ORDER BY ID DESC")
    LiveData<List<Tiddler>> getAllTiddlers();

    @Query("SELECT * FROM Tiddler WHERE ID in (:id)")
    LiveData<List<Tiddler>> getTiddlerByID(String id);
}
