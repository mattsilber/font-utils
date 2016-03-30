package com.guardanis.fontutils.fonts;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Locale;

public class FontFamily {

    private HashMap<Integer, Typeface> typefaces = new HashMap<Integer, Typeface>();

    public FontFamily(AssetManager assets, String fontParams){
        for(String params : fontParams.split(",")){
            String[] pair = params.split("=");

            try{
                typefaces.put(getStyle(pair[0]),
                        Typeface.createFromAsset(assets, pair[1]));
            }
            catch(RuntimeException e){ e.printStackTrace(); }
        }

        if(typefaces.get(Typeface.NORMAL) == null)
            throw new RuntimeException("Normal font not specified (e.g. normal=fonts/ex.ttf)");
    }

    private int getStyle(String value){
        value = value.toLowerCase(Locale.US);

        if(value.equals("normal"))
            return Typeface.NORMAL;
        else if(value.equals("bold"))
            return Typeface.BOLD;
        else if(value.equals("italic"))
            return Typeface.ITALIC;
        else if(value.equals("bold_italic"))
            return Typeface.BOLD_ITALIC;

        throw new RuntimeException("Unsupported font identifier: " + value);
    }

    /**
     * @return the Typeface for the entered style, or the default Typeface if it's not found
     */
    public Typeface get(int style){
        if(typefaces.get(style) == null)
            return typefaces.get(Typeface.NORMAL);
        else return typefaces.get(style);
    }

}
