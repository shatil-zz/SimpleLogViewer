package com.shieldpeoples.simplelogviewer.utils.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by shatil on 6/28/16.
 */
public class CustomLayoutManager extends LinearLayoutManager {
    private static final int DEFAULT_EXTRA_LAYOUT_SPACE = 600;
    private int extraLayoutSpace = -1;
    private Context context;

    public CustomLayoutManager(Context context) {
        super(context);
        this.context = context;
    }

    public CustomLayoutManager(Context context, int extraLayoutSpace) {
        super(context);
        this.context = context;
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        this.context = context;
    }

    public void setExtraLayoutSpace(int extraLayoutSpace) {
        this.extraLayoutSpace = extraLayoutSpace;
    }

    public void setExtraLayoutSpace() {
        this.extraLayoutSpace = Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    @Override
    protected int getExtraLayoutSpace(RecyclerView.State state) {
        if (extraLayoutSpace > 0) {
            return extraLayoutSpace;
        }
        return DEFAULT_EXTRA_LAYOUT_SPACE;
    }
}