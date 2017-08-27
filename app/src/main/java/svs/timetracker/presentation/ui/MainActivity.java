package svs.timetracker.presentation.ui;

import android.os.Bundle;

import svs.timetracker.domain.model.Greeting;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.model.SpentTime;
import svs.timetracker.domain.model.TwoLinesReport;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.navigation.BaseNavigationActivity;
import svs.timetracker.presentation.ui.navigation.BaseNavigationFragment;
import svs.timetracker.presentation.ui.navigation.SimpleNavigationFragment;
import svs.timetracker.presentation.ui.report.ReportFragment;

public class MainActivity extends BaseNavigationActivity {
    private TwoLinesReport mTwoLinesReport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTwoLinesReport = new TwoLinesReport();
        mTwoLinesReport.setCurrentDate(System.currentTimeMillis());
        mTwoLinesReport.setGreeting(new Greeting("Привет", "Марина", "))"));
        mTwoLinesReport.setSpentTime(new SpentTime(6, "часов"));
        mTwoLinesReport.setSpendingTimeCause(new Project("на", "honcker"));
    }

    @Override
    protected BaseFragment getContentFragment() {
        return ReportFragment.getInstance(mTwoLinesReport.toString());
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
