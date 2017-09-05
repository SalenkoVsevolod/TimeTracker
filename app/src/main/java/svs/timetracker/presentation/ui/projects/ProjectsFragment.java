package svs.timetracker.presentation.ui.projects;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.BaseFragment;


public class ProjectsFragment extends BaseFragment {
    @BindView(R.id.projectsRecycler) RecyclerView projectsRecyclerView;
    private ProjectsRecyclerAdapter projectsRecyclerAdapter;
    private final List<Project> projectList = new ArrayList<>();

    public ProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projects;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        projectsRecyclerAdapter = new ProjectsRecyclerAdapter(projectList);
        projectsRecyclerView.setAdapter(projectsRecyclerAdapter);
        projectsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        if (view != null) {
            ButterKnife.bind(this, view);
        }
        return view;
    }

}
