package svs.timetracker.presentation.ui.report;

import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import svs.timetracker.R;
import svs.timetracker.core.AppBridge;
import svs.timetracker.domain.model.Greeting;
import svs.timetracker.domain.model.PreviousWorkDayTwoLinesReport;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.model.RussianEndingSpentTime;
import svs.timetracker.domain.use_case.GetProjectByNameUseCase;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;

public class ReportPresenterImplementation extends BasePresenterImplementation<IReportView> implements ReportPresenter {
    private static final String TAG = "ReportPresenterImplemen";
    // TODO: 17.09.2017 customize it
    private static final String GREETING_TEXT = "Привет";
    // TODO: 17.09.2017 customize it
    private static final String HOURS_TEXT = "часов";
    private final String emojiItem;
    private PreviousWorkDayTwoLinesReport yesterdayTwoLinesReport;
    private String reportText;
    private GetProjectByNameUseCase getProjectByNameUseCase;

    public ReportPresenterImplementation(AppBridge appBridge) {
        super(appBridge);
        emojiItem = appBridge.getSharedPreferences().getEmojiItem();
        getProjectByNameUseCase = new GetProjectByNameUseCase(appBridge.getRepositoryManager().getRepository());
    }

    @Override
    public void bindView(IReportView iReportView) {
        super.bindView(iReportView);
        yesterdayTwoLinesReport = new PreviousWorkDayTwoLinesReport();
        yesterdayTwoLinesReport.setCurrentDate(System.currentTimeMillis());
        final int lastSelectedEmojiNumber = appBridge.getSharedPreferences().getLastSelectedEmojiNumber();
        final int lastSelectedHours = appBridge.getSharedPreferences().getLastSelectedHours();
        yesterdayTwoLinesReport.setGreeting(new Greeting(GREETING_TEXT, null, getEmojiString(lastSelectedEmojiNumber)));
        yesterdayTwoLinesReport.setSpentTime(new RussianEndingSpentTime(lastSelectedHours));
        reportText = yesterdayTwoLinesReport.toString();
        final String selectedProjectName = appBridge.getSharedPreferences().getSelectedProject();
        if (selectedProjectName != null) {
            getProjectByNameUseCase.execute(new ProjectObserver(), GetProjectByNameUseCase.Params.withFileName(selectedProjectName));
        }
        getView().setEmojiNumber(lastSelectedEmojiNumber);
        getView().setHours(lastSelectedHours);
        getView().displayReportText(reportText);
    }

    @Override
    public void unbindView() {
        super.unbindView();
        getProjectByNameUseCase.dispose();
    }

    public void onReportClick() {
        getView().copyToClipBoard(reportText);
        getView().showToast(R.string.report_copied_to_clipboard);
    }

    @Override
    public void onEmojiNumberChanged(final int number) {
        yesterdayTwoLinesReport.setGreeting(new Greeting(GREETING_TEXT, null, getEmojiString(number)));
        reportText = yesterdayTwoLinesReport.toString();
        getView().displayReportText(reportText);
        appBridge.getSharedPreferences().setLastSelectedEmojiNumber(number);
    }

    @Override
    public void onHoursNumberChanged(final int hours) {
        yesterdayTwoLinesReport.setSpentTime(new RussianEndingSpentTime(hours));
        reportText = yesterdayTwoLinesReport.toString();
        getView().displayReportText(reportText);
        appBridge.getSharedPreferences().setLastSelectedHours(hours);
    }

    private String getEmojiString(int itemsNumber) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < itemsNumber; i++) {
            stringBuilder.append(emojiItem);
        }
        return stringBuilder.toString();
    }

    private class ProjectObserver extends DisposableObserver<Project> {

        @Override
        public void onNext(@NonNull Project project) {
            yesterdayTwoLinesReport.setSpendingTimeCause(project);
            project.setName(String.format(getView().getOnString(), project.getName()));
            reportText = yesterdayTwoLinesReport.toString();
            Log.i(TAG, "onNext: " + reportText);
            getView().displayReportText(yesterdayTwoLinesReport.toString());
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
