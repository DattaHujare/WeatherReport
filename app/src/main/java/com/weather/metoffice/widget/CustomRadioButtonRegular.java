package com.weather.metoffice.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class CustomRadioButtonRegular extends RadioButton {
    public CustomRadioButtonRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(),"fonts/Roboto-Regular.ttf"));
    }
}
