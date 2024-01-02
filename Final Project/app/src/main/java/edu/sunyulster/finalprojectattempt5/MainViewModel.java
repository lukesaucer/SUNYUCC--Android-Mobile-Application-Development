package edu.sunyulster.finalprojectattempt5;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import edu.sunyulster.finalprojectattempt5.Note;
import edu.sunyulster.finalprojectattempt5.NoteRepository;
import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;
   // private MutableLiveData<List<Note>> searchResults;

    public MainViewModel (Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAll();
       // searchResults = repository.getSearchResults();
    }
}
