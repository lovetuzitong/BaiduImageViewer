package me.nereo.baiduimageview.util;

import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2014-07-08.
 */
public class UrlBuilder {

    private String url;
    private StringBuilder sb = new StringBuilder("?");

    public UrlBuilder(String url){
        this.url = url;
    }

    public UrlBuilder add(String key, String val){
        if(sb.length()>1){
            sb.append('&');
        }
        try {
            sb.append(URLEncoder.encode(key, "utf-8"))
                    .append('=')
                    .append(URLEncoder.encode(val, "utf-8"));
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return this;
    }

    public String build(){
        if(sb.length() == 1){
            throw new IllegalStateException("Form encoded body must have at least one part.");
        }
        return this.url+sb.toString();
    }
}
