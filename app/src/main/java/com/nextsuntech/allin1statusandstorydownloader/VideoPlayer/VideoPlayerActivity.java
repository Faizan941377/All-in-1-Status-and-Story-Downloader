package com.nextsuntech.allin1statusandstorydownloader.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.Model.BusinessWhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.R;

import java.io.File;
import java.util.ArrayList;

public class VideoPlayerActivity extends AppCompatActivity implements View.OnClickListener {

    TextView videoPath;
    VideoView playVideoVV;
    String str_Video_url;
    ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        playVideoVV = findViewById(R.id.vv_VideoPlayer_playVideo);
        videoPath = findViewById(R.id.tv_videoPlayer_videoPath);
        backIV = findViewById(R.id.iv_videoPlayer_back);


        videoPath.setText(str_Video_url = getIntent().getStringExtra("video"));

        str_Video_url = getIntent().getStringExtra("video");
        playVideoVV.setVideoPath(str_Video_url);
        MediaController mediaController = new MediaController(this);
        playVideoVV.setMediaController(mediaController);
        mediaController.setAnchorView(playVideoVV);
        playVideoVV.start();


        backIV.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_videoPlayer_back:
                finish();
                break;
        }
    }
}