package svs.timetracker.core;

import android.app.Application;

import svs.timetracker.data.Repository;
import svs.timetracker.data.room.RoomDatabase;
import svs.timetracker.data.room.RoomRepository;

public class TimeTrackerApplication extends Application implements AppBridge {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private Repository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        repository = new RoomRepository(RoomDatabase.getInstance(this).projectDao());
    }

    @Override
    public SharedPreferencesHelper getSharedPreferences() {
        return sharedPreferencesHelper;
    }

    @Override
    public Repository getRepository() {
        return repository;
    }
}
