package svs.timetracker.ui.report;

import svs.timetracker.R;
import svs.timetracker.ui.base.BasePresenter;

/**
 * Created by Black on 29.07.2017.
 */

public class ReportPresenter extends BasePresenter<String, IReportView> {
    public ReportPresenter() {
        setModel("Ya igral ves' den' i shobolil, ya ne rabotal, 8 chasow");
        //TODO move it somewhere else
    }

    @Override
    protected void updateView() {
        view().setReportText(mModel);
    }

    public void onReportClick() {
        view().copyToClipBoard(mModel);
        view().showToast(R.string.report_copied_to_clipboard);
    }
}
