package svs.timetracker.presentation.ui.base.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;


public class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

}
