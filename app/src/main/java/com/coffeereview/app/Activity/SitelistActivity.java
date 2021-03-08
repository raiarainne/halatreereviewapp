package com.coffeereview.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.coffeereview.app.Adapter.SitelistAdapter;
import com.coffeereview.app.Model.MainModel;
import com.coffeereview.app.Preference.PrefConst;
import com.coffeereview.app.Preference.Preference;
import com.coffeereview.app.R;

import java.util.ArrayList;

public class SitelistActivity extends BaseActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitelist);
        listView = (ListView)findViewById(R.id.listview);
        ArrayList<MainModel> mainModels =  Preference.getInstance().getSharedPreference(SitelistActivity.this, PrefConst.PREFKEY_MAINMODELS);
        SitelistAdapter sitelistAdapter = new SitelistAdapter(SitelistActivity.this, mainModels);
        listView.setAdapter(sitelistAdapter);
    }

    public void addnewitem(View view) {
        Intent intent = new Intent(this, AddnewdataActivity.class);
        startActivity(intent);
        finish();
    }

    public void confirmdeleat(MainModel item, int position) {
        new AlertDialog.Builder(SitelistActivity.this)
                .setTitle("Warning")
                .setMessage("Are you sure to delete this item?")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                ArrayList<MainModel> mainModels =  Preference.getInstance().getSharedPreference(SitelistActivity.this, PrefConst.PREFKEY_MAINMODELS);
                                mainModels.remove(position);
                                Preference.getInstance().putSharedPreference(SitelistActivity.this, PrefConst.PREFKEY_MAINMODELS, mainModels);
                                SitelistAdapter sitelistAdapter = new SitelistAdapter(SitelistActivity.this, mainModels);
                                listView.setAdapter(sitelistAdapter);
                            }
                        }).setNegativeButton("No", null).show();
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       gotoback();
    }

    private void gotoback() {
        Intent intent = new Intent(SitelistActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goback(View view) {
        gotoback();
    }
}