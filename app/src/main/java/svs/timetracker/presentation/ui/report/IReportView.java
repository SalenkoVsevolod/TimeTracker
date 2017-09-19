package svs.timetracker.presentation.ui.report;

import svs.timetracker.presentation.ui.base.BaseView;

public interface IReportView extends BaseView {

    void copyToClipBoard(String text);

    void displayReportText(String text);

    String getOnString();
}
