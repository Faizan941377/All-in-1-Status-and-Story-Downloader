package com.nextsuntech.allin1statusandstorydownloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.ReelFragment;
import com.nextsuntech.allin1statusandstorydownloader.Instagram.Fragments.IGTVFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    InstagramAdapter instagramAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_instagram_pages);
        viewPager = findViewById(R.id.viewPager);

        instagramAdapter = new InstagramAdapter(getSupportFragmentManager());
        //here adding the fragments
        instagramAdapter.AddFragment(new ReelFragment(),"Reel");
        instagramAdapter.AddFragment(new IGTVFragment(),"TV");

        viewPager.setAdapter(instagramAdapter);
        tabLayout.setupWithViewPager(viewPager);
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