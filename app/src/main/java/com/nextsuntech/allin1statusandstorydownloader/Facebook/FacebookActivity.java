package com.nextsuntech.allin1statusandstorydownloader.Facebook;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.http.Url;

public class FacebookActivity extends AppCompatActivity implements View.OnClickListener {

    CardView pastLinkBT;
    CardView downloadBT;
    EditText facebookLinkET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);

        pastLinkBT = findViewById(R.id.cv_facebook_Past);
        downloadBT = findViewById(R.id.cv_facebook_Download);
        facebookLinkET = findViewById(R.id.et_facebook_link);

        pastLinkBT.setOnClickListener(this);
        downloadBT.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.cv_facebook_Past:
                break;

            case R.id.cv_facebook_Download:

                break;
        }
    }
}