package svs.timetracker.presentation.ui;

import android.os.Bundle;

import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.navigation.BaseNavigationActivity;
import svs.timetracker.presentation.ui.navigation.BaseNavigationFragment;
import svs.timetracker.presentation.ui.navigation.SimpleNavigationFragment;
import svs.timetracker.presentation.ui.report.ReportFragment;

public class MainActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment getContentFragment() {
        return ReportFragment.getInstance("");
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
