package svs.timetracker.presentation.ui.base.dialogs;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import svs.timetracker.R;


public class EditTextDialog extends DialogFragment {
    private static final String TITLE_TAG = "title";
    private static final String HINT_TAG = "hint";
    private static final String TEXT_TAG = "text";
    @BindView(R.id.dialogTitle) TextView titleTextView;
    @BindView(R.id.dialogEditText) EditText editText;

    private String title;
    private String hint;
    private String text;

    private DialogListener dialogListener;

    public static EditTextDialog newInstance(@NonNull final String title, @NonNull final String hint, @Nullable final String text) {
        Bundle args = new Bundle();
        args.putString(TITLE_TAG, title);
        args.putString(HINT_TAG, hint);
        if (text != null) {
            args.putString(TEXT_TAG, text);
        }
        EditTextDialog fragment = new EditTextDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString(TITLE_TAG);
        hint = getArguments().getString(HINT_TAG);
        text = getArguments().getString(TEXT_TAG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_edit_text, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleTextView.setText(title);
        editText.setHint(hint);
        if (text != null) {
            editText.setText(text);
        }
    }


    @OnClick(R.id.dialogOkButton)
    void onOkClicked() {
        dismissAllowingStateLoss();
        dialogListener.onOk();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dialogListener.onDismiss();
    }

    @OnClick(R.id.dialogCancelButton)
    void onCancelClicked() {
        dismissAllowingStateLoss();
        dialogListener.onCancel();
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String text) {
        editText.setText(text);
    }

    public void setListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
