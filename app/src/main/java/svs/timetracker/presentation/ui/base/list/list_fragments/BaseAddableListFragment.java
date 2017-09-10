package svs.timetracker.presentation.ui.base.list.list_fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseFragment;
import svs.timetracker.presentation.ui.base.list.BaseAdapter;
import svs.timetracker.presentation.ui.base.list.BaseViewHolder;


public abstract class BaseAddableListFragment<Item, Adapter extends BaseAdapter<Item, VH>, VH extends BaseViewHolder> extends BaseFragment {
    @BindView(R.id.itemsRecycler) protected RecyclerView itemsRecyclerView;
    private Adapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_addable_list;
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.adapter = initAdapter();
        itemsRecyclerView.setAdapter(adapter);
        itemsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    protected abstract Adapter initAdapter();

    protected Adapter getAdapter() {
        return adapter;
    }

    @OnClick(R.id.addFab)
    protected abstract void onAddClicked();
}
