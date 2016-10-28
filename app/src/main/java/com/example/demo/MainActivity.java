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
import com.example.demo.util.L;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int lastID = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            lastID = switchContent(R.id.nav_material, -1);
            navigationView.setCheckedItem(lastID);
        } else {
            lastID = savedInstanceState.getInt(Constans.CURRENT_INDEX, R.id.nav_material);
        }

        Log.d("zhuangsj", "onCreate() currentIndex = " + getIndexById(lastID));
        // 用navigationView.getMenu().getItem(index).setChecked(true);会出问题
        //onNavigationItemSelected(navigationView.getMenu().findItem(getIdByIndex(currentIndex)));
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

    public int switchContent(int curr, int last) {
        L.d("curr = " + curr + ",last = " + last);
        android.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        Fragment lastFragment = getFragmentManager().findFragmentByTag("f" + last);
        if (lastFragment != null)
            fragmentTransaction.detach(lastFragment);

        Fragment currFragment = getFragmentManager().findFragmentByTag("f" + curr);
        if (currFragment == null) {
            currFragment = getFragment(curr);
            fragmentTransaction.replace(R.id.contentLayout, currFragment, "f" + curr);
        }
        fragmentTransaction.attach(currFragment).commitAllowingStateLoss();
        //invalidateOptionsMenu();
        return curr;
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
        lastID = switchContent(id, lastID);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constans.CURRENT_INDEX, lastID);
        super.onSaveInstanceState(outState);
    }

    private Fragment getFragment(int id) {
        if (id == R.id.nav_transition) {
            return new TransitionFragment();
        } else if (id == R.id.nav_setting) {
            return new SettingFragement();
        } else if (id == R.id.nav_about) {
            return new AboutFragment();
        } else return new MaterialFragment();
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

    private int getIndexById(int id) {
        switch (id) {
            case R.id.nav_transition:
                return 1;
            case R.id.nav_setting:
                return 2;
            case R.id.nav_about:
                return 3;
            default:
                return R.id.nav_material;
        }
    }
}
