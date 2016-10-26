package com.example.demo.util;

import android.content.Context;
import android.content.res.TypedArray;

import com.example.demo.R;

/**
 * Created by zhuangsj on 16-10-24.
 */

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }
}
