package com.nextsuntech.allin1statusandstorydownloader.GBWhatsApp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nextsuntech.allin1statusandstorydownloader.Constant.Constant;
import com.nextsuntech.allin1statusandstorydownloader.Model.WhatsAppModelClass;
import com.nextsuntech.allin1statusandstorydownloader.R;
import com.nextsuntech.allin1statusandstorydownloader.WhatsApp.Adapter.WhatsAppRecyclerViewAdapter;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GBWhatsAppAdapterClass extends RecyclerView.Adapter<GBWhatsAppAdapterClass.VH> {

    private Context mContext;
    private ArrayList<Object> filesList;

    public GBWhatsAppAdapterClass(Context mContext, ArrayList<Object> filesList) {
        this.mContext = mContext;
        this.filesList = filesList;
    }

    @NonNull
    @Override
    public GBWhatsAppAdapterClass.VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_gb_whatsapp, parent , false);
        return new GBWhatsAppAdapterClass.VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GBWhatsAppAdapterClass.VH holder, @SuppressLint("RecyclerView") int position) {
        final WhatsAppModelClass files = (WhatsAppModelClass) filesList.get(position);
        if (files.getUri().toString().endsWith(".mp4")) {
            holder.playBT.setVisibility(View.VISIBLE);
        } else {
            holder.playBT.setVisibility(View.INVISIBLE);
        }

        Glide.with(mContext).load(files.getUri()).into(holder.imageThumbnail);

        CheckFolder();
        holder.downloadBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckFolder();

                final String path = ((WhatsAppModelClass) filesList.get(position)).getPath();
                final File file = new File(path);

                String destPath = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME2;
                File destFile = new File(destPath);

                try {
                    FileUtils.copyFileToDirectory(file, destFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                MediaScannerConnection.scanFile(mContext,
                        new String[]{destPath+files.getFileName()},
                        new String[]{"*/*"},
                        new MediaScannerConnection.MediaScannerConnectionClient() {
                            @Override
                            public void onScanCompleted(String path, Uri uri) {

                            }

                            @Override
                            public void onMediaScannerConnected() {

                            }
                        }
                );

                Toast.makeText(mContext, "Saved to:" + destPath + files.getFileName(), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void CheckFolder() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + Constant.SAVE_FOLDER_NAME2;
        File dir = new File(path);

        boolean isDirectoryCreated = dir.exists();

        if (!isDirectoryCreated) {
            isDirectoryCreated = dir.mkdirs();
        }

        if (isDirectoryCreated) {
            Log.d("Folder", "Already Created");
        }
    }

    @Override
    public int getItemCount() {
        return  filesList.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        ImageView imageThumbnail;
        ImageView playBT;
        Button downloadBT;

        public VH(@NonNull View itemView) {
            super(itemView);

            imageThumbnail = itemView.findViewById(R.id.iv_row_GBWhatsApp_Image);
            playBT = itemView.findViewById(R.id.iv_row_GBWhatsApp_PlayButton);
            downloadBT = itemView.findViewById(R.id.bt_GBWhatsApp_download);
        }
    }
}
