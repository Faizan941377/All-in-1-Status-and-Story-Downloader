package com.nextsuntech.allin1statusandstorydownloader.Constant;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class Constant {
    public static final String FOLDER_NAME = "/WhatsApp/Media/.Statuses";
    public static final String FOLDER_NAMES = "/Android/media/com.whatsapp/WhatsApp/Media/.Statuses";
    public static final String BUSINESS_WHATSAPP = "/Android/media/com.whatsapp.w4b/WhatsApp Business/Media/.Statuses";
    public static final String GB_WHATSAPP = "/Android/media/com.gbwhatsapp/GBWhatsApp/Media/.Statuses";
    public static final String SAVE_FOLDER_NAME = "/All in 1 Status/WhatsApp/";
    public static final String SAVE_FOLDER_NAME1 = "/All in 1 Status/WABusiness/";
    public static final String SAVE_FOLDER_NAME2 = "/All in 1 Status/GBWhatsApp/";
    public static final String DIRECTORY_INSTAGRAM = "/All in 1 Status/Instagram/";

    public static void download(String downloadPath, String destinationPath, Context context, String fileName) {
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
