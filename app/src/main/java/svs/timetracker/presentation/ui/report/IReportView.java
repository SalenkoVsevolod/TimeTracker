package svs.timetracker.presentation.ui.report;

import svs.timetracker.presentation.ui.base.IBaseView;

/**
 * Created by Black on 29.07.2017.
 */

public interface IReportView extends IBaseView {
    void setReportText(String text);

    void copyToClipBoard(String text);

    void displayReportText(String text);
}
