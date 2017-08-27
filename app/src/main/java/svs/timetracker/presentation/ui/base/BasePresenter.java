package svs.timetracker.presentation.ui.base;

import java.lang.ref.WeakReference;

/**
 * Created by Black on 29.07.2017.
 */

public abstract class BasePresenter<M, V> {
    protected M mModel;
    private WeakReference<V> mView;

    public void setModel(M model) {
        resetState();
        mModel = model;
        if (setupIsDone()) {
            updateView();
        }
    }

    protected void resetState() {

    }

    protected boolean setupIsDone() {
        return view() != null && mModel != null;
    }

    public void bindView(V v) {
        mView = new WeakReference<>(v);
        if (setupIsDone()) {
            updateView();
        }
    }

    public void unbindView() {
        mView = null;
    }

    protected V view() {
        if (mView == null) {
            return null;
        } else {
            return mView.get();
        }
    }

    protected abstract void updateView();
}
