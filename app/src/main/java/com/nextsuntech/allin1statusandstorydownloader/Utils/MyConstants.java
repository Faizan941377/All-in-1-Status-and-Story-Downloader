package com.nextsuntech.allin1statusandstorydownloader.Utils;

import android.os.Environment;

import java.io.File;

public class MyConstants {

    public  static final File STATUS_DIRECTORY = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator +"Android/media/com.whatsapp/WhatsApp/Media/.Statuses");

    public  static final File STATUS_DIRECTORYLOWVERSION = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
            File.separator +"WhatsApp/Media/.Statuses");

    public static final String APP_DIR = Environment.getExternalStorageDirectory() +File.separator
            +"WhatsAppStatusProDir";

    public static  final int THUMSIZE = 128;
}
