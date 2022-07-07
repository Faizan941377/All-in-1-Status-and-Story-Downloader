package com.nextsuntech.allin1statusandstorydownloader.WhatsApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.Model.WhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.WhatsAppRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class WhatsAppActivity extends AppCompatActivity implements View.OnClickListener {

    private AdView mAdView;
    ImageView backBT;
    SwipeRefreshLayout swipeRefreshWhatApp;
    RecyclerView whatsAppRV;

    WhatsAppRecyclerViewAdapter whatsAppRecyclerViewAdapter;
    File[] files;
    ArrayList<Object> filesLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);

        initViews();
        version10();
        BannerAdsDashboard();
    }

    private void initViews() {
        swipeRefreshWhatApp = findViewById(R.id.sp_swipere);
        whatsAppRV = findViewById(R.id.rv_whatsApp);
        backBT = findViewById(R.id.iv_WhatsAppActivity_back);


        backBT.setOnClickListener(this);

        swipeRefreshWhatApp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                version10();
                (
                        new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshWhatApp.setRefreshing(false);
                        Toast.makeText(WhatsAppActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

    private void version10() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setRefreshLayout();
        } else{
            setRefreshLayouts();
        }
    }

    private void setRefreshLayout() {
        filesLists.clear();
        whatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        whatsAppRV.setLayoutManager(gridLayoutManager);
        whatsAppRecyclerViewAdapter = new WhatsAppRecyclerViewAdapter(WhatsAppActivity.this, getData(),WhatsAppActivity.this);
        whatsAppRV.setAdapter(whatsAppRecyclerViewAdapter);
        whatsAppRecyclerViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<Object> getData() {
        try {
        WhatsAppModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAMES;
        File targetDirectory = new File(targetPath);

        files = targetDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new WhatsAppModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFileName(file.getName());

            if (!f.getUri().toString().endsWith(".endmedia")) {
                filesLists.add(f);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Please download whatsApp first", Toast.LENGTH_SHORT).show();
        }
        return filesLists;
    }

    private void setRefreshLayouts() {
        filesLists.clear();
        whatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        whatsAppRV.setLayoutManager(gridLayoutManager);
        whatsAppRecyclerViewAdapter = new WhatsAppRecyclerViewAdapter(WhatsAppActivity.this, getsData1(),WhatsAppActivity.this);
        whatsAppRV.setAdapter(whatsAppRecyclerViewAdapter);
        whatsAppRecyclerViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<Object> getsData1() {
        try {
            WhatsAppModelClass f;
            String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAME;
            File targetDirectory = new File(targetPath);

            files = targetDirectory.listFiles();

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                f = new WhatsAppModelClass();
                f.setUri(Uri.fromFile(file));
                f.setPath(files[i].getAbsolutePath());
                f.setFileName(file.getName());

                if (!f.getUri().toString().endsWith(".endmedia")) {
                    filesLists.add(f);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Please download whatsApp first", Toast.LENGTH_SHORT).show();
        }
        return filesLists;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.iv_WhatsAppActivity_back:
                finish();
                break;
        }
    }

    // Admob ads method
    private void BannerAdsDashboard() {

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                super.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
                super.onAdFailedToLoad(adError);
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
                super.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }
}