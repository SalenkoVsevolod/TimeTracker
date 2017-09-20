package svs.timetracker.data;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import svs.timetracker.domain.model.Project;

public interface Repository {
    Flowable<List<Project>> getProjects();

    Single<Project> getProjectByName(String name);

    void updateOrCreateProject(Project project);

    void updateProject(Project project);

    void deleteProject(Project project);
}
