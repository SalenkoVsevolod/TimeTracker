package svs.timetracker.presentation.ui.base.list.list_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import butterknife.BindString;
import svs.timetracker.R;
import svs.timetracker.domain.model.Nameable;
import svs.timetracker.presentation.ui.base.list.BaseAdapter;
import svs.timetracker.presentation.ui.base.list.BaseViewHolder;

public abstract class BaseAddableRemovableListFragment<Item extends Nameable, Adapter extends BaseAdapter<Item, VH>, VH extends BaseViewHolder> extends BaseAddableListFragment<Item, Adapter, VH> {
    private Item deletedItem;
    @BindString(android.R.string.cancel) String cancelText;
    @BindString(R.string.item_deleted) String itemDeletedText;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                deletedItem = getAdapter().getItems().get(viewHolder.getAdapterPosition());
                onItemDelete(deletedItem);
                showDeletingSnackbar();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(itemsRecyclerView);
    }

    private void showDeletingSnackbar() {
        final View view = getView();
        if (view != null) {
            Snackbar.make(view, String.format(itemDeletedText, getItemName(), deletedItem.getName()), Snackbar.LENGTH_LONG)
                    .setAction(cancelText, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onItemRestore(deletedItem);
                            deletedItem = null;
                        }
                    })
                    .show();
        }
    }

    protected abstract void onItemDelete(@Nullable Item item);

    protected abstract void onItemRestore(@NonNull Item item);

    protected abstract String getItemName();
}
