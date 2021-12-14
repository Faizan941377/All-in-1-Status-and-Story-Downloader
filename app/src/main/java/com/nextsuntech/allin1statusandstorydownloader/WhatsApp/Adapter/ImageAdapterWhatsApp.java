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
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Fragments.ImageFragment;

import java.io.IOException;
import java.util.List;

public class ImageAdapterWhatsApp extends RecyclerView.Adapter<ImageAdapterWhatsApp.ImageAdapterWhatsAppVH> {

    private final List<StatusModel> imageList;
    Context context;
    ImageFragment imageFragment;

    public ImageAdapterWhatsApp(Context context, List<StatusModel> imageList, ImageFragment imageFragment) {
        this.imageList = imageList;
        this.context= context;
        this.imageFragment = imageFragment;
    }

    @NonNull
    @Override
    public ImageAdapterWhatsApp.ImageAdapterWhatsAppVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_image,parent,false);
        return new ImageAdapterWhatsAppVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapterWhatsApp.ImageAdapterWhatsAppVH holder, int position) {
        StatusModel statusModel = imageList.get(position);
        holder.imageViewIV.setImageBitmap(statusModel.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageAdapterWhatsAppVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView imageViewIV;
        ImageButton downloadBT;

        public ImageAdapterWhatsAppVH(@NonNull View itemView) {
            super(itemView);
            imageViewIV = itemView.findViewById(R.id.iv_thumbnail_rowImage);
            downloadBT = itemView.findViewById(R.id.ib_download_rowImage);

            downloadBT.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ib_download_rowImage:
                    StatusModel statusModel = imageList.get(getAdapterPosition());
                    if (statusModel != null) {

                        try {
                            imageFragment.downloadImage(statusModel);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
        }
    }
}
