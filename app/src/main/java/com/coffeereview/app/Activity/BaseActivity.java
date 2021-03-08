package com.coffeereview.app.Activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.coffeereview.app.Model.MainModel;
import com.coffeereview.app.Preference.PrefConst;
import com.coffeereview.app.Preference.Preference;
import com.coffeereview.app.R;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class BaseActivity extends AppCompatActivity implements Handler.Callback{

    public Context _context = null;

    public Handler _handler = null;

    public ProgressDialog progressBar;

    public static final String[] CAM_PER = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Thread.setDefaultUncaughtExceptionHandler(handleAppCrash);
        _context = this;
        _handler = new Handler(this);

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Downloading Images...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setCancelable(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {


            Log.d("desctorey===","destroy");

        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     *  show toast
     * @param toast_string
     */
    public void showToast(String toast_string) {

        Toast.makeText(_context, toast_string, Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {

            default:
                break;
        }

        return false;
    }

    public void checkPermissions(String[] PERMISSIONS) {
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        if (hasPermissions(this, PERMISSIONS)) {

        } else {
            ActivityCompat.requestPermissions(this, PERMISSIONS, 101);
        }
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public void showAlertDialogForQuestion(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.layout_alert_question, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        TextView titleBox = (TextView) view.findViewById(R.id.title);
       // titleBox.setText(title);
        TextView contentBox = (TextView) view.findViewById(R.id.content);
        //contentBox.setText(content);
        TextView noButton = (TextView) view.findViewById(R.id.no_button);
        EditText editText =(EditText)view.findViewById(R.id.passcode);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView yesButton = (TextView) view.findViewById(R.id.yes_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("password123")){
                    dialog.dismiss();
                    gotoapppage();
                }else{
                    Toast.makeText(activity, "Please enter correct protect key", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ImageView cancelButton = (ImageView) view.findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        // Get screen width and height in pixels
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        // The absolute width of the available display size in pixels.
        int displayWidth = displayMetrics.widthPixels;
        // The absolute height of the available display size in pixels.
        int displayHeight = displayMetrics.heightPixels;

        // Initialize a new window manager layout parameters
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();

        // Copy the alert dialog window attributes to new layout parameter instance
        layoutParams.copyFrom(dialog.getWindow().getAttributes());

        // Set alert dialog width equal to screen width 80%
        int dialogWindowWidth = (int) (displayWidth * 0.8f);
        // Set alert dialog height equal to screen height 80%
        //    int dialogWindowHeight = (int) (displayHeight * 0.8f);

        // Set the width and height for the layout parameters
        // This will bet the width and height of alert dialog
        layoutParams.width = dialogWindowWidth;
        //      layoutParams.height = dialogWindowHeight;

        // Apply the newly created layout parameters to the alert dialog window
        dialog.getWindow().setAttributes(layoutParams);
        dialog.setCancelable(false);
    }

    private void gotoapppage() {
        Intent intent = new Intent(this, SitelistActivity.class);
        startActivity(intent);
        finish();
    }

    public MainModel getcurrentmodel(String id){
        MainModel mainModel = new MainModel();
        ArrayList<MainModel> mainModels = Preference.getInstance().getSharedPreference(this, PrefConst.PREFKEY_MAINMODELS);
        for(int i=0; i<mainModels.size(); i++){
            if(mainModels.get(i).id.equals(id)){
                mainModel = mainModels.get(i);
            }
        }
        return  mainModel;
    }
    public int getcurrentmodel_position(String id){
        int position = -1;
        ArrayList<MainModel> mainModels = Preference.getInstance().getSharedPreference(this, PrefConst.PREFKEY_MAINMODELS);
        for(int i=0; i<mainModels.size(); i++){
            if(mainModels.get(i).id.equals(id)){
               position = i;
            }
        }
        return  position;
    }


}
