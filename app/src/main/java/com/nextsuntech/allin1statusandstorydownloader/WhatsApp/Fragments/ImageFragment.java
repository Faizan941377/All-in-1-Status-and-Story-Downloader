package com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.allin1statusandstorydownloader.Model.StatusModel;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.Utils.MyConstants;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.ImageAdapterWhatsApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;


public class ImageFragment extends Fragment {

    ArrayList<StatusModel> imageModelArrayList;
    Handler handler = new Handler();
    ImageAdapterWhatsApp imageAdapterWhatsApp;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image, container, false);

        imageModelArrayList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewImage);
        progressBar = view.findViewById(R.id.progressBarImages);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        getStatus();
        getStatusOldAndroidVersion();
        return view;
    }

    private void getStatusOldAndroidVersion() {
        if (MyConstants.STATUS_DIRECTORYLOWVERSION.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORYLOWVERSION.listFiles();
                    if (statusFiles!=null && statusFiles.length>0){
                        Arrays.sort(statusFiles);

                        for (final File statusFile:statusFiles){
                            StatusModel statusModel = new StatusModel(statusFile, statusFile.getName(),statusFile.getAbsolutePath());
                            statusModel.setThumbnail(getThumbnail(statusModel));

                            if (!statusModel.isVideo()){
                                imageModelArrayList.add(statusModel);
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                imageAdapterWhatsApp = new ImageAdapterWhatsApp(getContext(),imageModelArrayList,ImageFragment.this);
                                recyclerView.setAdapter(imageAdapterWhatsApp);
                                imageAdapterWhatsApp.notifyDataSetChanged();
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "dir doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private void getStatus() {
        if (MyConstants.STATUS_DIRECTORY.exists()){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    File[] statusFiles = MyConstants.STATUS_DIRECTORY.listFiles();
                    if (statusFiles!=null && statusFiles.length>0){
                        Arrays.sort(statusFiles);

                        for (final File statusFile:statusFiles){
                            StatusModel statusModel = new StatusModel(statusFile, statusFile.getName(),statusFile.getAbsolutePath());
                            statusModel.setThumbnail(getThumbnail(statusModel));

                            if (!statusModel.isVideo()){
                                imageModelArrayList.add(statusModel);
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                imageAdapterWhatsApp = new ImageAdapterWhatsApp(getContext(),imageModelArrayList,ImageFragment.this);
                                recyclerView.setAdapter(imageAdapterWhatsApp);
                                imageAdapterWhatsApp.notifyDataSetChanged();
                            }
                        });
                    }else {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "dir doesn't exist", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    private Bitmap getThumbnail(StatusModel statusModel) {
        if (statusModel.isVideo()){
            return ThumbnailUtils.createVideoThumbnail(statusModel.getFile().getAbsolutePath(),
                    MediaStore.Video.Thumbnails.MICRO_KIND);
        }else {
            return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(statusModel.getFile().getAbsolutePath()),
                    MyConstants.THUMSIZE,
                    MyConstants.THUMSIZE);
        }
    }

    public void downloadImage(StatusModel statusModel) throws IOException {
        File file  = new File(MyConstants.APP_DIR);
        if (!file.exists()){
            file.mkdirs();
        }

        File destFile = new File(file+File.separator + statusModel.getTitle());
        if (destFile.exists()){
            destFile.delete();
        }
        copyFile(statusModel.getFile(),destFile);
        Toast.makeText(getActivity(), "Download Completed", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(destFile));
        getActivity().sendBroadcast(intent);
    }

    private void copyFile(File file, File destFile) throws IOException {
        if (!destFile.getParentFile().exists()){
            destFile.getParentFile().mkdirs();
        }
        if (!destFile.exists()){
            destFile.createNewFile();
        }

        FileChannel source  = null;
        FileChannel destination = null;
        source = new FileInputStream(file).getChannel();
        destination = new FileInputStream(destFile).getChannel();
        destination.transferFrom(source,0,source.size());
        source.close();
        destination.close();
    }
}