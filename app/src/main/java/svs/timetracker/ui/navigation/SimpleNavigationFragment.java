package svs.timetracker.ui.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class SimpleNavigationFragment extends BaseNavigationFragment implements INavigationView {

    private NavigationPresenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new NavigationPresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.unbindView();
    }


    @Override
    public void onNavigationItemClicked(int position) {
        mPresenter.onNavigationItemClicked(position);
    }

    @Override
    public void openProjectsScreen() {
        showToast("PROJECTS");
        //TODO
    }

    @Override
    public void openSettingsScreen() {
        showToast("SETTINGS");
        //TODO
    }

}
