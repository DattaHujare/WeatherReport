package com.weather.metoffice.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;


public class CustomCheckBoxRegular extends CheckBox {
    public CustomCheckBoxRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf"));
    }
}
