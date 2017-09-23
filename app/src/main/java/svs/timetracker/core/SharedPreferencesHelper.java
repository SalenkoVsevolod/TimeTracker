package svs.timetracker.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class SharedPreferencesHelper {
    private static final String PREFERENCES_NAME = "prefs";
    private static final String GREETINGS_NEEDED_KEY = "greetingsNeededKey";
    private static final String MAX_EMOJI_NUMBER_KEY = "maxEmojiNumberKey";
    private static final String MIN_HOURS_KEY = "minHoursKey";
    private static final String MAX_HOURS_KEY = "maxHoursKey";
    private static final String EMOJI_ITEM_KEY = "emojiCharKey";
    private static final String LAST_SELECTED_PROJECT_KEY = "lastSelectedProjectKey";
    private static final String LAST_SELECTED_HOURS_KEY = "lastSelectedHoursKey";
    private static final String LAST_SELECTED_EMOJI_NUMBER_KEY = "lastSelectedEmojiNumberKey";
    private SharedPreferences preferences;

    public SharedPreferencesHelper(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public boolean isGreetingsNeeded() {
        return preferences.getBoolean(GREETINGS_NEEDED_KEY, false);
    }

    public void setGreetingsNeeded(final boolean greetingsNeeded) {
        preferences.edit().putBoolean(GREETINGS_NEEDED_KEY, greetingsNeeded).apply();
    }

    public int getMaxEmojiNumber() {
        return preferences.getInt(MAX_EMOJI_NUMBER_KEY, 3);
    }

    public void setMaxEmojiNumber(final int maxEmojiNumber) {
        preferences.edit().putInt(MAX_EMOJI_NUMBER_KEY, maxEmojiNumber).apply();
    }

    @NonNull
    public String getEmojiItem() {
        return preferences.getString(EMOJI_ITEM_KEY, ")");
    }

    public void setEmojiItem(@NonNull final String emojiItem) {
        preferences.edit().putString(EMOJI_ITEM_KEY, emojiItem).apply();
    }

    public int getLastSelectedEmojiNumber() {
        return preferences.getInt(LAST_SELECTED_EMOJI_NUMBER_KEY, 1);
    }

    public void setLastSelectedEmojiNumber(final int emojiNumber) {
        preferences.edit().putInt(LAST_SELECTED_EMOJI_NUMBER_KEY, emojiNumber).apply();
    }

    public int getMinHours() {
        return preferences.getInt(MIN_HOURS_KEY, 1);
    }

    public void setMinHours(final int hours) {
        preferences.edit().putInt(MIN_HOURS_KEY, hours).apply();
    }

    public int getMaxHours() {
        return preferences.getInt(MAX_HOURS_KEY, 8);
    }

    public void setMaxHours(final int hours) {
        preferences.edit().putInt(MAX_HOURS_KEY, hours).apply();
    }

    public int getLastSelectedHours() {
        return preferences.getInt(LAST_SELECTED_HOURS_KEY, 8);
    }

    public void setLastSelectedHours(final int hours) {
        preferences.edit().putInt(LAST_SELECTED_HOURS_KEY, hours).apply();
    }

    @Nullable
    public String getSelectedProject() {
        return preferences.getString(LAST_SELECTED_PROJECT_KEY, null);
    }

    public void setSelectedProject(@Nullable final String projectName) {
        preferences.edit().putString(LAST_SELECTED_PROJECT_KEY, projectName).apply();
    }

}
