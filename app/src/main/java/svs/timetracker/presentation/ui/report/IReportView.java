package svs.timetracker.presentation.ui.report;

import android.support.annotation.NonNull;

import svs.timetracker.presentation.ui.base.BaseView;

public interface IReportView extends BaseView {

    void copyToClipBoard(@NonNull String text);

    void displayReportText(@NonNull String text);

    void setEmojiNumber(int emojiNumber);

    void setHours(int hours);

    String getOnString();
}
