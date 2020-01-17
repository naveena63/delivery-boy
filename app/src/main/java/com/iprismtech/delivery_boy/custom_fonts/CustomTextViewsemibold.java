package com.iprismtech.delivery_boy.custom_fonts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


/**
 * Created by admin on 2/15/2017.
 */

@SuppressLint("AppCompatCustomView")
public class CustomTextViewsemibold extends TextView {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextViewsemibold(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle,defStyleRes);
        init();
    }
    public CustomTextViewsemibold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewsemibold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewsemibold(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/segui_semi_bold.ttf");
        setTypeface(externalFont);
}
}
