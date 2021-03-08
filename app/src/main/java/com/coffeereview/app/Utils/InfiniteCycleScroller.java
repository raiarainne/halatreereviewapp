package com.coffeereview.app.Utils;

import android.content.Context;
import android.widget.Scroller;
import android.view.animation.Interpolator;

class InfiniteCycleScroller extends Scroller {

    private int mDuration;

    public InfiniteCycleScroller(final Context context) {
        super(context);
    }

    public InfiniteCycleScroller(final Context context, final Interpolator interpolator) {
        super(context, interpolator);
    }

    public InfiniteCycleScroller(final Context context, final Interpolator interpolator, final boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    public void setDuration(final int duration) {
        mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}