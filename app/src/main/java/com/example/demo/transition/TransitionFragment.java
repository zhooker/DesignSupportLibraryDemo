package com.example.demo.transition;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.BaseFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;

/**
 * Created by zhooker on 2016/10/23.
 */

public class TransitionFragment extends BaseFragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    String[] fragments = new String[]{
            RevealContentFragment.class.getSimpleName(),
            NormalTransitionFragment.class.getSimpleName(),
            ExplorelTransitionFragment.class.getSimpleName(),
            SceneTransitionFragment.class.getSimpleName(),
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.fragment_transition, null);
        return base;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("Transition动画");
        ((MainActivity) getActivity()).initDrawer(mToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        onNavigationItemSelected(bottomNavigationView.getMenu().getItem(0));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.item1:
                fragment = getFragment(0);
                break;
            case R.id.item2:
                fragment = getFragment(1);
                break;
            case R.id.item3:
                fragment = getFragment(2);
                break;
            case R.id.item4:
                fragment = getFragment(3);
                break;
        }
        if (fragment != null) {
            fragment.setEnterTransition(new Slide(Gravity.RIGHT));
            fragment.setExitTransition(new Slide(Gravity.LEFT));
            reaplceFragment(fragment);
        }
        return false;
    }

    private Fragment getFragment(int index) {
        try {
            Class<?> clazz = Class.forName("com.example.demo.transition." + fragments[index]);
            return (Fragment) clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void reaplceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentLayout, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
