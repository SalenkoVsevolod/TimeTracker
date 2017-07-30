package svs.timetracker.ui.navigation;

import java.util.List;

import svs.timetracker.ui.base.IBaseView;

public interface INavigationView extends IBaseView{
    void openProjectsScreen();

    void openSettingsScreen();

    void setNavigationItems(List<NavigationItem> items);
}
