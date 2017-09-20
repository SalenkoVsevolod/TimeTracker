package svs.timetracker.presentation.ui.projects.editable_list;

import android.support.annotation.NonNull;

import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.BasePresenter;

public interface ProjectsEditableListFragmentPresenter extends BasePresenter<ProjectsEditableListView> {
    void onProjectDelete(@NonNull Project project);

    void onProjectAdded(@NonNull Project project);

    void onProjectRestore(@NonNull Project project);

    void onProjectSelected(@NonNull Project project);
}
