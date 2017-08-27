package svs.timetracker.presentation.ui.navigation;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import svs.timetracker.R;
import svs.timetracker.presentation.ui.base.BaseActivity;
import svs.timetracker.presentation.ui.base.BaseFragment;


public abstract class BaseNavigationActivity extends BaseActivity {
    @BindView(R.id.base_navigation_drawer_layout) protected DrawerLayout mDrawerLayout;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    protected ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_navigation;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initNavigationToggle();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getFragmentManager().beginTransaction().add(R.id.left_drawer, getNavigationFragment()).commit();
        initContent();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(getNavigationFragment().getView());
        //TODO hide all toolbar actions
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void initContent() {
        final BaseFragment fragment = getContentFragment();
        if (fragment != null) {
            getFragmentManager().beginTransaction().add(R.id.base_navigation_content, getContentFragment()).commit();
        }
    }

    protected void initNavigationToggle() {
        if (getSupportActionBar() != null) {
            mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation, R.string.navigation) {
                public void onDrawerClosed(View view) {
                    super.onDrawerClosed(view);
                    invalidateOptionsMenu();
                }

                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                    invalidateOptionsMenu();
                }
            };
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    protected abstract BaseFragment getContentFragment();

    protected abstract BaseNavigationFragment getNavigationFragment();
}
