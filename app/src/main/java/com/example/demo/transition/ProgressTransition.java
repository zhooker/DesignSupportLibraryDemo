package com.example.demo.transition;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Property;
import android.view.ViewGroup;
import android.widget.ProgressBar;

/**
 * Created by zhooker on 2016/10/25.
 */

public class ProgressTransition extends Transition {

    /**
     * Property is like a helper that contain setter and getter in one place
     */
    private static final Property<ProgressBar, Integer> PROGRESS_PROPERTY = new IntProperty<ProgressBar>() {

        @Override
        public void setValue(ProgressBar progressBar, int value) {
            progressBar.setProgress(value);
        }

        @Override
        public Integer get(ProgressBar progressBar) {
            return progressBar.getProgress();
        }
    }.optimize();

    /**
     * Internal name of property. Like a bundles for intent
     */
    private static final String PROPNAME_PROGRESS = "ProgressTransition:progress";

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    private void captureValues(TransitionValues transitionValues) {
        if (transitionValues.view instanceof ProgressBar) {
            // save current progress in the values map
            ProgressBar progressBar = ((ProgressBar) transitionValues.view);
            transitionValues.values.put(PROPNAME_PROGRESS, progressBar.getProgress());
        }
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        if (startValues != null && endValues != null && endValues.view instanceof ProgressBar) {
            ProgressBar progressBar = (ProgressBar) endValues.view;
            int start = (Integer) startValues.values.get(PROPNAME_PROGRESS);
            int end = (Integer) endValues.values.get(PROPNAME_PROGRESS);
            if (start != end) {
                // first of all we need to return the start value, because right now
                // the view is have the end value
                progressBar.setProgress(start);
                // create animator with our progressBar, property and end value
                return ObjectAnimator.ofInt(progressBar, PROGRESS_PROPERTY, end);
            }
        }
        return null;
    }
}