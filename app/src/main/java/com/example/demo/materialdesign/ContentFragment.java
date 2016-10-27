package com.example.demo.materialdesign;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhooker on 2016/10/22.
 */

public class ContentFragment extends Fragment {

    //标志位，标志已经初始化完成，因为setUserVisibleHint是在onCreateView之前调用的，在视图未初始化的时候，在lazyLoad当中就使用的话，就会有空指针的异常
    private boolean isPrepared;
    //标志当前页面是否可见
    private boolean isVisible;
    //标志数据是否已经加载
    private boolean isLoaded;
    //数据页面
    private int page = 1;

    private String mTitle;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerViewAdapter mRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_material_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTitle = this.getArguments().getString("title");
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.id_refreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isLoaded = false;
                mRecyclerViewAdapter.clear();
                mHandler.sendEmptyMessageDelayed(1, 500);
            }
        });
        initRecyclerView();

        isPrepared = true;
        lazyLoad();
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mRecyclerViewAdapter = new RecyclerViewAdapter(getParentFragment(), new ArrayList<String>());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //懒加载
        if (getUserVisibleHint()) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
    }

    protected void lazyLoad() {
        if (!isVisible || !isPrepared || isLoaded) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
        mHandler.sendEmptyMessageDelayed(1, 300);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    int start = 20 * (page - 1);
                    List<String> list = new ArrayList<>();
                    for (int i = start; i < page * 20; i++) {
                        list.add(mTitle + "," + getActivity().getString(R.string.test_data) + i);
                    }
                    mRecyclerViewAdapter.addAll(list);
                    mSwipeRefreshLayout.setRefreshing(false);
                    isLoaded = true;
                    break;
            }
        }
    };
}
