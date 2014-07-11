package me.nereo.baiduimageview.util;

import android.util.Log;

import me.nereo.baiduimageview.BuildConfig;

public class Logger {

	private static final String TAG = "Firefly";
	
	public static void v(String msg){
		if(BuildConfig.DEBUG)
			Log.v(TAG, msg);
	}
	
	public static void d(String msg){
		if(BuildConfig.DEBUG)
			Log.d(TAG, msg);
	}
	
	public static void i(String msg){
		if(BuildConfig.DEBUG)
			Log.i(TAG, msg);
	}
	
	public static void w(String msg){
		if(BuildConfig.DEBUG)
			Log.w(TAG, msg);
	}
	
	public static void e(String msg){
		if(BuildConfig.DEBUG)
			Log.e(TAG, msg);
	}
	
}
