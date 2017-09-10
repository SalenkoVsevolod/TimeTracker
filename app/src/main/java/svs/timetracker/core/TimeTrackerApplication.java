package svs.timetracker.core;

import android.app.Application;

import svs.timetracker.data.RepositoryManager;

public class TimeTrackerApplication extends Application implements AppBridge {
    private SharedPreferencesHelper sharedPreferencesHelper;
    private RepositoryManager repositoryManager;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
        repositoryManager = new RepositoryManager(this);
    }

    @Override
    public SharedPreferencesHelper getSharedPreferences() {
        return sharedPreferencesHelper;
    }

    @Override
    public RepositoryManager getRepositoryManager() {
        return repositoryManager;
    }
}
