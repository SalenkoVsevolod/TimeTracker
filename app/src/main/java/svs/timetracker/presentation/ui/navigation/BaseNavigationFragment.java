package svs.timetracker.presentation.ui.navigation;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;

public abstract class BaseNavigationFragment extends BaseFragment implements NavigationAdapter.OnNavigationItemClicked {
    @BindView(R.id.navigation_items_recycler) RecyclerView mNavigationRecycler;
    private NavigationAdapter mAdapter;

    public BaseNavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setAdapter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_base_navigation;
    }

    protected void setAdapter() {
        mAdapter = new NavigationAdapter(this);
        mNavigationRecycler.setAdapter(mAdapter);
        mNavigationRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setNavigationItems(List<NavigationItem> items) {
        mAdapter.setItems(items);
    }
}
