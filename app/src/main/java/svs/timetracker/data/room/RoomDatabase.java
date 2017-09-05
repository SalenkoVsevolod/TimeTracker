package svs.timetracker.data.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.content.Context;

import svs.timetracker.domain.model.Project;

@Database(entities = {Project.class}, version = 1)
public abstract class RoomDatabase extends android.arch.persistence.room.RoomDatabase {
    private static volatile RoomDatabase INSTANCE;

    public static RoomDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDatabase.class, "TimeTrackerDataBase.db").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract ProjectDao projectDao();
}
