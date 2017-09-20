package svs.timetracker.domain.use_case;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import svs.timetracker.data.Repository;
import svs.timetracker.domain.model.Project;

public class GetProjectByNameUseCase extends UseCase<Project, GetProjectByNameUseCase.Params> {
    private Repository repository;

    public GetProjectByNameUseCase(@NonNull final Repository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Project> buildObservable(@NonNull final Params params) {
        return Observable.create(new ObservableOnSubscribe<Project>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<Project> emitter) throws Exception {
                repository.getProjectByName(params.fileName)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new DisposableSingleObserver<Project>() {
                            @Override
                            public void onSuccess(@NonNull Project project) {
                                emitter.onNext(project);
                                emitter.onComplete();
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                emitter.onError(e);
                            }
                        });
            }
        });
    }

    public static final class Params {
        private String fileName;

        public Params(@NonNull final String fileName) {
            this.fileName = fileName;
        }

        public static Params withFileName(@NonNull final String fileName) {
            return new Params(fileName);
        }
    }
}
