package svs.timetracker.presentation.ui.projects.editable_list;

import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.BasePresenter;

public interface ProjectsEditableListFragmentPresenter extends BasePresenter<ProjectsEditableListView> {
    void onProjectDelete(Project project);

    void onProjectAdded(Project project);

    void onProjectRestore(Project project);

    void onProjectClicked(Project project);

    void onProjectUnselected(Project project);
}
