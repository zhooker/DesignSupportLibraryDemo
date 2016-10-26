package com.example.demo.materialdesign;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.demo.util.Utils;

/**
 * Created by zhuangsj on 16-10-24.
 */

public class ScrollingFabBehavior extends CoordinatorLayout.Behavior<LinearLayout> {
    private static final String TAG = "ScrollingFabBehavior3";

    private int toolbarHeight;

    public ScrollingFabBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.toolbarHeight = Utils.getToolbarHeight(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, LinearLayout fab, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    ImageView imageView = null;

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, LinearLayout fab, View dependency) {
        final float finalScale = 0.36f;
        final float finalX = 165f;
        final float finalY = 2f;

        if (dependency instanceof AppBarLayout) {
            CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
            float ratio = (float) dependency.getY() / (float) (dependency.getHeight() - toolbarHeight);
            ratio = -ratio;

            if (imageView == null)
                imageView = (ImageView) fab.getChildAt(0);

            float beginX = (dependency.getWidth() - imageView.getWidth()) / 2.0f;
            float beginY = (dependency.getHeight() - imageView.getHeight()) / 2.0f;

            fab.setTranslationX((beginX + (ratio) * (finalX - beginX) / 0.9f));
            fab.setTranslationY((beginY + (ratio) * (finalY - beginY) / 0.9f));

            float scale = 1 - ratio * (1 - finalScale);
            fab.setScaleX(scale);
            fab.setScaleY(scale);


            Log.d(TAG, "onDependentViewChanged() ratio = " + ratio + ",x=" + fab.getTranslationX()
                    + ",y=" + fab.getTranslationY());


        }
        return true;
    }
}