package svs.timetracker.domain.use_case;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {
    private CompositeDisposable mCompositeDisposable;

    protected abstract Observable<T> buildObservable(Params params);

    public void execute(DisposableObserver<T> disposableObserver, Params params) {
        final Observable<T> observable = buildObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(disposableObserver));
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable = new CompositeDisposable(disposable);
    }

    public void dispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}