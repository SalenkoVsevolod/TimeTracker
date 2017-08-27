package svs.timetracker.presentation.ui.navigation;

import java.util.List;

import svs.timetracker.presentation.ui.base.IBaseView;

public interface INavigationView extends IBaseView{
    void openProjectsScreen();

    void openSettingsScreen();

    void setNavigationItems(List<NavigationItem> items);
}
