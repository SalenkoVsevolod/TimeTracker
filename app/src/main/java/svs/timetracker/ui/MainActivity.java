package svs.timetracker.ui;

import android.os.Bundle;

import svs.timetracker.ui.base.BaseFragment;
import svs.timetracker.ui.navigation.BaseNavigationActivity;
import svs.timetracker.ui.navigation.BaseNavigationFragment;
import svs.timetracker.ui.navigation.SimpleNavigationFragment;
import svs.timetracker.ui.report.ReportFragment;

public class MainActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BaseFragment getContentFragment() {
        return new ReportFragment();
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
