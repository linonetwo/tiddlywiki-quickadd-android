package com.example.twquickadd.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Tiddler {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Tiddler{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
