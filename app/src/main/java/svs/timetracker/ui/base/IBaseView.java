package svs.timetracker.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Black on 29.07.2017.
 */

public interface IBaseView {
    void finish();

    void showToast(String message);

    void showToast(@StringRes int messageId);
}
