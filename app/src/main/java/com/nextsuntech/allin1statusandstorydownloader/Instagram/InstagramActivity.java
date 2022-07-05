package com.nextsuntech.allin1statusandstorydownloader.Instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.ReelFragment;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.TVFragment;
import com.nextsuntech.allin1statusandstorydownloader.MainActivity;
import com.nextsuntech.allin1statusandstorydownloader.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Objects;

public class InstagramActivity extends AppCompatActivity implements View.OnClickListener {


    ImageView backBT;
    TabLayout tabLayout;
    ViewPager viewPager;


    InstagramActivity.InstagramAdapter instagramAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

        tabLayout = findViewById(R.id.tab_instagram_pages);
        viewPager = findViewById(R.id.viewPager);

        backBT = findViewById(R.id.ic_instagram_back);

        instagramAdapter = new InstagramAdapter(getSupportFragmentManager());
        //here adding the fragments
        instagramAdapter.AddFragment(new ReelFragment(),"Reel");
        instagramAdapter.AddFragment(new TVFragment(),"TV");

        viewPager.setAdapter(instagramAdapter);
        tabLayout.setupWithViewPager(viewPager);



        backBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ic_instagram_back:
                finish();
                break;
        }
    }

    private class InstagramAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        ArrayList<String> stringArrayLists = new ArrayList<>();

        public void AddFragment(Fragment fragment,String s){
            fragmentArrayList.add(fragment);
            stringArrayLists.add(s);
        }

        public InstagramAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayLists.get(position);
        }
    }
}