package com.nextsuntech.allin1statusandstorydownloader.WhatsApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayoutMediator;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.ImageFragment;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.VideoFragment;
import com.nextsuntech.allin1statusandstorydownloader.databinding.ActivityWhatsAppBinding;

import java.util.ArrayList;
import java.util.List;

public class WhatsAppActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}