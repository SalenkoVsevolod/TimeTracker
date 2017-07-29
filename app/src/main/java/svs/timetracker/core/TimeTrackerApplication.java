package svs.timetracker.core;

import android.app.Application;

/**
 * Created by Black on 29.07.2017.
 */

public class TimeTrackerApplication extends Application implements AppBridge {
    private SharedPreferencesHelper mSharedPreferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mSharedPreferencesHelper = new SharedPreferencesHelper(this);
    }

    @Override
    public SharedPreferencesHelper getSharedPreferences() {
        return mSharedPreferencesHelper;
    }
}
