package com.coffeereview.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.coffeereview.app.Model.MainModel;
import com.coffeereview.app.Preference.PrefConst;
import com.coffeereview.app.Preference.Preference;
import com.coffeereview.app.R;
import com.coffeereview.app.Utils.HorizontalInfiniteCycleViewPager;
import com.coffeereview.app.Utils.HorizontalPagerAdapter;
import com.coffeereview.app.Utils.MainPagerAdapter;
import com.coffeereview.app.Utils.Utils;
import com.gigamole.navigationtabstrip.NavigationTabStrip;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class MainActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HorizontalInfiniteCycleViewPager horizontalInfiniteCycleViewPager =
                (HorizontalInfiniteCycleViewPager) findViewById(R.id.hicvp);
        ArrayList<Utils.LibraryObject> libraryObjects = new ArrayList<>();
        ArrayList<MainModel> mainModels = Preference.getInstance().getSharedPreference(this, PrefConst.PREFKEY_MAINMODELS);
        if(mainModels.size()>0){
            for(int i=0; i<mainModels.size(); i++){
                Utils.LibraryObject libraryObject = new Utils.LibraryObject(mainModels.get(i).sitename, "https://chart.googleapis.com/chart?chs=420x420&cht=qr&chl="+mainModels.get(i).siteurl.trim().replaceAll(" ","%20")+"&choe=UTF-8");
                libraryObjects.add(libraryObject);
            }
            horizontalInfiniteCycleViewPager.setAdapter(new HorizontalPagerAdapter(this, false, libraryObjects));
        }



        /*final ViewPager viewPager = (ViewPager) findViewById(R.id.vp_main);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);

        final NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts);
        navigationTabStrip.setTitles("HOW WE WORK", "WE WORK WITH");
        navigationTabStrip.setViewPager(viewPager);*/


    }



    public void showpasscode(View view) {
        showAlertDialogForQuestion( this);
    }
}