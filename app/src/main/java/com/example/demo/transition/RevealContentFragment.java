package com.example.demo.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

import com.example.demo.R;

/**
 * Created by zhooker on 2016/10/23.
 */

public class RevealContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_content_reveal, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initYellowView(view.findViewById(R.id.contentLinearLayout));

        initBlueView(view.findViewById(R.id.contentLinearLayout));
    }

    private void initYellowView(final View parentView) {
        //CircularReveal动画可以为颜色改变，控件的显示隐藏作动画
        parentView.findViewById(R.id.square_yellow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cx = (v.getLeft() + v.getRight()) / 2;
                int cy = (v.getTop() + v.getBottom()) / 2;
                int finalRadius = Math.max(parentView.getWidth(), parentView.getHeight());
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(parentView, cx, cy, 0, finalRadius);
                parentView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.sample_yellow2));
                anim.setDuration(500);
                anim.setInterpolator(new AccelerateInterpolator());
                anim.start();
            }
        });
    }

    private static final String TAG = "zhuangsj";

    private void initBlueView(final View parentView) {
        parentView.findViewById(R.id.square_blue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // topMargin = 270 ???
                float circle = ((RelativeLayout.LayoutParams) v.getLayoutParams()).topMargin + v.getHeight();


                float cx = (parentView.getWidth() - v.getWidth()) / 2f;
                float cy = (parentView.getHeight() - v.getHeight()) / 2f;

                Path path = new Path();
                path.moveTo(cx, cy + circle);

                for (int i = 0; i <= 360; i += 2) {
                    float x = (float) Math.sin(i * Math.PI / 180.0f) * circle;
                    float y = (float) Math.cos(i * Math.PI / 180.0f) * circle;
                    path.lineTo(x + cx, y + cy);
                }


                View myView = parentView.findViewById(R.id.square_blue);
                ObjectAnimator mAnimator;
                mAnimator = ObjectAnimator.ofFloat(myView, View.X, View.Y, path);
                mAnimator.setDuration(1000);
                mAnimator.start();
            }
        });
    }
}
