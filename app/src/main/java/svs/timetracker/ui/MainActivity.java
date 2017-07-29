package svs.timetracker.ui;

import android.os.Bundle;

import svs.timetracker.R;
import svs.timetracker.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
