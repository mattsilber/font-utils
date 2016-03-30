package com.guardanis.fontutils;

import android.content.Context;
import android.util.AttributeSet;

import com.guardanis.fontutils.fonts.FontUtils;

public class Button extends android.widget.Button {

    public Button(Context context) {
        super(context);
        init(null, 0);
    }

    public Button(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Button(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public Button init(AttributeSet attrs, int defStyle) {
        setWillNotDraw(false);

        FontUtils.getInstance(getContext())
                .load(this, attrs, defStyle);

        return this;
    }

    public void setFont(int fontFamilyResId, int style){
        setFont(getResources().getString(fontFamilyResId), style);
    }

    public void setFont(String fontFamily, int style){
        FontUtils.getInstance(getContext())
                .setTypeface(this, fontFamily, style);
    }

}
