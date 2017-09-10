package svs.timetracker.presentation.ui.base;

public interface BasePresenter<T extends BaseView> {
    void bindView(T t);

    void unbindView();
}
