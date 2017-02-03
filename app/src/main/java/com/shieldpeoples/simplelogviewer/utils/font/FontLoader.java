package com.shieldpeoples.simplelogviewer.utils.font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by Shahanur on 1/13/2017.
 */

public enum FontLoader {
    INSTANCE;
    String LOG_TAG = "FontLoader";
    private Typeface tfRegular, tfLight, tfBold, tfItalic;

    public Typeface getRegularFont(Context context) {
        if (tfRegular == null) {
            tfRegular = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Regular.ttf");
            Log.d(LOG_TAG, "Regular font loaded");
        }
        return tfRegular;
    }

    public Typeface getLightFont(Context context) {
        if (tfLight == null) {
            tfLight = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Light.ttf");
        }
        return tfLight;
    }

    public Typeface getBoldFont(Context context) {
        if (tfBold == null) {
            tfBold = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Bold.ttf");
            Log.d(LOG_TAG, "Bold font loaded");
        }
        return tfBold;
    }

    public Typeface getItalicFont(Context context) {
        if (tfItalic == null) {
            tfItalic = Typeface.createFromAsset(context.getAssets(),
                    "font/Roboto-Italic.ttf");
        }
        return tfItalic;
    }

}
