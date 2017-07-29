package svs.timetracker.ui.report;

import svs.timetracker.R;
import svs.timetracker.ui.base.BasePresenter;

/**
 * Created by Black on 29.07.2017.
 */

public class ReportPresenter extends BasePresenter<String, IReportView> {
    private String mReportText = "Ya igral ves' den' i shobolil, ya ne rabotal, 8 chasow";

    @Override
    protected void updateView() {

    }

    @Override
    public void bindView(IReportView iReportView) {
        super.bindView(iReportView);
        view().setReportText(mReportText);
    }

    public void onReportClick() {
        view().copyToClipBoard(mReportText);
        view().showToast(R.string.report_copied_to_clipboard);
    }
}
