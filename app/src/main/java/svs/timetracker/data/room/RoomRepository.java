package svs.timetracker.data.room;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import svs.timetracker.data.Repository;
import svs.timetracker.domain.model.Project;


public class RoomRepository implements Repository {
    private ProjectDao projectDao;

    public RoomRepository(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @Override
    public Flowable<List<Project>> getProjects() {
        return projectDao.getAll();
    }

    @Override
    public Single<Project> getProjectByName(String name) {
        return projectDao.getByName(name);
    }

    @Override
    public void updateOrCreateProject(final @NonNull Project project) {
        // TODO: 10.09.2017 replace with use case
        Observable.just(project).subscribeOn(Schedulers.io()).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                projectDao.insert(project);
            }
        }).subscribe();
    }

    @Override
    public void updateProject(final @NonNull Project project) {
        // TODO: 10.09.2017 replace with use case
        Observable.just(project).subscribeOn(Schedulers.io()).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                projectDao.update(project);
            }
        }).subscribe();
    }

    @Override
    public void deleteProject(@NonNull final Project project) {
        // TODO: 10.09.2017 replace with use case
        Observable.just(project).subscribeOn(Schedulers.io()).doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                projectDao.delete(project);
            }
        }).subscribe();
    }

    @Override
    public Flowable<Project> getSelectedProject() {
        return projectDao.getLastSelectedProject();
    }
}
