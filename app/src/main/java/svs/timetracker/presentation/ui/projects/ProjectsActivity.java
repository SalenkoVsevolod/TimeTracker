package svs.timetracker.presentation.ui.projects;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.base.dialogs.EditTextDialog;
import svs.timetracker.presentation.ui.navigation.BaseNavigationActivity;
import svs.timetracker.presentation.ui.navigation.BaseNavigationFragment;
import svs.timetracker.presentation.ui.navigation.SimpleNavigationFragment;

public class ProjectsActivity extends BaseNavigationActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, ProjectsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.projects));
    }

    @Override
    protected BaseFragment getContentFragment() {
        return ProjectsFragment.newInstance();
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
