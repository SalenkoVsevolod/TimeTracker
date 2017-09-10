package svs.timetracker.presentation.ui.report;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;


public class ReportFragment extends BaseFragment implements IReportView {
    private static final String REPORT_TEXT = "reportText";
    @BindView(R.id.fragment_report_text) TextView mReportTextView;
    private ReportPresenter mReportPresenter;
    private String reportText;

    public static ReportFragment getInstance(String reportText) {
        final ReportFragment res = new ReportFragment();
        Bundle args = new Bundle();
        args.putString(REPORT_TEXT, reportText);
        res.setArguments(args);
        return res;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            reportText = getArguments().getString(REPORT_TEXT);
            mReportPresenter = new ReportPresenterImplementation(appBridge);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mReportPresenter.bindView(this);
        if (reportText != null) {
            mReportPresenter.onTextChanged(reportText);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mReportPresenter.unbindView();
    }

    @OnClick(R.id.fragment_report_text)
    public void onReportClick() {
        mReportPresenter.onReportClick();
    }

    @Override
    public void setReportText(String text) {
        this.reportText = text;
        mReportPresenter.onTextChanged(text);
    }

    @Override
    public void displayReportText(String text) {
        mReportTextView.setText(text);
    }

    @Override
    public String getOnString() {
        return getString(R.string.on);
    }

    @Override
    public void copyToClipBoard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.time_report), text);
        clipboard.setPrimaryClip(clip);
    }
}
