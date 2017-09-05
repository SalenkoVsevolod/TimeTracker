package svs.timetracker.core;

import android.app.Application;

public class TimeTrackerApplication extends Application implements AppBridge {
    private SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferencesHelper = new SharedPreferencesHelper(this);
    }

    @Override
    public SharedPreferencesHelper getSharedPreferences() {
        return sharedPreferencesHelper;
    }
}
