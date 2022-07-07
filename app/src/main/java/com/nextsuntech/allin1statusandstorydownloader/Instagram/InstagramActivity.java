package com.nextsuntech.allin1statusandstorydownloader.Instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.ReelFragment;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.IGTVFragment;
import com.nextsuntech.allin1statusandstorydownloader.R;

import java.util.ArrayList;

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
        instagramAdapter.AddFragment(new IGTVFragment(),"IGTV");

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