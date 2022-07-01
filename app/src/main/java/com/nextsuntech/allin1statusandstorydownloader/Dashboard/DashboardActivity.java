package com.nextsuntech.allin1statusandstorydownloader.Dashboard;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nextsuntech.allin1statusandstorydownloader.BusinessWhatsApp.BusinessWhatsAppActivity;
import com.nextsuntech.allin1statusandstorydownloader.Facebook.FacebookActivity;
import com.nextsuntech.allin1statusandstorydownloader.GBWhatsApp.GBWhatsAppActivity;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.InstagramActivity;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.WhatsAppActivity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STORAGE_PERMISSION_CODE = 100;

    private AdView mAdView;
    RelativeLayout whatsAppBT;
    RelativeLayout instagramBT;
    RelativeLayout facebookBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        whatsAppBT = findViewById(R.id.rel_whatsApp);
        instagramBT = findViewById(R.id.rl_instagram_button);
        facebookBT = findViewById(R.id.rl_facebook_button);

        whatsAppBT.setOnClickListener(this);
        instagramBT.setOnClickListener(this);
        facebookBT.setOnClickListener(this);

        if (checkPermission()){
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Permission was not granted", Toast.LENGTH_SHORT).show();
            requestPermission();
        }

        BannerAdsDashboard();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rel_whatsApp:
                WhatsAppOptions();
                break;

            case R.id.rl_instagram_button:
                Intent intent1 = new Intent(this, InstagramActivity.class);
                startActivity(intent1);
                break;

            case R.id.rl_facebook_button:
                Intent intent2 = new Intent(this, FacebookActivity.class);
                startActivity(intent2);
                break;
        }
    }

    private void WhatsAppOptions() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(DashboardActivity.this
                , R.style.BottomSheetDialogTheme);

        View bottomSheetView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.bottom_sheet_layout, (LinearLayout) findViewById(R.id.bottom_sheet_container));
        bottomSheetView.findViewById(R.id.bt_bottom_whatsApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WhatsAppActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetView.findViewById(R.id.iv_rowBottomSheet_Close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();

        bottomSheetView.findViewById(R.id.bt_bottom_gbWhatsApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GBWhatsAppActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();


        bottomSheetDialog.findViewById(R.id.bt_bottom_business).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BusinessWhatsAppActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            }
        });
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            try {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", this.getPackageName(), null);
                intent.setData(uri);
                storageActivityResultLauncher.launch(intent);

            } catch (Exception e) {

                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                storageActivityResultLauncher.launch(intent);
            }
        } else {

            //Android 11 below
            ActivityCompat.requestPermissions(DashboardActivity.this,
                    new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    private ActivityResultLauncher<Intent> storageActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        //Android is 11 (R) or above
                        if (Environment.isExternalStorageManager()) {
                            //Manage External Storage is Granted

                            //here we call the dirctory
                            Toast.makeText(DashboardActivity.this, "Permission is Granted", Toast.LENGTH_SHORT).show();
                        } else {
                            //Manage External Storage is Denied
                            Toast.makeText(DashboardActivity.this, "Permission is Denied", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //Android is Below 11(R)
                    }
                }
            }
    );

    public boolean checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android is 11 or above
            return Environment.isExternalStorageManager();
        } else {
            //Android is below 11(R)
            int write = ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
            return write == PackageManager.PERMISSION_GRANTED && read == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE){
            if (grantResults.length>0){
                //check each permission if granted or not
                boolean write  = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (write && read){
                    // External storage permission is granted


                    // here we call the find the directory


                }else {
                    // External storage permission is denied
                    Toast.makeText(this, "External Storage Permission is Denied", Toast.LENGTH_SHORT).show();
                }
            }
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
