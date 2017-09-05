package svs.timetracker.core;

import svs.timetracker.data.Repository;

public interface AppBridge {
    SharedPreferencesHelper getSharedPreferences();

    Repository getRepository();
}
