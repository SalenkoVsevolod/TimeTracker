package svs.timetracker.presentation.ui.base.list;

import android.support.v7.widget.RecyclerView;

import java.util.List;


public abstract class BaseAdapter<Item, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> {
    protected List<Item> items;

    public BaseAdapter(List<Item> items) {
        setItems(items);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public abstract void setItems(List<Item> items);

    public List<Item> getItems() {
        return items;
    }
}
