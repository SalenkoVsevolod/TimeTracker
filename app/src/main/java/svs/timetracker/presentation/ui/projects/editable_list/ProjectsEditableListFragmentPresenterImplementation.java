package svs.timetracker.presentation.ui.projects.editable_list;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import svs.timetracker.core.AppBridge;
import svs.timetracker.domain.model.Project;
import svs.timetracker.domain.use_case.GetProjectsUseCase;
import svs.timetracker.presentation.ui.base.BasePresenterImplementation;


public class ProjectsEditableListFragmentPresenterImplementation extends BasePresenterImplementation<ProjectsEditableListView> implements ProjectsEditableListFragmentPresenter {
    private static final String TAG = "ProjectsEditableListFra";
    private GetProjectsUseCase getProjectsUseCase;

    protected ProjectsEditableListFragmentPresenterImplementation(AppBridge appBridge) {
        super(appBridge);
        getProjectsUseCase = new GetProjectsUseCase(appBridge.getRepositoryManager().getRepository());
    }

    @Override
    public void bindView(ProjectsEditableListView projectsEditableListView) {
        super.bindView(projectsEditableListView);
        getProjectsUseCase.execute(new ProjectsObserver(), null);
    }

    @Override
    public void unbindView() {
        super.unbindView();
        getProjectsUseCase.dispose();
    }


    @Override
    public void onProjectDelete(Project project) {
        appBridge.getRepositoryManager().getRepository().deleteProject(project);
    }

    @Override
    public void onProjectAdded(Project project) {
        appBridge.getRepositoryManager().getRepository().updateOrCreateProject(project);
    }

    @Override
    public void onProjectRestore(Project project) {
        onProjectAdded(project);
    }

    @Override
    public void onProjectClicked(Project project) {
        project.setCurrentSelected(true);
        appBridge.getRepositoryManager().getRepository().updateOrCreateProject(project);
    }

    @Override
    public void onProjectUnselected(Project project) {
        project.setCurrentSelected(false);
        appBridge.getRepositoryManager().getRepository().updateProject(project);
    }

    private final class ProjectsObserver extends DisposableObserver<List<Project>> {


        @Override
        public void onNext(@NonNull List<Project> projects) {
            getView().setProjects(projects);
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    }
}
