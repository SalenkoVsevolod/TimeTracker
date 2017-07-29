package svs.timetracker.ui.report;

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
import svs.timetracker.ui.base.BaseFragment;


public class ReportFragment extends BaseFragment implements IReportView {

    @BindView(R.id.fragment_report_text) TextView mReportTextView;
    private ReportPresenter mReportPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_report;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReportPresenter = new ReportPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mReportPresenter.bindView(this);
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
        mReportTextView.setText(text);
    }

    @Override
    public void copyToClipBoard(String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.time_report), text);
        clipboard.setPrimaryClip(clip);
    }
}
