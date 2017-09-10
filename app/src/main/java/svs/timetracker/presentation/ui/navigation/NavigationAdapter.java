package svs.timetracker.presentation.ui.navigation;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;


public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigatiionVH> {
    private List<NavigationItem> mItems;
    private OnNavigationItemClicked mOnNavigationItemClicked;

    public NavigationAdapter(OnNavigationItemClicked onNavigationItemClicked) {
        mOnNavigationItemClicked = onNavigationItemClicked;
    }

    public void setItems(final List<NavigationItem> items) {
        if (mItems == null) {
            mItems = items;
            notifyDataSetChanged();
            return;
        }
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
            @Override
            public int getOldListSize() {
                return mItems.size();
            }

            @Override
            public int getNewListSize() {
                return items.size();
            }

            @Override
            public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                return mItems.get(oldItemPosition).getItemText().equals(items.get(newItemPosition).getItemText());
            }

            @Override
            public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                return areItemsTheSame(oldItemPosition, newItemPosition) /*&& mItems.get(oldItemPosition).getIcon().equals(items.get(newItemPosition).getIcon())*/;
            }
        });
        result.dispatchUpdatesTo(this);
    }

    @Override
    public NavigatiionVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigatiionVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_navigation, parent, false));
    }

    @Override
    public void onBindViewHolder(NavigatiionVH holder, int position) {
        final NavigationItem item = mItems.get(holder.getAdapterPosition());
        holder.item.setText(item.getItemText());
        if (item.getIcon() != null) {
            holder.item.setCompoundDrawables(item.getIcon(), null, null, null);
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class NavigatiionVH extends RecyclerView.ViewHolder {
        @BindView(R.id.navigation_item) TextView item;

        public NavigatiionVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnNavigationItemClicked.onNavigationItemClicked(getAdapterPosition());
                }
            });
        }
    }

    interface OnNavigationItemClicked {
        void onNavigationItemClicked(int position);
    }
}
