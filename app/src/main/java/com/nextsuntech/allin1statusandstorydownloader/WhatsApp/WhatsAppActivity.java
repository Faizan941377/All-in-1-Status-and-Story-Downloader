package com.nextsuntech.allin1statusandstorydownloader.WhatsApp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.ImageAdapterWhatsApp;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.ViewPagerWhatsApp;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WhatsAppActivity extends AppCompatActivity {

    ViewPager viewPager;
    MaterialToolbar toolbar;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);
        setSupportActionBar(toolbar);

        toolbar = findViewById(R.id.toolbar);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        viewPager.setAdapter(new ViewPagerWhatsApp(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }
}