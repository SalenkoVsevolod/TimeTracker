package svs.timetracker.presentation.ui.report;

import svs.timetracker.presentation.ui.base.BasePresenter;

public interface ReportPresenter extends BasePresenter<IReportView> {

    void onReportClick();

    void onTextChanged(String text);
}
