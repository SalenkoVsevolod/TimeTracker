package svs.timetracker.presentation.ui.projects;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import svs.timetracker.R;
import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.list.BaseAdapter;
import svs.timetracker.presentation.ui.base.list.BaseViewHolder;
import svs.timetracker.presentation.ui.base.list.OnItemClickListener;


public class ProjectsRecyclerAdapter extends BaseAdapter<Project, ProjectsRecyclerAdapter.ProjectsViewHolder> {
    private static final String TAG = "ProjectsRecyclerAdapter";
    private Context context;
    private Project currentSelectedProject;
    private OnProjectUnselectedListener onProjectUnselectedListener;

    public ProjectsRecyclerAdapter(Context context, List<Project> projectList) {
        super(projectList);
        this.context = context;
    }

    public OnItemClickListener<Project> onItemClickListener;

    @Override
    public void setItems(List<Project> projects) {
        //TODO replace with DefUtils
        if (items == null) {
            items = projects;
        } else {
            items.clear();
            items.addAll(projects);
        }
        notifyDataSetChanged();
    }

    @Override
    public ProjectsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProjectsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false));
    }

    @Override
    public void onBindViewHolder(ProjectsViewHolder holder, int position) {
        final Project project = items.get(holder.getAdapterPosition());
        holder.projectItemTextView.setText(project.getName());
        if (project.isCurrentSelected()) {
            holder.projectItemTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSelectedProject));
        } else {
            holder.projectItemTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorProjectsBackground));
        }
    }

    public void setOnItemClickListener(OnItemClickListener<Project> onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setProjectSelected(@NonNull final String projectName) {
        if (currentSelectedProject != null) {
            currentSelectedProject.setCurrentSelected(false);
            final int previousPosition = getProjectPosition(currentSelectedProject.getName());
            if (onProjectUnselectedListener != null) {
                onProjectUnselectedListener.onProjectUnselected(items.get(previousPosition));
            }
            notifyItemChanged(previousPosition);
        }
        currentSelectedProject = items.get(getProjectPosition(projectName));
        currentSelectedProject.setCurrentSelected(true);
        notifyItemChanged(getProjectPosition(currentSelectedProject.getName()));
    }

    public int getProjectPosition(@NonNull final String projectName) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(projectName)) {
                return i;
            }
        }
        return 0;
    }

    public void setOnProjectUnselectedListener(OnProjectUnselectedListener onProjectUnselectedListener) {
        this.onProjectUnselectedListener = onProjectUnselectedListener;
    }

    public class ProjectsViewHolder extends BaseViewHolder {
        @BindView(R.id.projectItemText) TextView projectItemTextView;

        public ProjectsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(getAdapterPosition(), items.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnProjectUnselectedListener {
        void onProjectUnselected(Project project);
    }
}
