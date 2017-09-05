package svs.timetracker.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import svs.timetracker.domain.model.Project;

public interface Repository {
    Observable<List<Project>> observeProjects();

    Observable<Project> observeProject(String name);

    Single<Project> updateOrCreateProject(Project project);

    Completable deleteProject(String name);
}
