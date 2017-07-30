package svs.timetracker.ui.base;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Black on 29.07.2017.
 */

public abstract class BaseFragment extends Fragment implements IBaseView {
    private BaseActivity mActivity;

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
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
