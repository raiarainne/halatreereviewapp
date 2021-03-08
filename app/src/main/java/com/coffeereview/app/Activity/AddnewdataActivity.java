package com.coffeereview.app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.coffeereview.app.Model.MainModel;
import com.coffeereview.app.Preference.PrefConst;
import com.coffeereview.app.Preference.Preference;
import com.coffeereview.app.R;
import com.coffeereview.app.Utils.FileDownloader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AddnewdataActivity extends BaseActivity {
    EditText sitename, siteurl;
    TextView txv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewdata);
        sitename =(EditText)findViewById(R.id.sitename);
        siteurl =(EditText)findViewById(R.id.siteurl);
        txv_title =(TextView)findViewById(R.id.txv_title);
        if(PrefConst.currentitemid.length()==0) txv_title.setText("Add New Site");
        else{
            MainModel mainModel = getcurrentmodel(PrefConst.currentitemid);
            txv_title.setText("Update Site Data");
            sitename.setText(mainModel.sitename);
            siteurl.setText(mainModel.siteurl);
        }

       /* new DownloadFile().execute(imageModel.getImageurl().replaceAll(" ", "%20"), imageModel.getImagefilename());

        Glide.with(_context)
                .load(Uri.fromFile(new File(item.getPath().replace("/storage/emulated/0","/sdcard"))))
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //.placeholder(R.drawable.loading_spinner)
                .into(holder.imv_image);*/
    }

    public void savedata(View view) {
        if(sitename.getText().toString().length()==0){
            Toast.makeText(_context, "Please enter site name", Toast.LENGTH_SHORT).show();
        }else if(siteurl.getText().toString().length()==0){
            Toast.makeText(_context, "Please enter site url", Toast.LENGTH_SHORT).show();
        }else{
            String qrcodeurl = "https://chart.googleapis.com/chart?chs=420x420&cht=qr&chl="+siteurl.getText().toString().replaceAll(" ","%20")+"&choe=UTF-8";
            if(PrefConst.currentitemid.length()==0){ // Add New
                MainModel mainModel = new MainModel();
                Long tsLong = System.currentTimeMillis()/1000;
                String ts = tsLong.toString();
                mainModel.id  = ts;
                mainModel.sitename = sitename.getText().toString();
                mainModel.siteurl = siteurl.getText().toString();
                ArrayList<MainModel> mainModels = Preference.getInstance().getSharedPreference(this, PrefConst.PREFKEY_MAINMODELS);
                mainModels.add(mainModel);
                Preference.getInstance().putSharedPreference(this, PrefConst.PREFKEY_MAINMODELS, mainModels);
            }else{ //Update workdlog
                MainModel mainModel = getcurrentmodel(PrefConst.currentitemid);
                if(mainModel.id.length()==0){
                    Toast.makeText(_context, "There is no same item", Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    mainModel.sitename = sitename.getText().toString();
                    mainModel.siteurl = siteurl.getText().toString();
                    ArrayList<MainModel> mainModels = Preference.getInstance().getSharedPreference(this, PrefConst.PREFKEY_MAINMODELS);
                    mainModels.set(getcurrentmodel_position(mainModel.id), mainModel);
                    Preference.getInstance().putSharedPreference(this, PrefConst.PREFKEY_MAINMODELS, mainModels);
                }
            }
            Toast.makeText(_context, "Data saved successfully", Toast.LENGTH_SHORT).show();
            backtolist();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backtolist();
    }

    public void backtolist(){
        PrefConst.currentitemid = "";
        Intent intent = new Intent(this, SitelistActivity.class);
        startActivity(intent);
        finish();
    }

    public void goback(View view) {
        backtolist();
    }

    private class DownloadFile extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.show();
        }

        @Override
        protected Void doInBackground(String... strings) {

           /* String fileUrl = strings[0];
            String fileName = strings[1];
            File folder = new File(Environment.getExternalStorageDirectory()
                    + "/Civilog/");
            boolean var = false;
            if (!folder.exists())
                var = folder.mkdir();
            System.out.println("" + var);
            boolean var1 = false;
            File subfolder = new File(Environment.getExternalStorageDirectory()
                    + "/Civilog/"+ mainModel.getJobnumber()+"_"+mainModel.getStateaddress());
            if (!subfolder.exists())
                var1 = subfolder.mkdir();
            System.out.println("" + subfolder.toString());
            File pdfFile = new File(subfolder, fileName);
            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            if(fileName.contains("Siteplan")){
                Uri fileuri = FileProvider.getUriForFile(mainActivity, "com.xianshu.civilog.provider", pdfFile);
                Log.d("siteplanpath==", fileuri.getPath());
                // mainModel.setSiteplanphotopath(fileuri.getPath());
                mainModel.addnewsiteplanphoto(fileuri.getPath());
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
           /* if(numberof_downloadedfiles==imageModels.size()-1){
                mainModel.setEditedstatus(0);
                mainActivity.updatesavedmodels(mainModel,modelstatus);
                progressBar.dismiss();
                Toast.makeText(mainActivity, "Download Completed.", Toast.LENGTH_SHORT).show();
            }
            numberof_downloadedfiles++;*/
            // Toast.makeText(mainActivity, "Download PDf successfully", Toast.LENGTH_SHORT).show();

            Log.d("Download complete", "----------");
        }
    }
}