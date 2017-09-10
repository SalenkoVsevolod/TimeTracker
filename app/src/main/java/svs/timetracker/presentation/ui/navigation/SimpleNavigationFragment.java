package svs.timetracker.presentation.ui.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import svs.timetracker.presentation.ui.projects.ProjectsActivity;

public class SimpleNavigationFragment extends BaseNavigationFragment implements NavigationView {

    private NavigationPresenterImplementation presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new NavigationPresenterImplementation(appBridge);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unbindView();
    }


    @Override
    public void onNavigationItemClicked(int position) {
        presenter.onNavigationItemClicked(position);
    }

    @Override
    public void openProjectsScreen() {
        ProjectsActivity.start(getActivity());
    }

    @Override
    public void openSettingsScreen() {
        showToast("SETTINGS");
        //TODO
    }

}
