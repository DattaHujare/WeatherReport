package com.weather.metoffice.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

public class CustomMaterialSpinner extends MaterialBetterSpinner {

    public CustomMaterialSpinner(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf"));
    }

    public CustomMaterialSpinner(Context context, AttributeSet arg1) {
        super(context, arg1);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf"));
    }

    public CustomMaterialSpinner(Context context, AttributeSet arg1, int arg2) {
        super(context, arg1, arg2);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf"));
    }

    /**
     * Override to fix enable/disable problem.
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled())
            return super.onTouchEvent(event);
        else return false;
    }
}