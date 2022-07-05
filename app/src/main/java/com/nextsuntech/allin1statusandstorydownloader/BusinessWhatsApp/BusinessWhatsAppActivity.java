package com.nextsuntech.allin1statusandstorydownloader.BusinessWhatsApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
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
import com.nextsuntech.allin1statusandstorydownloader.BusinessWhatsApp.Adapter.BusinessWhatsAppAdapterClass;
import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.Model.BusinessWhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.Model.WhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.WhatsAppRecyclerViewAdapter;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.WhatsAppActivity;

import java.io.File;
import java.util.ArrayList;

public class BusinessWhatsAppActivity extends AppCompatActivity implements View.OnClickListener {


    private AdView mAdView;
    ImageView backBT;
    SwipeRefreshLayout swipeRefreshWhatApp;
    RecyclerView businessWhatsAppRV;

    BusinessWhatsAppAdapterClass businessWhatsAppAdapterClass;
    File[] files;
    ArrayList<Object> filesLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_whats_app);

        swipeRefreshWhatApp = findViewById(R.id.sp_swipere);
        businessWhatsAppRV = findViewById(R.id.rv_businessWhatsApp);
        backBT = findViewById(R.id.iv_BusinessWhatsApp_back);


        backBT.setOnClickListener(this);

        swipeRefreshWhatApp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //version10();
                setRefreshLayout();
                (
                        new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshWhatApp.setRefreshing(false);
                        Toast.makeText(BusinessWhatsAppActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });

        BannerAdsBusinessWhatsApp();
        version10();
    }

    private void version10() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setRefreshLayout();
        } else {
            setRefreshLayouts();
        }
    }

    private void setRefreshLayouts() {
        filesLists.clear();
        businessWhatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        businessWhatsAppRV.setLayoutManager(gridLayoutManager);
        businessWhatsAppAdapterClass = new BusinessWhatsAppAdapterClass(BusinessWhatsAppActivity.this,getData1(),BusinessWhatsAppActivity.this);
        businessWhatsAppAdapterClass.notifyDataSetChanged();
    }

    private ArrayList<Object> getData1() {
        BusinessWhatsAppModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.BUSINESS_WHATSAPP;
        File targetDirectory = new File(targetPath);

        files = targetDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new BusinessWhatsAppModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFileName(file.getName());

            if (!f.getUri().toString().endsWith(".endmedia")) {
                filesLists.add(f);
            }
        }
        return filesLists;
    }

    private void setRefreshLayout() {
        filesLists.clear();
        businessWhatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        businessWhatsAppRV.setLayoutManager(gridLayoutManager);
        businessWhatsAppAdapterClass = new BusinessWhatsAppAdapterClass(BusinessWhatsAppActivity.this, getData(),BusinessWhatsAppActivity.this);
        businessWhatsAppRV.setAdapter(businessWhatsAppAdapterClass);
        businessWhatsAppAdapterClass.notifyDataSetChanged();
    }

    private ArrayList<Object> getData() {
        BusinessWhatsAppModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.BUSINESS_WHATSAPP;
        File targetDirectory = new File(targetPath);

        files = targetDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new BusinessWhatsAppModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFileName(file.getName());

            if (!f.getUri().toString().endsWith(".endmedia")) {
                filesLists.add(f);
            }
        }
        return filesLists;
    }


    // Admob ads method
    private void BannerAdsBusinessWhatsApp() {

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_BusinessWhatsApp_back:
                finish();
                break;
        }
    }
}