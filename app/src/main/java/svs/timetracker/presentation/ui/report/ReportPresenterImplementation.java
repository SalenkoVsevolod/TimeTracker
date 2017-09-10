package svs.timetracker.presentation.ui.report;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import svs.timetracker.R;
import svs.timetracker.core.AppBridge;
import svs.timetracker.domain.model.Greeting;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.model.SpentTime;
import svs.timetracker.domain.model.TwoLinesReport;
import svs.timetracker.domain.use_case.GetSelectedProjectUseCase;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;

public class ReportPresenterImplementation extends BasePresenterImplementation<IReportView> implements ReportPresenter {
    private static final String TAG = "ReportPresenterImplemen";
    private String reportText;
    private GetSelectedProjectUseCase getSelectedProjectUseCase;
    private final TwoLinesReport twoLinesReport = new TwoLinesReport();

    public ReportPresenterImplementation(AppBridge appBridge) {
        super(appBridge);
    }

    @Override
    public void bindView(IReportView iReportView) {
        super.bindView(iReportView);
        getSelectedProjectUseCase = new GetSelectedProjectUseCase(appBridge.getRepositoryManager().getRepository());
        twoLinesReport.setCurrentDate(System.currentTimeMillis());
        twoLinesReport.setGreeting(new Greeting("Привет", null, "))"));
        twoLinesReport.setSpentTime(new SpentTime(8, "часов"));
        reportText = twoLinesReport.toString();
        getView().setReportText(reportText);
        getSelectedProjectUseCase.execute(new SelectedProjectObserver(), null);
    }

    @Override
    public void unbindView() {
        super.unbindView();
        getSelectedProjectUseCase.dispose();
    }

    public void onReportClick() {
        getView().copyToClipBoard(reportText);
        getView().showToast(R.string.report_copied_to_clipboard);
    }

    public void onTextChanged(String text) {
        reportText = text;
        getView().displayReportText(reportText);
    }

    private class SelectedProjectObserver extends DisposableObserver<Project> {

        @Override
        public void onNext(@NonNull Project project) {
            twoLinesReport.setSpendingTimeCause(project);
            project.setName(String.format(getView().getOnString(), project.getName()));
            reportText = twoLinesReport.toString();
            Log.i(TAG, "onNext: " + reportText);
            getView().setReportText(twoLinesReport.toString());
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG, "onError: ", e);
        }

        @Override
        public void onComplete() {

        }
    }
}
