package svs.timetracker.core;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SharedPreferencesHelper {
    private static final String PREFERENCES_NAME = "prefs";
    private static final String GREETINGS_NEEDED_KEY = "greetingsNeededKey";
    private static final String MAX_EMOJI_NUMBER_KEY = "maxEmojiNumberKey";
    private static final String MIN_HOURS_KEY = "minHoursKey";
    private static final String MAX_HOURS_KEY = "maxHoursKey";
    private static final String EMOJI_ITEM_KEY = "emojiCharKey";
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

    @NonNull
    public String getEmojiItem() {
        return preferences.getString(EMOJI_ITEM_KEY, ")");
    }

    public void setEmojiItem(@NonNull final String emojiItem) {
        preferences.edit().putString(EMOJI_ITEM_KEY, emojiItem).apply();
    }
}
