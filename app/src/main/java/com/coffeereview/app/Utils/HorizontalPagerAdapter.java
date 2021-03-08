package com.coffeereview.app.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.coffeereview.app.R;

import java.util.ArrayList;

import static com.coffeereview.app.Utils.Utils.setupItem;

public class HorizontalPagerAdapter extends PagerAdapter {
    ArrayList<Utils.LibraryObject> libraryObjects = new ArrayList<>();

    /*private final Utils.LibraryObject[] LIBRARIES = new Utils.LibraryObject[]{
            new Utils.LibraryObject(
                    R.drawable.qrcode,
                    "Strategy"
            ),
            new Utils.LibraryObject(
                    R.drawable.qrcode,
                    "Design"
            ),
            new Utils.LibraryObject(
                    R.drawable.qrcode,
                    "Development"
            ),
            new Utils.LibraryObject(
                    R.drawable.qrcode,
                    "Quality Assurance"
            )
    };*/

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    private boolean mIsTwoWay;

    public HorizontalPagerAdapter(final Context context, final boolean isTwoWay, ArrayList<Utils.LibraryObject> libraryObjects1) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mIsTwoWay = isTwoWay;
        this.libraryObjects = libraryObjects1;
    }

    @Override
    public int getCount() {
        return mIsTwoWay ? 6 : libraryObjects.size();
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        if (mIsTwoWay) {
            view = mLayoutInflater.inflate(R.layout.two_way_item, container, false);

            final VerticalInfiniteCycleViewPager verticalInfiniteCycleViewPager =
                    (VerticalInfiniteCycleViewPager) view.findViewById(R.id.vicvp);
            verticalInfiniteCycleViewPager.setAdapter(
                    new VerticalPagerAdapter(mContext)
            );
            verticalInfiniteCycleViewPager.setCurrentItem(position);
        } else {
            view = mLayoutInflater.inflate(R.layout.item, container, false);
            setupItem(view, libraryObjects.get(position), mContext);
        }

        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
