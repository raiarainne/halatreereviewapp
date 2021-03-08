package com.coffeereview.app.Adapter;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coffeereview.app.Activity.AddnewdataActivity;
import com.coffeereview.app.Activity.SitelistActivity;
import com.coffeereview.app.Model.MainModel;
import com.coffeereview.app.Preference.PrefConst;
import com.coffeereview.app.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SitelistAdapter extends BaseAdapter {
    SitelistActivity sitelistActivity=null;
    private ArrayList<MainModel> navitems = new ArrayList<>();


    public SitelistAdapter(SitelistActivity context, ArrayList<MainModel> mainModels) {

        super();
        this.sitelistActivity = context;
        this.navitems=mainModels;
    }



    @Override
    public int getCount() {
        return navitems.size();
    }

    @Override
    public Object getItem(int position) {
        return navitems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final CustomHolder holder;


        if (convertView == null) {
            holder = new CustomHolder();
            LayoutInflater inflater= (LayoutInflater) sitelistActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            holder.txvtitle = convertView.findViewById(R.id.title);
            holder.txvcontent = convertView.findViewById(R.id.content);
            holder.imvdelete = (ImageView) convertView.findViewById(R.id.btn_cancel);
            convertView.setTag(holder);
        } else {
            holder = (CustomHolder) convertView.getTag();
        }




        final MainModel item = (MainModel) navitems.get(position);
        holder.txvtitle.setText(item.sitename);
        holder.txvcontent.setText(item.siteurl);
        holder.imvdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sitelistActivity.confirmdeleat(item, position);
            }
        });


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefConst.currentitemid = item.id;
                Intent intent = new Intent(sitelistActivity, AddnewdataActivity.class);
                sitelistActivity.startActivity(intent);
                sitelistActivity.finish();
            }
        });
        return convertView;
    }




    private class CustomHolder {
        TextView txvtitle, txvcontent;
        ImageView imvdelete;
    }





}
