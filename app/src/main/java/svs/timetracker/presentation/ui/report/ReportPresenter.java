package svs.timetracker.presentation.ui.report;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BasePresenter;

public class ReportPresenter extends BasePresenter<IReportView> {
    private String reportText;

    public ReportPresenter() {
    }

    @Override
    public void bindView(IReportView iReportView) {
        super.bindView(iReportView);
        view().setReportText(reportText);
    }

    public void onReportClick() {
        view().copyToClipBoard(reportText);
        view().showToast(R.string.report_copied_to_clipboard);
    }

    public void onTextChanged(String text) {
        reportText = text;
        view().displayReportText(reportText);
    }

}
