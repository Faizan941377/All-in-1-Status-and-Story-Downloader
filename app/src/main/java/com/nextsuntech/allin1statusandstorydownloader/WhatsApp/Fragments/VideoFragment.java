package com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nextsuntech.allin1statusandstorydownloader.Model.StatusModel;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.Utils.MyConstants;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.VideoAdapterWhatsApp;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class VideoFragment extends Fragment {

    ArrayList<StatusModel> videoModelArrayList;
    Handler handler;
    VideoAdapterWhatsApp videoAdapterWhatsApp;
    RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        handler = new Handler();

        videoModelArrayList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerViewVideo);
        progressBar = view.findViewById(R.id.progressBarVideo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

        getStatus();
        return view;
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

                            if (statusModel.isVideo()){
                                videoModelArrayList.add(statusModel);
                            }
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.GONE);
                                videoAdapterWhatsApp = new VideoAdapterWhatsApp(getContext(),videoModelArrayList,VideoFragment.this);
                                recyclerView.setAdapter(videoAdapterWhatsApp);
                                videoAdapterWhatsApp.notifyDataSetChanged();
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
}