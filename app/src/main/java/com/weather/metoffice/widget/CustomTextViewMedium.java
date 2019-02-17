package com.weather.metoffice.widget;


import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomTextViewMedium extends TextView {
    public CustomTextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Medium.ttf"));
    }
}
