package svs.timetracker.ui.report;

import svs.timetracker.ui.base.IBaseView;

/**
 * Created by Black on 29.07.2017.
 */

public interface IReportView extends IBaseView {
    void setReportText(String text);

    void copyToClipBoard(String text);
}
