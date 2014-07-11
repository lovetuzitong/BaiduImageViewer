package me.nereo.baiduimageview;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;

import me.nereo.baiduimageview.util.StorageUtils;

/**
 * Created by Administrator on 2014-07-07.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader(getApplicationContext());
    }

    public static void initImageLoader(Context context){
        File cacheDir = StorageUtils.getCacheDirectory(context);
        ImageLoaderConfiguration cfg = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .diskCache(new UnlimitedDiscCache(cacheDir))
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024)
                .diskCacheFileCount(100)
                .build();
        ImageLoader.getInstance().init(cfg);
    }
}
