package svs.timetracker.domain.use_case;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import svs.timetracker.data.Repository;
import svs.timetracker.domain.model.Project;

public class GetSelectedProjectUseCase extends UseCase<Project, Void> {
    private Repository repository;

    public GetSelectedProjectUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Project> buildObservable(Void aVoid) {
        return Observable.create(new ObservableOnSubscribe<Project>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Project> emitter) throws Exception {
                repository.getSelectedProject().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSubscriber<Project>() {
                    @Override
                    public void onNext(Project project) {
                        emitter.onNext(project);
                    }

                    @Override
                    public void onError(Throwable t) {
                        emitter.onError(t);
                    }

                    @Override
                    public void onComplete() {
                        emitter.onComplete();
                    }
                });
            }
        });
    }
}
