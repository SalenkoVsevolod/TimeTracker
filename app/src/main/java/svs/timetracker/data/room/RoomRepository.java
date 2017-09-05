package svs.timetracker.data.room;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import svs.timetracker.data.Repository;
import svs.timetracker.domain.model.Project;


public class RoomRepository implements Repository {
    private ProjectDao projectDao;

    public RoomRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Single<List<Project>> getProjects() {
        return projectDao.getAll();
    }

    @Override
    public Single<Project> getProjectByName(String name) {
        return projectDao.getByName(name);
    }

    @Override
    public Completable updateOrCreateProject(final @NonNull Project project) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                projectDao.insert(project);
                emitter.onComplete();
            }
        });
    }

    @Override
    public Completable deleteProject(@NonNull final Project project) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull CompletableEmitter emitter) throws Exception {
                projectDao.delete(project);
                emitter.onComplete();
            }
        });
    }
}
