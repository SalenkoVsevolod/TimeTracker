package svs.timetracker.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import svs.timetracker.core.AppBridge;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected AppBridge appBridge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        appBridge = (AppBridge) getApplication();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showToast(@StringRes int messageRes) {
        showToast(getString(messageRes));
    }


}
