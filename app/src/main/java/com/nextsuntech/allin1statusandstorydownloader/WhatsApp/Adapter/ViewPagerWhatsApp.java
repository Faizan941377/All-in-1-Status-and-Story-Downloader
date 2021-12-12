package com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.ImageFragment;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.VideoFragment;

public class ViewPagerWhatsApp extends FragmentPagerAdapter {

    private ImageFragment imageFragment;
    private VideoFragment videoFragment;

    public ViewPagerWhatsApp(@NonNull FragmentManager fm) {
        super(fm);
        imageFragment = new ImageFragment();
        videoFragment = new VideoFragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return imageFragment;
        }
        else {
            return videoFragment;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (position == 0)
        {
            return "Images";
        }
        else {
            return "Videos";
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}