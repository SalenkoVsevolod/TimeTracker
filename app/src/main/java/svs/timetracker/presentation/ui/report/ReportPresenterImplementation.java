package svs.timetracker.presentation.ui.report;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import svs.timetracker.R;
import svs.timetracker.core.AppBridge;
import svs.timetracker.domain.model.Greeting;
import svs.timetracker.domain.model.PreviousWorkDayTwoLinesReport;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.model.SpentTime;
import svs.timetracker.domain.use_case.GetSelectedProjectUseCase;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;

public class ReportPresenterImplementation extends BasePresenterImplementation<IReportView> implements ReportPresenter {
    private static final String TAG = "ReportPresenterImplemen";
    // TODO: 17.09.2017 customize it
    private static final String GREETING_TEXT = "Привет";
    // TODO: 17.09.2017 customize it
    private static final String HOURS_TEXT = "часов";
    private final PreviousWorkDayTwoLinesReport yesterdayTwoLinesReport = new PreviousWorkDayTwoLinesReport();
    private final String emojiItem;
    private String reportText;
    private GetSelectedProjectUseCase getSelectedProjectUseCase;

    public ReportPresenterImplementation(AppBridge appBridge) {
        super(appBridge);
        emojiItem = appBridge.getSharedPreferences().getEmojiItem();
    }

    @Override
    public void bindView(IReportView iReportView) {
        super.bindView(iReportView);
        getSelectedProjectUseCase = new GetSelectedProjectUseCase(appBridge.getRepositoryManager().getRepository());
        yesterdayTwoLinesReport.setCurrentDate(System.currentTimeMillis());
        yesterdayTwoLinesReport.setGreeting(new Greeting(GREETING_TEXT, null, getEmojiString(0)));
        yesterdayTwoLinesReport.setSpentTime(new SpentTime(appBridge.getSharedPreferences().getMinHours(), HOURS_TEXT));
        reportText = yesterdayTwoLinesReport.toString();
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

    @Override
    public void onEmojiNumberChanged(final int number) {
        yesterdayTwoLinesReport.setGreeting(new Greeting(GREETING_TEXT, null, getEmojiString(number)));
        getView().setReportText(yesterdayTwoLinesReport.toString());
    }

    @Override
    public void onHoursNumberChanged(final int hours) {
        yesterdayTwoLinesReport.setSpentTime(new SpentTime(hours, HOURS_TEXT));
        getView().setReportText(yesterdayTwoLinesReport.toString());
    }

    private String getEmojiString(int itemsNumber) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < itemsNumber; i++) {
            stringBuilder.append(emojiItem);
        }
        return stringBuilder.toString();
    }

    private class SelectedProjectObserver extends DisposableObserver<Project> {

        @Override
        public void onNext(@NonNull Project project) {
            yesterdayTwoLinesReport.setSpendingTimeCause(project);
            project.setName(String.format(getView().getOnString(), project.getName()));
            reportText = yesterdayTwoLinesReport.toString();
            Log.i(TAG, "onNext: " + reportText);
            getView().setReportText(yesterdayTwoLinesReport.toString());
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
