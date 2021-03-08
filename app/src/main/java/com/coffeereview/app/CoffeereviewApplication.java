package com.coffeereview.app;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import androidx.multidex.MultiDexApplication;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xianshu on 09/22/2018.
 */
public class CoffeereviewApplication extends MultiDexApplication {

    public static final String TAG = CoffeereviewApplication.class.getSimpleName();

    private static CoffeereviewApplication _instance;


    //public RequestQueue _requestQueue;

   // public ImageLoader _imageLoader;

    @Override
    public void onCreate(){
        super.onCreate();
        _instance = this;
      //  JodaTimeAndroid.init(this);
        instantiateVolleyQueue();
        getUserInfo();

    }
    public void instantiateVolleyQueue() {

    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      //  MultiDex.install(this);
    }


    public static synchronized CoffeereviewApplication getInstance(){

        return _instance;
    }

  /*  public RequestQueue getRequestQueue(){

        if(_requestQueue == null){
            _requestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return _requestQueue;
    }*/
    /*


    public ImageLoader getImageLoader(){

        getRequestQueue();
        if(_imageLoader == null){
           // _imageLoader = new ImageLoader(this._requestQueue, new LruBitmapCache());
        }
        return this._imageLoader;
    }*/

   /* public <T> void addToRequestQueue(Request<T> req, String tag){

        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (_requestQueue != null) {
            _requestQueue.cancelAll(tag);
        }
    }*/

    // get sha1 and then print logo
    public void getUserInfo(){
        // Add code to print out the key hash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }







}
