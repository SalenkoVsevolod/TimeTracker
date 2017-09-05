package svs.timetracker.presentation.ui.navigation;

import java.util.ArrayList;
import java.util.List;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BasePresenter;


public class NavigationPresenter extends BasePresenter<INavigationView> {
    private static final int PROJECTS = 0, SETTINGS = 1;
    private List<NavigationItem> navigationItems;

    @Override
    public void bindView(INavigationView iNavigationView) {
        super.bindView(iNavigationView);
        List<NavigationItem> items = new ArrayList<>();
        items.add(PROJECTS, new NavigationItem(view().getString(R.string.projects)));
        items.add(SETTINGS, new NavigationItem(view().getString(R.string.settings)));
        navigationItems = items;
        view().setNavigationItems(navigationItems);
    }

    public void onNavigationItemClicked(int position) {
        switch (position) {
            case PROJECTS:
                view().openProjectsScreen();
                break;
            case SETTINGS:
                view().openSettingsScreen();
                break;
        }
    }
}
