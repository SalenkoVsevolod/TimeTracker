package svs.timetracker.presentation.ui.greetings;

import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.navigation.BaseNavigationActivity;
import svs.timetracker.presentation.ui.navigation.BaseNavigationFragment;
import svs.timetracker.presentation.ui.navigation.SimpleNavigationFragment;

public class GreetingsActivity extends BaseNavigationActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greetings);
    }

    @Override
    protected BaseFragment getContentFragment() {
        return new GreetingsFragment();
    }

    @Override
    protected BaseNavigationFragment getNavigationFragment() {
        return new SimpleNavigationFragment();
    }
}
