package svs.timetracker.presentation.ui.navigation;

import java.util.List;

import svs.timetracker.presentation.ui.base.BaseView;

public interface NavigationView extends BaseView{
    void openProjectsScreen();

    void openSettingsScreen();

    void setNavigationItems(List<NavigationItem> items);
}
