package svs.timetracker.domain.use_case;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    protected abstract Observable<T> buildObservable(Params params);

    public void execute(DisposableObserver<T> disposableObserver, Params params) {
        final Observable<T> observable = buildObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        addDisposable(observable.subscribeWith(disposableObserver));
    }

    public void executeSync(Params params) {
        buildObservable(params)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }
}