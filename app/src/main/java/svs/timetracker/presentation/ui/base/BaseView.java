package svs.timetracker.presentation.ui.base;

import android.support.annotation.StringRes;

public interface BaseView {
    void finish();

    void showToast(String message);

    void showToast(@StringRes int messageId);

    String getString(@StringRes int stringRes);
}
