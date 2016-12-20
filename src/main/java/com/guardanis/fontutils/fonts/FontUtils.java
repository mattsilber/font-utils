package com.guardanis.fontutils.fonts;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.guardanis.fontutils.R;

import java.util.HashMap;
import java.util.Set;

public class FontUtils {

    private static FontUtils instance;
    public static FontUtils getInstance(Context context){
        if(instance == null)
            instance = new FontUtils(context);

        return instance;
    }

    private static final String RES_AUTO = "http://schemas.android.com/apk/res/android";

    private HashMap<String, FontFamily> fontFamilies = new HashMap<String, FontFamily>();

    private String defaultFontFamily;

    protected FontUtils(Context context){
        this.defaultFontFamily = context.getString(R.string.fu__default_font_family);

        for(String family : context.getResources().getStringArray(R.array.fu__font_family_resources)){
            String[] pair = family.split("--");

            fontFamilies.put(pair[0],
                    new FontFamily(context.getAssets(), pair[1]));
        }
    }

    public Typeface getTypeface(){
        return fontFamilies.get(defaultFontFamily)
                .get(Typeface.NORMAL);
    }

    /**
     * @return Typeface for style from default FontFamily, or default normal Typeface if not found
     */
    public Typeface getTypeface(int style){
        return fontFamilies.get(defaultFontFamily)
                .get(style);
    }

    /**
     * @return Typeface for Style, or FontFamily's normal font if not found
     */
    public Typeface getTypeface(Context context, int fontFamilyResId, int style){
        return getTypeface(context.getString(fontFamilyResId), style);
    }

    /**
     * @return Typeface for Style, or FontFamily's normal font if not found
     */
    public Typeface getTypeface(String fontFamily, int style){
        return fontFamilies.get(fontFamily)
                .get(style);
    }

    public Set<String> getFontFamilyKeys(){
        return fontFamilies.keySet();
    }

    public FontFamily getFontFamily(String fontFamily){
        return fontFamilies.get(fontFamily);
    }

    public void registerFontFamily(String key, FontFamily fontFamily){
        fontFamilies.put(key, fontFamily);
    }

    public void load(TextView view, AttributeSet attrs, int defStyle){
        if(attrs == null)
            view.setTypeface(getTypeface());
        else {
            String fontFamily = "";

            TypedArray a = view.getContext().getTheme()
                    .obtainStyledAttributes(attrs, R.styleable.FontUtils, defStyle, 0);

            fontFamily = a.getString(R.styleable.FontUtils_textFontFamily);

            if(fontFamily == null || fontFamily.length() < 1)
                fontFamily = defaultFontFamily;

            int style = attrs.getAttributeIntValue(RES_AUTO, "textStyle", Typeface.NORMAL);

            view.setTypeface(getTypeface(fontFamily, style));

            int textSize = a.getInt(R.styleable.FontUtils_textSizePercent, -1);
            if(0 < textSize)
                view.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                        view.getResources().getDisplayMetrics().widthPixels * (textSize / 100f));

            a.recycle();
        }
    }

    public void setTypeface(TextView view){
        setTypeface(view, defaultFontFamily, Typeface.NORMAL);
    }

    public void setTypeface(TextView view, int style){
        setTypeface(view, defaultFontFamily, style);
    }

    public void setTypeface(TextView view, int fontFamilyResId, int style){
        setTypeface(view, view.getContext().getString(fontFamilyResId), style);
    }

    public void setTypeface(TextView view, String fontFamily, int style){
        view.setTypeface(getTypeface(fontFamily, style));
    }

}
