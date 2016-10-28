package com.example.demo.setting;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.demo.BaseFragment;
import com.example.demo.MainActivity;
import com.example.demo.R;
import com.example.demo.util.L;

/**
 * Created by zhuangsj on 16-10-23.
 */

public class SettingFragement extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View base = inflater.inflate(R.layout.fragment_setting, null);
        L.df(this);
        return base;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("设置");
        ((MainActivity) getActivity()).initDrawer(mToolbar);
        initFragment();
        L.df(this);
    }

    private void initFragment() {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, new PrefsFragement());
        fragmentTransaction.commitAllowingStateLoss();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.df(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.df(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        L.d(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        L.df(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        L.df(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        L.d(hidden);
    }

    @Override
    public void onPause() {
        super.onPause();
        L.df(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        L.df(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        L.df(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        L.df(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        L.df(this);
    }
}