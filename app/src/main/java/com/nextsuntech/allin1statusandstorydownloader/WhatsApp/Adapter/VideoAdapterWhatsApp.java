package com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.nextsuntech.allin1statusandstorydownloader.Model.StatusModel;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapterWhatsApp extends RecyclerView.Adapter<VideoAdapterWhatsApp.VideoAdapterWhatsAppVH> {

    private final List<StatusModel> videoList;
    Context context;
    VideoFragment videoFragment;

    public VideoAdapterWhatsApp(List<StatusModel> videoList, Context context, VideoFragment videoFragment) {
        this.videoList = videoList;
        this.context = context;
        this.videoFragment = videoFragment;
    }

    @NonNull
    @Override
    public VideoAdapterWhatsApp.VideoAdapterWhatsAppVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_video, parent, false);
        return new VideoAdapterWhatsAppVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapterWhatsApp.VideoAdapterWhatsAppVH holder, int position) {
        StatusModel statusModel = videoList.get(position);
        holder.imageViewIV.setImageBitmap(statusModel.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public class VideoAdapterWhatsAppVH extends RecyclerView.ViewHolder {

        ImageView imageViewIV;
        ImageView downloadBT;

        public VideoAdapterWhatsAppVH(@NonNull View itemView) {
            super(itemView);
            imageViewIV = itemView.findViewById(R.id.iv_thumbnail_rowImage);
            downloadBT = itemView.findViewById(R.id.ib_download_rowImage);
        }
    }
}
