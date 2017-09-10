package svs.timetracker.domain.use_case;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import svs.timetracker.data.Repository;
import svs.timetracker.domain.model.Project;

public class GetProjectsUseCase extends UseCase<List<Project>, Void> {
    private Repository repository;

    public GetProjectsUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<List<Project>> buildObservable(Void aVoid) {
        return Observable.create(new ObservableOnSubscribe<List<Project>>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<List<Project>> emitter) throws Exception {
                repository.getProjects().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSubscriber<List<Project>>() {
                    @Override
                    public void onNext(List<Project> projects) {
                        emitter.onNext(projects);
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
