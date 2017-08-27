package svs.timetracker.presentation.ui.report;

import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BasePresenter;

public class ReportPresenter extends BasePresenter<String, IReportView> {
    public ReportPresenter() {
    }

    @Override
    protected void updateView() {
        view().setReportText(mModel);
    }

    public void onReportClick() {
        view().copyToClipBoard(mModel);
        view().showToast(R.string.report_copied_to_clipboard);
    }

    public void onTextChanged(String text) {
        if (mModel == null || !mModel.equals(text)) {
            setModel(text);
            view().displayReportText(mModel);
        }
    }

}
