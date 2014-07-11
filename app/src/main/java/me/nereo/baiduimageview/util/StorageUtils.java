package me.nereo.baiduimageview.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by Administrator on 2014-07-08.
 */
public class StorageUtils {

    public static File getCacheDirectory(){
        File sdcard = Environment.getExternalStorageDirectory();
        File cache = new File(sdcard, "baidu-image");
        if(!cache.exists()){
            cache.mkdir();
        }
        return cache;
    }

    public static File getCacheDirectory(Context context){
        return context.getCacheDir();
    }

}
