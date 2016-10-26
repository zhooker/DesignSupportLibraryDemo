package com.example.demo.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.ArcMotion;
import android.transition.AutoTransition;
import android.transition.ChangeBounds;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.PathInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.demo.R;
import com.example.demo.materialdesign.RecyclerViewAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.button;

/**
 * Created by zhooker on 2016/10/23.
 */

public class ContentFragment extends Fragment {

    private int[] colors = new int[]{
            0xFFFF0000,
            0xFFFF5722,
            0xFF9C27B0,
            0xFF1E88E5,
            0xFFFF5722
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.contentLinearLayout).setBackgroundColor(colors[0]);


        RadioGroup rg1 = (RadioGroup) view.findViewById(R.id.radiogroup1);
        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                           @Override
                                           public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                               setEnterTransition(createTransition(i));
                                           }
                                       }

        );
        rg1.check(R.id.radiobutton1);

        final RadioGroup rg2 = (RadioGroup) view.findViewById(R.id.radiogroup2);
        rg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                           @Override
                                           public void onCheckedChanged(RadioGroup radioGroup, int i) {
                                               setExitTransition(createTransition(i));
                                           }
                                       }

        );
        rg2.check(R.id.radiobutton1);
    }


    private Transition createTransition(int id) {
        switch (id) {
            case R.id.radiobutton1:
                return new Fade();
            case R.id.radiobutton2:
                return TransitionInflater.from(getActivity()).inflateTransition(R.transition.custom_transition);
            case R.id.radiobutton3:
                return new ChangeBounds();
            case R.id.radiobutton4:
                return new Slide(Gravity.LEFT);
            case R.id.radiobutton5:
                return new Slide(Gravity.RIGHT);
            case R.id.radiobutton6:
                Transition explode = new Explode();
                explode.setEpicenterCallback(new Transition.EpicenterCallback() {
                    @Override
                    public Rect onGetEpicenter(Transition transition) {
                        return new Rect(0, 0, 500, 500);
                    }
                });
                explode.setDuration(500);
                return explode;
        }
        return new AutoTransition();
    }
}
