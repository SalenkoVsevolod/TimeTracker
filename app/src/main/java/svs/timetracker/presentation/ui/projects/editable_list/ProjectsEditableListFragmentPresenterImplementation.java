package svs.timetracker.presentation.ui.projects.editable_list;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.observers.DisposableObserver;
import svs.timetracker.core.AppBridge;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.use_case.GetProjectsUseCase;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;


public class ProjectsEditableListFragmentPresenterImplementation extends BasePresenterImplementation<ProjectsEditableListView> implements ProjectsEditableListFragmentPresenter {
    private GetProjectsUseCase getProjectsUseCase;
    private String selectedProjectName;

    protected ProjectsEditableListFragmentPresenterImplementation(@NonNull final AppBridge appBridge) {
        super(appBridge);
        getProjectsUseCase = new GetProjectsUseCase(appBridge.getRepositoryManager().getRepository());
    }

    @Override
    public void bindView(ProjectsEditableListView projectsEditableListView) {
        super.bindView(projectsEditableListView);
        selectedProjectName = appBridge.getSharedPreferences().getSelectedProject();
        if (selectedProjectName != null) {
            getView().setSelectedProject(selectedProjectName);
        }
        getProjectsUseCase.execute(new ProjectsObserver(), null);
    }

    @Override
    public void unbindView() {
        super.unbindView();
        getProjectsUseCase.dispose();
    }


    @Override
    public void onProjectDelete(@NonNull final Project project) {
        if (project.getName().equals(selectedProjectName)) {
            selectedProjectName = null;
            appBridge.getSharedPreferences().setSelectedProject(null);
        }
        appBridge.getRepositoryManager().getRepository().deleteProject(project);
    }

    @Override
    public void onProjectAdded(@NonNull final Project project) {
        appBridge.getRepositoryManager().getRepository().updateOrCreateProject(project);
    }

    @Override
    public void onProjectRestore(@NonNull final Project project) {
        onProjectAdded(project);
    }

    @Override
    public void onProjectSelected(@NonNull final Project project) {
        selectedProjectName = project.getName();
        appBridge.getSharedPreferences().setSelectedProject(project.getName());
    }

    private final class ProjectsObserver extends DisposableObserver<List<Project>> {


        @Override
        public void onNext(@NonNull final List<Project> projects) {
            getView().setProjects(projects);
        }

        @Override
        public void onError(@NonNull final Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
