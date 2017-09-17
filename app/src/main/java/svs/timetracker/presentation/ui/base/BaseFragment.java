package svs.timetracker.presentation.ui.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import svs.timetracker.core.AppBridge;

public abstract class BaseFragment extends Fragment implements BaseView {
    protected AppBridge appBridge;
    private BaseActivity mActivity;

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        appBridge = (AppBridge) getActivity().getApplication();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(getLayoutId(), null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void finish() {
        mActivity.onBackPressed();
    }

    @Override
    public void showToast(String message) {
        mActivity.showToast(message);
    }

    @Override
    public void showToast(@StringRes int messageId) {
        mActivity.showToast(messageId);
    }


}
