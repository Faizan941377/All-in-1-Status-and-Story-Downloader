package com.nextsuntech.allin1statusandstorydownloader.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.WhatsAppActivity;

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
        switch (v.getId()){

            case R.id.rel_whatsApp:
                Intent intent = new Intent(this, WhatsAppActivity.class);
                startActivity(intent);
                break;
        }
    }
}