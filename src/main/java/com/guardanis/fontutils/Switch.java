package com.guardanis.fontutils;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.guardanis.fontutils.fonts.FontUtils;

@RequiresApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Switch extends android.widget.Switch {

    public Switch(Context context) {
        super(context);
        init(null, 0);
    }

    public Switch(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public Switch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public Switch init(AttributeSet attrs, int defStyle) {
        setWillNotDraw(false);

        FontUtils.getInstance(getContext())
                .load(this, attrs, defStyle);

        return this;
    }

    public void setFont(int fontFamilyResId, int style) {
        setFont(getResources().getString(fontFamilyResId), style);
    }

    public void setFont(String fontFamily, int style) {
        FontUtils.getInstance(getContext())
                .setTypeface(this, fontFamily, style);
    }

}
