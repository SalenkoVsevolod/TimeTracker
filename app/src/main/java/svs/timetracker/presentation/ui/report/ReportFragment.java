package svs.timetracker.presentation.ui.report;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;


public class ReportFragment extends BaseFragment implements IReportView {
    private static final String REPORT_TEXT = "reportText";
    @BindView(R.id.fragment_report_text) TextView reportTextView;
    @BindView(R.id.emojiSeekBar) SeekBar emojiSeekBar;
    @BindView(R.id.hoursSeekBar) SeekBar hoursSeekBar;
    private ReportPresenter reportPresenter;
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
            reportPresenter = new ReportPresenterImplementation(appBridge);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emojiSeekBar.setMax(appBridge.getSharedPreferences().getMaxEmojiNumber());
        hoursSeekBar.setMax(appBridge.getSharedPreferences().getMaxHours() - appBridge.getSharedPreferences().getMinHours());
        emojiSeekBar.setOnSeekBarChangeListener(new OnEmojiNumberChangingListener());
        hoursSeekBar.setOnSeekBarChangeListener(new OnHoursChangingListener());
    }

    private int getHoursValue() {
        return hoursSeekBar.getProgress() + appBridge.getSharedPreferences().getMinHours();
    }

    @Override
    public void onResume() {
        super.onResume();
        reportPresenter.bindView(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        reportPresenter.unbindView();
    }

    @OnClick(R.id.fragment_report_text)
    public void onReportClick() {
        reportPresenter.onReportClick();
    }

    @Override
    public void displayReportText(@NonNull String text) {
        reportTextView.setText(text);
    }

    @Override
    public void setEmojiNumber(int emojiNumber) {
        emojiSeekBar.setProgress(emojiNumber);
    }

    @Override
    public void setHours(int hours) {
        hoursSeekBar.setProgress(hours);
    }

    @Override
    public String getOnString() {
        return getString(R.string.on);
    }

    @Override
    public void copyToClipBoard(@NonNull String text) {
        ClipboardManager clipboard = (ClipboardManager) getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(getString(R.string.time_report), text);
        clipboard.setPrimaryClip(clip);
    }

    private final class OnEmojiNumberChangingListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            // TODO: 19.09.2017 show progress popup
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            reportPresenter.onEmojiNumberChanged(seekBar.getProgress());
        }
    }

    private final class OnHoursChangingListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            // TODO: 19.09.2017 show progress popup
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            reportPresenter.onHoursNumberChanged(getHoursValue());
        }
    }
}
