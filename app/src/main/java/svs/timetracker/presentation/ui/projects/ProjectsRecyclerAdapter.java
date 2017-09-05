package svs.timetracker.presentation.ui.projects;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.domain.model.Project;


public class ProjectsRecyclerAdapter extends RecyclerView.Adapter<ProjectsRecyclerAdapter.ProjectsViewHolder> {
    private List<Project> projectList;

    public ProjectsRecyclerAdapter(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void setItems(List<Project> projects) {
        //TODO replace with DefUtils
        projectList.clear();
        projectList.addAll(projects);
        notifyDataSetChanged();
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {
        final Project project = projectList.get(holder.getAdapterPosition());
        holder.projectItemTextView.setText(project.getName());
    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public class ProjectsViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.projectItemText) TextView projectItemTextView;

        public ProjectsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
