package svs.timetracker.ui;

import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.ui.navigation.BaseNavigationActivity;
import svs.timetracker.ui.navigation.BaseNavigationFragment;
import svs.timetracker.ui.navigation.SimpleNavigationFragment;
import svs.timetracker.ui.report.ReportFragment;

public class MainActivity extends BaseNavigationActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().add(R.id.activity_main_root, new ReportFragment()).commit();
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
