package svs.timetracker.data;

import android.content.Context;

import svs.timetracker.data.room.RoomDatabase;
import svs.timetracker.data.room.RoomRepository;

public class RepositoryManager {
    private RoomRepository roomRepository;

    public RepositoryManager(Context context) {
        roomRepository = new RoomRepository(RoomDatabase.getInstance(context).projectDao());
    }

    public Repository getRepository() {
        return roomRepository;
    }
}
