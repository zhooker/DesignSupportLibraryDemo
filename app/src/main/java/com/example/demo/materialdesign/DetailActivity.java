package com.example.demo.materialdesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.demo.Constans;
import com.example.demo.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("详情标题");
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        int position = this.getIntent().getIntExtra("position", 0);
        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);
        ViewCompat.setTransitionName(backdrop, Constans.TRANSITION_PIC);
        // ViewCompat.setTransitionName(fab, Constans.TRANSITION_FAB);
        if (position % 2 == 0) {
            backdrop.setBackgroundResource(R.mipmap.show_img1);
        } else {
            backdrop.setBackgroundResource(R.mipmap.show_img2);
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

        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    /**
     * @param showImage 启动共享的元素的组件
     */
    public static void startActivity(Activity activity, int position, View showImage) {
        Intent intent = new Intent();
        intent.setClass(activity, DetailActivity.class);
        intent.putExtra("position", position);

        Pair<View, String> p1 = Pair.create(showImage, Constans.TRANSITION_PIC);
        // Pair<View, String> p2 = Pair.create(fab, Constans.TRANSITION_FAB);

        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, p1);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
