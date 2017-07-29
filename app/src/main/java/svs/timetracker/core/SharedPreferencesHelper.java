package svs.timetracker.core;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Black on 29.07.2017.
 */

public class SharedPreferencesHelper {
    private static final String PREFERENCES_NAME = "prefs";
    private SharedPreferences mPreferences;

    public SharedPreferencesHelper(Context context) {
        mPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}
