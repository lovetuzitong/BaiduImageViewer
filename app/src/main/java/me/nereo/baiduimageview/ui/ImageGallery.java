package me.nereo.baiduimageview.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

import me.nereo.baiduimageview.R;
import me.nereo.baiduimageview.model.Image;
import me.nereo.baiduimageview.ui.adapter.UrlPagerAdapter;
import me.nereo.baiduimageview.util.Logger;
import me.nereo.baiduimageview.util.UrlBuilder;
import me.nereo.baiduimageview.vender.BaiduApi;

/**
 * Created by Administrator on 2014-07-10.
 */
public class ImageGallery extends Activity {

    private ArrayList<String> urls = new ArrayList<String>();

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);

        urls = getIntent().getStringArrayListExtra("URLS");

        int i = getIntent().getIntExtra("POSITION", 0);

        mViewPager.setAdapter(new UrlPagerAdapter(this, urls));
        mViewPager.setCurrentItem(i);

        if(urls != null && urls.size()>0){

        }else{
            throw new IllegalArgumentException("no image object passed in");
        }

        final ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setTitle("详细");

    }

    /*
    private void loadImageByGallery(Image img, String col, String sort){
        OkHttpClient client = new OkHttpClient();
        String url = new UrlBuilder(BaiduApi.Image.ALBUM_IMAGES)
                .add("col", col)
                .add("tag", "全部")
                .add("sort", sort)
                .add("from", "1")
                .add("aid", img.getAlbumId())
                .add("cid", img.getId())
                .add("setid", img.getSetId())
                .add("dressid", img.getDressId())
                .add("pn", "0")
                .add("rn", "600")
                .build();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Logger.d(url);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Logger.d(response.body().string());
            }
        });
    }
    */
}
