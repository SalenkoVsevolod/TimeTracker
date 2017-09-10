package svs.timetracker.presentation.ui.projects.editable_list;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.domain.model.Project;
import svs.timetracker.presentation.ui.base.dialogs.DialogListener;
import svs.timetracker.presentation.ui.base.dialogs.EditTextDialog;
import svs.timetracker.presentation.ui.base.list.OnItemClickListener;
import svs.timetracker.presentation.ui.base.list.list_fragments.BaseAddableRemovableListFragment;
import svs.timetracker.presentation.ui.projects.ProjectsRecyclerAdapter;

//TODO create abstract editable list fragment and move all displaying logic there
public class ProjectsEditableListFragment
        extends BaseAddableRemovableListFragment<Project, ProjectsRecyclerAdapter, ProjectsRecyclerAdapter.ProjectsViewHolder>
        implements ProjectsEditableListView, OnItemClickListener<Project>, ProjectsRecyclerAdapter.OnProjectUnselectedListener {
    private ProjectsEditableListFragmentPresenter presenter;

    public ProjectsEditableListFragment() {
        // Required empty public constructor
    }

    public static ProjectsEditableListFragment newInstance() {
        Bundle args = new Bundle();
        ProjectsEditableListFragment fragment = new ProjectsEditableListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addable_list;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProjectsEditableListFragmentPresenterImplementation(appBridge);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.bindView(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unbindView();
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
    protected ProjectsRecyclerAdapter initAdapter() {
        final ProjectsRecyclerAdapter adapter = new ProjectsRecyclerAdapter(getActivity(), new ArrayList<Project>());
        adapter.setOnItemClickListener(this);
        adapter.setOnProjectUnselectedListener(this);
        return adapter;
    }

    @Override
    protected void onAddClicked() {
        showCreateProjectDialog();
    }

    @Override
    public void setProjects(List<Project> projects) {
        getAdapter().setItems(projects);
    }

    private void showCreateProjectDialog() {
        final EditTextDialog editTextDialog = EditTextDialog.newInstance(getString(R.string.create_project_title), getString(R.string.project_name_hint), null);
        editTextDialog.setListener(new DialogListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onOk() {
                presenter.onProjectAdded(new Project(editTextDialog.getText()));
            }

            @Override
            public void onDismiss() {

            }
        });
        editTextDialog.show(getFragmentManager(), null);
    }

    @Override
    protected void onItemDelete(@Nullable Project project) {
        presenter.onProjectDelete(project);
    }

    @Override
    protected void onItemRestore(@NonNull Project project) {
        presenter.onProjectRestore(project);
    }

    @Override
    protected String getItemName() {
        return getString(R.string.project);
    }

    @Override
    public void onItemClicked(int position, Project project) {
        presenter.onProjectClicked(project);
        getAdapter().setProjectSelected(project.getName());
    }

    @Override
    public void onProjectUnselected(Project project) {
        presenter.onProjectUnselected(project);
    }
}
