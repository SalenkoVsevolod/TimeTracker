package svs.timetracker.presentation.ui.base;

import java.lang.ref.WeakReference;


public abstract class BasePresenter<V> {
    private WeakReference<V> view;

    public void bindView(V v) {
        view = new WeakReference<>(v);
    }

    public void unbindView() {
        view = null;
    }

    protected V view() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }
}
