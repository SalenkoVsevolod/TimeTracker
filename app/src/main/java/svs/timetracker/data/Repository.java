package svs.timetracker.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import svs.timetracker.domain.model.Project;

public interface Repository {
    Single<List<Project>> getProjects();

    Single<Project> getProjectByName(String name);

    Completable updateOrCreateProject(Project project);

    Completable deleteProject(Project project);
}
