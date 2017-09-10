package svs.timetracker.presentation.ui.projects.editable_list;

import java.util.List;

import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.BaseView;

public interface ProjectsEditableListView extends BaseView {
    void setProjects(List<Project> projects);
}
