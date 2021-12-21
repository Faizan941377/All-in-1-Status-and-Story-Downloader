package com.nextsuntech.allin1statusandstorydownloader.Constant;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class Constant {
    public static final String FOLDER_NAME = "/WhatsApp/Media/.Statuses";
    public static final String FOLDER_NAMES = "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses";
    public static final String SAVE_FOLDER_NAME = "/All in 1 Status/WhatsApp/";
    public static final String SAVE_FACEBOOK_VIDEO = "/All in 1 Status/Facebook/";

    public static void downloadFacebookVideo(String downloadPath, String destinationPath, Context context, String fileName) {
        Toast.makeText(context, "Downloading Started", Toast.LENGTH_SHORT).show();
        Uri uri = Uri.parse(downloadPath);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle(fileName);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destinationPath + fileName);
        ((DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE)).enqueue(request);

    }
}
