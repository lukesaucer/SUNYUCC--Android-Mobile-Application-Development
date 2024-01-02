package edu.sunyulster.finalprojectattempt5;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao noteDao();
    private static NoteDatabase INSTANCE;

    static NoteDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            sychronized (NoteDatabase.class); {
                if (INSTANCE == null) {

                     INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database").build();
                }
            }
        }
        return INSTANCE;
    }

    private static void sychronized(Class<NoteDatabase> noteDatabaseClass) {
    }
}
