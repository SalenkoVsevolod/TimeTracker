package svs.timetracker.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SharedPreferencesHelper {
    private static final String PREFERENCES_NAME = "prefs";
    private static final String PROJECT_NAME = "projectName";
    private SharedPreferences preferences;

    public SharedPreferencesHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setLastProjectName(@NonNull String name) {
        preferences.edit().putString(PROJECT_NAME, name).apply();
    }

    @Nullable
    public String getLastProjectName() {
        return preferences.getString(PROJECT_NAME, null);
    }
}
