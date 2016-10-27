package com.example.demo;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.demo.about.AboutFragment;
import com.example.demo.materialdesign.MaterialFragment;
import com.example.demo.setting.PrefsFragement;
import com.example.demo.setting.SettingFragement;
import com.example.demo.transition.TransitionFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Fragment currentFragment;
    private int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            currentIndex = 0;
        } else {
            currentIndex = savedInstanceState.getInt(Constans.CURRENT_INDEX);
        }

        Log.d("zhuangsj", "onCreate() currentIndex = " + currentIndex);
        //onNavigationItemSelected(navigationView.getMenu().findItem(getIdByIndex(currentIndex)));
        int id = getIdByIndex(currentIndex);
        if (id == R.id.nav_material) {
            currentIndex = 0;
            currentFragment = new MaterialFragment();
        } else if (id == R.id.nav_transition) {
            currentIndex = 1;
            currentFragment = new TransitionFragment();
        } else if (id == R.id.nav_setting) {
            currentIndex = 2;
            currentFragment = new SettingFragement();
        } else if (id == R.id.nav_about) {
            currentIndex = 3;
            currentFragment = new AboutFragment();
        }
        switchContent(currentFragment);
        // 用navigationView.getMenu().getItem(index).setChecked(true);会出问题
        navigationView.setCheckedItem(id);
    }

    public void initDrawer(Toolbar toolbar) {
        if (toolbar != null) {
            DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }
            };
            mDrawerToggle.syncState();
            mDrawerLayout.addDrawerListener(mDrawerToggle);
        }
    }

    private int getIdByIndex(int index) {
        switch (index) {
            case 1:
                return R.id.nav_transition;
            case 2:
                return R.id.nav_setting;
            case 3:
                return R.id.nav_about;
            default:
                return R.id.nav_material;
        }
    }

    public void switchContent(Fragment fragment) {
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment).commit();
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_material) {
            currentIndex = 0;
            currentFragment = new MaterialFragment();
        } else if (id == R.id.nav_transition) {
            currentIndex = 1;
            currentFragment = new TransitionFragment();
        } else if (id == R.id.nav_setting) {
            currentIndex = 2;
            currentFragment = new SettingFragement();
        } else if (id == R.id.nav_about) {
            currentIndex = 3;
            currentFragment = new AboutFragment();
        }
        switchContent(currentFragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        //item.setChecked(true);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constans.CURRENT_INDEX, currentIndex);
        super.onSaveInstanceState(outState);
    }
}
