package com.example.demo.transition;

import android.app.Fragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demo.R;

/**
 * Created by zhooker on 2016/10/23.
 */

public class NormalTransitionFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_content_normal, container, false);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initSimpleTransition(view);

        initDelayTransition(view);

        initSlideTransition(view);

        initScaleTransition(view);

        initProgressTransition(view);
    }

    private void initSimpleTransition(View view) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.container_simple);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.tv_simple);

        transitionsContainer.findViewById(R.id.btn_simple).setOnClickListener(new VisibleToggleClickListener() {

            @Override
            protected void changeVisibility(boolean visible) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                // it is the same as
                // TransitionManager.beginDelayedTransition(transitionsContainer, new AutoTransition());
                // where AutoTransition is the set of Fade and ChangeBounds transitions
                text.setVisibility(visible ? View.GONE : View.VISIBLE);
            }

        });
    }

    private void initDelayTransition(View view) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.container_delay);
        final Button btn = (Button) transitionsContainer.findViewById(R.id.btn_delay);
        btn.setOnClickListener(new VisibleToggleClickListener() {
            @Override
            protected void changeVisibility(boolean visible) {
                Transition transition = new ChangeBounds();
                transition.setDuration(visible ? 700 : 300);
                transition.setInterpolator(visible ? new FastOutSlowInInterpolator() : new AccelerateInterpolator());
                transition.setStartDelay(visible ? 0 : 500);
                TransitionManager.beginDelayedTransition(transitionsContainer, transition);

                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) btn.getLayoutParams();
                params.gravity = visible ? Gravity.RIGHT : Gravity.LEFT;
                params.gravity |= Gravity.CENTER_VERTICAL;
                btn.setLayoutParams(params);
            }
        });
    }

    private void initSlideTransition(View view) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.container_slide);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.tv_slide);

        transitionsContainer.findViewById(R.id.btn_slide).setOnClickListener(new VisibleToggleClickListener() {

            @Override
            protected void changeVisibility(boolean visible) {
                TransitionManager.beginDelayedTransition(transitionsContainer, visible ? new Slide(Gravity.RIGHT) : new Slide(Gravity.LEFT));
                // it is the same as
                // TransitionManager.beginDelayedTransition(transitionsContainer, new AutoTransition());
                // where AutoTransition is the set of Fade and ChangeBounds transitions
                text.setVisibility(visible ? View.GONE : View.VISIBLE);
            }

        });
    }

    private void initScaleTransition(View view) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.container_scale);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.tv_scale);

        transitionsContainer.findViewById(R.id.btn_scale).setOnClickListener(new VisibleToggleClickListener() {

            @Override
            protected void changeVisibility(boolean visible) {
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.7f))
                        .addTransition(new Fade())
                        .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                // it is the same as
                // TransitionManager.beginDelayedTransition(transitionsContainer, new AutoTransition());
                // where AutoTransition is the set of Fade and ChangeBounds transitions
                text.setVisibility(visible ? View.GONE : View.VISIBLE);
            }

        });
    }

    private void initProgressTransition(View view) {
        final ViewGroup transitionsContainer = (ViewGroup) view.findViewById(R.id.container_progress);
        final ProgressBar mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        view.findViewById(R.id.btn_progress_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = mProgressBar.getProgress() - 20;
                TransitionManager.beginDelayedTransition(transitionsContainer, new ProgressTransition());
                value = Math.max(0, Math.min(100, value));
                mProgressBar.setProgress(value);
            }
        });
        view.findViewById(R.id.btn_progress_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = mProgressBar.getProgress() + 30;
                TransitionManager.beginDelayedTransition(transitionsContainer, new ProgressTransition());
                value = Math.max(0, Math.min(100, value));
                mProgressBar.setProgress(value);
            }
        });
    }


    public abstract class VisibleToggleClickListener implements View.OnClickListener {

        private boolean mVisible;

        @Override
        public void onClick(View v) {
            mVisible = !mVisible;
            changeVisibility(mVisible);
        }

        protected abstract void changeVisibility(boolean visible);

    }
}
