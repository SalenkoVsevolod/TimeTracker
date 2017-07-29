package svs.timetracker.ui;

import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.ui.base.BaseActivity;
import svs.timetracker.ui.report.ReportFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().add(R.id.activity_main_root, new ReportFragment()).commit();
    }
}
