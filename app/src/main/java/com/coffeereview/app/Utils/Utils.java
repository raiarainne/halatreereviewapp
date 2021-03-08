package com.coffeereview.app.Utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coffeereview.app.R;

import java.io.File;

public class Utils {
    public static void setupItem(final View view, final LibraryObject libraryObject) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        img.setImageResource(libraryObject.getRes());

    }

    public static void setupItem(final View view, final LibraryObject libraryObject, Context _context) {
        final TextView txt = (TextView) view.findViewById(R.id.txt_item);
        txt.setText(libraryObject.getTitle());

        final ImageView img = (ImageView) view.findViewById(R.id.img_item);
        Log.d("urls==", libraryObject.getQrurl());
        //img.setImageResource(libraryObject.getRes());
        Glide.with(_context)
                .load(libraryObject.getQrurl())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //.placeholder(R.drawable.loading_spinner)
                .into(img);
    }

    public static class LibraryObject {

        private String mTitle;
        private String qrurl;
        private int mRes;

        public LibraryObject(final int res, final String title) {
            mRes = res;
            mTitle = title;
        }

        public LibraryObject(final String title, final String url) {
            qrurl = url;
            mTitle = title;
        }

        public String getTitle() {
            return mTitle;
        }

        public void setTitle(final String title) {
            mTitle = title;
        }

        public int getRes() {
            return mRes;
        }

        public void setRes(final int res) {
            mRes = res;
        }

        public String getQrurl() {
            return qrurl;
        }

        public void setQrurl(String qrurl) {
            this.qrurl = qrurl;
        }
    }
}
