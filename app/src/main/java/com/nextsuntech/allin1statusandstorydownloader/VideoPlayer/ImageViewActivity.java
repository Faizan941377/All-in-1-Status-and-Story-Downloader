package com.nextsuntech.allin1statusandstorydownloader.VideoPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nextsuntech.allin1statusandstorydownloader.R;

public class ImageViewActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imageIV;
    ImageView backIV;
    TextView imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);


        imageIV = findViewById(R.id.iv_imageView_image);
        backIV = findViewById(R.id.iv_imageView_back);
        imagePath = findViewById(R.id.tv_imageView_imagePath);



        Intent i = getIntent();
        String image = i.getStringExtra("Image");
        imagePath.setText(image);
        Glide.with(this).load(image).into(imageIV);


        backIV.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_imageView_back:
                finish();
                break;
        }
    }
}