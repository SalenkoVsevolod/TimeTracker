package svs.timetracker.presentation.ui.projects;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.projects.editable_list.ProjectsEditableListFragment;

public class ProjectsFragment extends BaseFragment {
    @BindView(R.id.fragment_projects_container) FrameLayout projectsContainer;

    public static ProjectsFragment newInstance() {
        Bundle args = new Bundle();
        ProjectsFragment fragment = new ProjectsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ProjectsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_projects;
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

    @Override
    public void onStart() {
        super.onStart();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_projects_container, ProjectsEditableListFragment.newInstance())
                .commit();
    }
}
