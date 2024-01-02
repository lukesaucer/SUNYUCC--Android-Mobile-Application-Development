package edu.sunyulster.finalprojectattempt5;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Query("SELECT * FROM note")
    static LiveData<List<Note>> getAll() {
        return null;
    }

}
