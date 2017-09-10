package svs.timetracker.core;

import svs.timetracker.data.RepositoryManager;

public interface AppBridge {
    SharedPreferencesHelper getSharedPreferences();

    RepositoryManager getRepositoryManager();
}
