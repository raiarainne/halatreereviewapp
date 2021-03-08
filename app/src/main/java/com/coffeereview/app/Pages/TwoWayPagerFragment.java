package com.coffeereview.app.Pages;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coffeereview.app.R;
import com.coffeereview.app.Utils.HorizontalInfiniteCycleViewPager;
import com.coffeereview.app.Utils.HorizontalPagerAdapter;


public class TwoWayPagerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_two_way_pager, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       /* final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) view.findViewById(R.id.hicvp);
        horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(getContext(), true));*/
//
//        horizontalInfiniteCycleViewPager.setScrollDuration(500);
//        horizontalInfiniteCycleViewPager.setPageDuration(1000);
//        horizontalInfiniteCycleViewPager.setInterpolator(null);
//        horizontalInfiniteCycleViewPager.setMediumScaled(true);
//        horizontalInfiniteCycleViewPager.setMaxPageScale(1.0F);
//        horizontalInfiniteCycleViewPager.setMinPageScale(0.7F);
//        horizontalInfiniteCycleViewPager.setCenterPageScaleOffset(0.0F);
//        horizontalInfiniteCycleViewPager.setMinPageScaleOffset(0.0F);
    }
}