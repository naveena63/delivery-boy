package com.iprismtech.delivery_boy.custom_fonts;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

@SuppressLint("AppCompatCustomView")
public class CustomEditTextsemibold extends EditText {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomEditTextsemibold(Context context, AttributeSet attrs, int defStyle, int defStyleRes) {
        super(context, attrs, defStyle, defStyleRes);
        init();
    }

    public CustomEditTextsemibold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomEditTextsemibold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomEditTextsemibold(Context context) {
        super(context);
        init();
    }

    private void init() {

        Typeface externalFont = Typeface.createFromAsset(getContext().getAssets(), "fonts/segui_semi_bold.ttf");

        setTypeface(externalFont);

    }

}
