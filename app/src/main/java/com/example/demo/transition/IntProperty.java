package com.example.demo.transition;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Property;

/**
 * Created by zhooker on 2016/10/25.
 */

public abstract class IntProperty<T> extends Property<T, Integer> {

    public IntProperty() {
        super(Integer.class, null);
    }

    public abstract void setValue(T object, int value);

    @Override
    final public void set(T object, Integer value) {
        setValue(object, value);
    }

    /**
     * Just default realisation. Some of properties can have no getter. Override for real getter
     */
    @Override
    public Integer get(T object) {
        return null;
    }

    @SuppressLint("NewApi")
    public Property<T, Integer> optimize() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return new android.util.IntProperty<T>(null) {
                @Override
                public void setValue(T object, int value) {
                    IntProperty.this.setValue(object, value);
                }

                @Override
                public Integer get(T object) {
                    return IntProperty.this.get(object);
                }
            };
        } else {
            return this;
        }
    }

}
