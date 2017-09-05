package svs.timetracker.presentation.ui.projects;

import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.navigation.BaseNavigationActivity;
import svs.timetracker.presentation.ui.navigation.BaseNavigationFragment;
import svs.timetracker.presentation.ui.navigation.SimpleNavigationFragment;

public class ProjectsActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
    }

    @Override
    protected BaseFragment getContentFragment() {
        return new ProjectsFragment();
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
