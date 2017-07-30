package svs.timetracker.ui.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;

import svs.timetracker.R;
import svs.timetracker.ui.base.BaseActivity;


public abstract class BaseNavigationActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().add(R.id.left_drawer, getNavigationFragment()).commit();
        //TODO handle configuration changing
    }

    protected abstract BaseNavigationFragment getNavigationFragment();
}
