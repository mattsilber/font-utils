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
                typefaces.put(getStyle(pair[0]), Typeface.createFromAsset(assets, pair[1]));
            }
            catch(RuntimeException e){ e.printStackTrace(); }
        }

        if(typefaces.get(Typeface.NORMAL) == null)
            throw new RuntimeException("Normal font not specified (e.g. normal=fonts/ex.ttf)");
    }

    private int getStyle(String value){
        value = value.toLowerCase(Locale.US);

        switch (value) {
            case "normal":
                return Typeface.NORMAL;
            case "bold":
                return Typeface.BOLD;
            case "italic":
                return Typeface.ITALIC;
            case "bold_italic":
                return Typeface.BOLD_ITALIC;
            default:
                break;
        }

        try {
            // For custom variations of a font without creating a new family
            return Integer.parseInt(value);
        }
        catch (Exception e) { }

        throw new RuntimeException("Unsupported font identifier: " + value);
    }

    /**
     * @return the Typeface for the entered style, or the default Typeface if it's not found
     */
    public Typeface get(int style){
        if(typefaces.get(style) == null)
            return typefaces.get(Typeface.NORMAL);

        return typefaces.get(style);
    }
}
