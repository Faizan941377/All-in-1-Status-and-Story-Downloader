package com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.allin1statusandstorydownloader.Model.StatusModel;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.VideoFragment;

import java.util.ArrayList;

public class VideoAdapterWhatsApp extends RecyclerView.Adapter<VideoAdapterWhatsApp.VideoAdapterVH> {

    Context context;
    ArrayList<StatusModel> videoList;
    VideoFragment videoFragment;

    public VideoAdapterWhatsApp(Context context, ArrayList<StatusModel> videoList, VideoFragment videoFragment) {
        this.context = context;
        this.videoList = videoList;
        this.videoFragment = videoFragment;
    }

    @NonNull
    @Override
    public VideoAdapterWhatsApp.VideoAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_video,parent,false);
        return new VideoAdapterVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapterWhatsApp.VideoAdapterVH holder, int position) {
        StatusModel statusModel = videoList.get(position);
        holder.imageVideo.setImageBitmap(statusModel.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoAdapterVH extends RecyclerView.ViewHolder {

        ImageView imageVideo;
        ImageButton videoDownloadBT;

        public VideoAdapterVH(@NonNull View itemView) {
            super(itemView);

            imageVideo = itemView.findViewById(R.id.iv_thumbnail_rowVideo);
            videoDownloadBT = itemView.findViewById(R.id.ib_download_rowVideo);
        }
    }
}
