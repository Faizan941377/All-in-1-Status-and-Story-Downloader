package com.nextsuntech.allin1statusandstorydownloader.Instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.hcr2bot.instagramvideosdownloader.InstaVideo;
import com.nextsuntech.allin1statusandstorydownloader.R;

public class InstagramActivity extends AppCompatActivity implements View.OnClickListener {

    CardView downloadBT;
    EditText pastLinkET;
    ImageView backBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram);

      //  pastBT = findViewById(R.id.cv_instagram_Past);
        downloadBT = findViewById(R.id.cv_instagram_Download);
        pastLinkET = findViewById(R.id.et_instagram_link);
        backBT = findViewById(R.id.ic_instagram_back);


       // pastBT.setOnClickListener(this);
        downloadBT.setOnClickListener(this);
        backBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cv_instagram_Download:
                try {
                    InstaVideo.downloadVideo(InstagramActivity.this, pastLinkET.getText().toString());
                    String downloadButton = pastLinkET.getText().toString();

                    if (downloadButton.length()==0){
                        Toast.makeText(this, "Please enter your video url", Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                break;

            case R.id.ic_instagram_back:
                finish();
                break;
        }
    }
}