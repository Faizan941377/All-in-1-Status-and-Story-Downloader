package com.nextsuntech.allin1statusandstorydownloader.Dashboard;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.RelativeLayout;

import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.WhatsAppActivity;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout whatsAppBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        whatsAppBT = findViewById(R.id.rel_whatsApp);

        whatsAppBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rel_whatsApp:
                Intent intent = new Intent(this, WhatsAppActivity.class);
                startActivity(intent);
                break;
        }
    }
}
