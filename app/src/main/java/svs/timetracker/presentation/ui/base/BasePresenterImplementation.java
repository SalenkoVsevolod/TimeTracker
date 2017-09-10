package svs.timetracker.presentation.ui.base;

import java.lang.ref.WeakReference;

import svs.timetracker.core.AppBridge;


public abstract class BasePresenterImplementation<V extends BaseView> implements BasePresenter<V> {
    protected AppBridge appBridge;

    private WeakReference<V> view;

    protected BasePresenterImplementation(AppBridge appBridge) {
        this.appBridge = appBridge;
    }

    public void bindView(V v) {
        view = new WeakReference<>(v);
    }

    public void unbindView() {
        view = null;
    }

    protected V getView() {
        if (view == null) {
            return null;
        } else {
            return view.get();
        }
    }
}
