package edu.sunyulster.finalprojectattempt5;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "note")
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    private String noteContext;

    public Note(String noteContext) {
        this.noteContext = noteContext;
    }

    public int getId() {
        return this.id;
    }

    public String getNoteContext() {
        return this.noteContext;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNoteContext(String noteContext) {
        this.noteContext = noteContext;
    }
}
