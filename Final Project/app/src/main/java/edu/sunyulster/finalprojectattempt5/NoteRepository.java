package edu.sunyulster.finalprojectattempt5;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;
import androidx.room.Query;

import java.util.List;

public class NoteRepository {

    private final MutableLiveData<List<Note>> searchResults =
            new MutableLiveData<>();
            private List<Note> results;
            private final LiveData<List<Note>> allNotes;
            private final NoteDao noteDao;

            public NoteRepository(Application application) {
                NoteDatabase db;
                db = NoteDatabase.getDatabase(application);
                noteDao = db.noteDao();
                allNotes = NoteDao.getAll();
            }

    Handler handler = new Handler(Looper.getMainLooper()) {
        @Override public void handleMessage(Message msg) {
            searchResults.setValue(results);
        }
    };

    public void insertNote(Note newNote) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            noteDao.insertNote(newNote);
        });
        executor.shutdown();
    }

    public LiveData<List<Note>> getAll() {
        return NoteDao.getAll();
    }
}
