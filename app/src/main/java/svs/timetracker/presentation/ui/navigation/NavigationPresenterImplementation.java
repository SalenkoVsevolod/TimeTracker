package svs.timetracker.presentation.ui.navigation;

import java.util.ArrayList;
import java.util.List;

import svs.timetracker.R;
import svs.timetracker.core.AppBridge;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;


public class NavigationPresenterImplementation extends BasePresenterImplementation<NavigationView> {
    private static final int PROJECTS = 0, SETTINGS = 1;
    private List<NavigationItem> navigationItems;

    protected NavigationPresenterImplementation(AppBridge appBridge) {
        super(appBridge);
    }


    @Override
    public void bindView(NavigationView navigationView) {
        super.bindView(navigationView);
        List<NavigationItem> items = new ArrayList<>();
        items.add(PROJECTS, new NavigationItem(getView().getString(R.string.projects)));
        items.add(SETTINGS, new NavigationItem(getView().getString(R.string.settings)));
        navigationItems = items;
        getView().setNavigationItems(navigationItems);
    }

    public void onNavigationItemClicked(int position) {
        switch (position) {
            case PROJECTS:
                getView().openProjectsScreen();
                break;
            case SETTINGS:
                getView().openSettingsScreen();
                break;
        }
    }
}
