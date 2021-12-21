package com.nextsuntech.allin1statusandstorydownloader.WhatsApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.Model.WhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.WhatsAppRecyclerViewAdapter;

import java.io.File;
import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

public class WhatsAppActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView backBT;
    SwipeRefreshLayout swipeRefreshWhatApp;
    RecyclerView whatsAppRV;

    WhatsAppRecyclerViewAdapter whatsAppRecyclerViewAdapter;
    File[] files;
    ArrayList<Object> filesLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whats_app);

        initViews();
        showPermissionDialog();
        checkPermission();
        version10();
    }

    private void initViews() {
        swipeRefreshWhatApp = findViewById(R.id.sp_swipere);
        whatsAppRV = findViewById(R.id.rv_whatsApp);
        backBT = findViewById(R.id.iv_WhatsAppActivity_back);


        backBT.setOnClickListener(this);

        swipeRefreshWhatApp.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                version10();
                (
                        new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshWhatApp.setRefreshing(false);
                        Toast.makeText(WhatsAppActivity.this, "Refresh!", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);
            }
        });
    }

    private void showPermissionDialog() {
        if (SDK_INT >= Build.VERSION_CODES.R) {

            try {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                intent.addCategory("android.intent.category.DEFAULT");
                intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
                startActivityForResult(intent, 2000);
            } catch (Exception e) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivityForResult(intent, 2000);

            }

        } else
            ActivityCompat.requestPermissions(this,
                    new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, 333);
    }

    private boolean checkPermission() {
        if (SDK_INT >= Build.VERSION_CODES.R) {
            return Environment.isExternalStorageManager();
        } else {
            int write = ContextCompat.checkSelfPermission(getApplicationContext(),
                    WRITE_EXTERNAL_STORAGE);
            int read = ContextCompat.checkSelfPermission(getApplicationContext(),
                    READ_EXTERNAL_STORAGE);

            return write == PackageManager.PERMISSION_GRANTED &&
                    read == PackageManager.PERMISSION_GRANTED;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 333) {
            if (grantResults.length > 0) {
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (read && write) {

                } else {

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {

                } else {
                }
            }
        }
    }

    private void version10(){
        if (SDK_INT <= Build.VERSION_CODES.R){
            setRefreshLayout();
        }else {
            setRefreshLayouts();
        }
    }

    private void setRefreshLayout() {
        filesLists.clear();
        whatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        whatsAppRV.setLayoutManager(gridLayoutManager);
        whatsAppRecyclerViewAdapter = new WhatsAppRecyclerViewAdapter(WhatsAppActivity.this, getData());
        whatsAppRV.setAdapter(whatsAppRecyclerViewAdapter);
        whatsAppRecyclerViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<Object> getData() {
        WhatsAppModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAMES;
        File targetDirectory = new File(targetPath);

        files = targetDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new WhatsAppModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFileName(file.getName());

            if (!f.getUri().toString().endsWith(".endmedia")) {
                filesLists.add(f);
            }
        }
        return filesLists;
    }

    private void setRefreshLayouts() {
        filesLists.clear();
        whatsAppRV.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        whatsAppRV.setLayoutManager(gridLayoutManager);
        whatsAppRecyclerViewAdapter = new WhatsAppRecyclerViewAdapter(WhatsAppActivity.this, getsData());
        whatsAppRV.setAdapter(whatsAppRecyclerViewAdapter);
        whatsAppRecyclerViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<Object> getsData() {
        WhatsAppModelClass f;
        String targetPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.FOLDER_NAME;
        File targetDirectory = new File(targetPath);

        files = targetDirectory.listFiles();

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            f = new WhatsAppModelClass();
            f.setUri(Uri.fromFile(file));
            f.setPath(files[i].getAbsolutePath());
            f.setFileName(file.getName());

            if (!f.getUri().toString().endsWith(".endmedia")) {
                filesLists.add(f);
            }
        }
        return filesLists;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.iv_WhatsAppActivity_back:
                finish();
                break;
        }
    }
}