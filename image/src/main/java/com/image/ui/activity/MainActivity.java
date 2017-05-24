package com.image.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.framework.base.BaseActivity;
import com.framework.base.mvp.BaseModel;
import com.image.R;
import com.image.manager.ApiConfig;
import com.image.mvp.presenter.MainPresenterImpl;
import com.image.mvp.view.ViewManager;
import com.image.ui.fragment.CollectionListFragment;
import com.image.ui.fragment.TabFragment;

public class MainActivity extends BaseActivity<MainPresenterImpl>
        implements NavigationView.OnNavigationItemSelectedListener, ViewManager.MainView {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private AppBarLayout appBarLayout;
    private AppBarLayout.LayoutParams layoutParams;
    private String searchType = null;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        layoutParams = (AppBarLayout.LayoutParams) appBarLayout.getChildAt(0).getLayoutParams();
        navigationView.setNavigationItemSelectedListener(this);
        toolbar.setTitle(getString(R.string.dbmz_title));
        setSupportActionBar(toolbar);
        replaceFragment(TabFragment.newInstance(searchType = ApiConfig.Type.DOU_BAN_MEI_ZI));
    }

    @Override
    protected void initById() {
        appBarLayout = getView(R.id.appbar);
        toolbar = getView(R.id.toolbar);
        drawerLayout = getView(R.id.dl_layout);
        navigationView = getView(R.id.navigationview);
    }

    @Override
    protected MainPresenterImpl initPresenterImpl() {
        return null;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        toolbar.setTitle(item.getTitle());
        mPresenter.switchId(item.getItemId());
        if (item.getItemId() == R.id.collection) {
            replaceFragment(CollectionListFragment.newInstance());
            layoutParams.setScrollFlags(0);
        } else {
            replaceFragment(TabFragment.newInstance(searchType));
            layoutParams.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS);
        }
        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public void switchDouban() {
        searchType = ApiConfig.Type.DOU_BAN_MEI_ZI;
    }

    @Override
    public void switchMZitu() {
        searchType = ApiConfig.Type.M_ZI_TU;
    }

    @Override
    public void switchMM() {
        searchType = ApiConfig.Type.MM;
    }

    @Override
    public void switchMeiZiTu() {
        searchType = ApiConfig.Type.MEIZITU;
    }

    @Override
    public void switch7KK() {
        searchType = ApiConfig.Type.KK;
    }

    @Override
    public void switchCollection() {
        searchType = ApiConfig.Type.COLLECTION;
    }

    @Override
    public void netWorkSuccess(BaseModel data) {

    }

    @Override
    public void netWorkError() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
