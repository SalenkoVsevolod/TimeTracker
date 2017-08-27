package svs.timetracker.presentation.ui.navigation;

import android.graphics.drawable.Drawable;

public class NavigationItem {
    private String mItemText;
    private Drawable mIcon;

    public NavigationItem() {
    }

    public NavigationItem(String text) {
        mItemText = text;
    }

    public String getItemText() {
        return mItemText;
    }

    public void setItemText(String itemText) {
        mItemText = itemText;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setmIcon(Drawable icon) {
        mIcon = icon;
    }
}
