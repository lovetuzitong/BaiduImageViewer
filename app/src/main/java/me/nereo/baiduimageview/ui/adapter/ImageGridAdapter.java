package me.nereo.baiduimageview.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import me.nereo.baiduimageview.R;
import me.nereo.baiduimageview.model.Image;

/**
 * Created by Administrator on 2014-07-07.
 */
public class ImageGridAdapter extends BaseAdapter {

    private List<Image> images = new ArrayList<Image>();
    private LayoutInflater mInflater;
    ImageLoader imageLoader = ImageLoader.getInstance();
    DisplayImageOptions options;
    private int itemWidth;

    private FrameLayout.LayoutParams mImageLayoutParams;

    private ImageLoadingListener animateFirstListener;

    private Drawable mDefaultImageDrawable = new ColorDrawable(Color.argb(255, 201, 201, 201));

    public ImageGridAdapter(Context ctx){
        mInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        options = new DisplayImageOptions.Builder()
                //.cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        mImageLayoutParams = new
                FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        animateFirstListener = new AnimateFirstDisplayListener();
    }

    public void setData(List<Image> images){
        if(images.size()>0){
            images.remove(images.size()-1);
        }
        this.images.addAll(images);
        //this.images = images;
        notifyDataSetChanged();
    }

    public List<Image> getData(){
        return images;
    }

    public void clearData(){
        if(images.size() > 0)
            images.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int i) {
        return images.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.valueOf(images.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view != null){
            holder = (ViewHolder) view.getTag();
        }else{
            view = mInflater.inflate(R.layout.item_image_grid, viewGroup, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.image);
            holder.textView = (TextView) view.findViewById(R.id.text);
            holder.albumNum = (TextView) view.findViewById(R.id.albumNum);
            view.setTag(holder);
        }

        Image img = (Image) getItem(i);
        if(img != null){

            if(img.getAlbumNum() > 1){
                if(holder.albumNum.getVisibility() != View.VISIBLE)
                    holder.albumNum.setVisibility(View.VISIBLE);
                holder.albumNum.setText(String.valueOf(img.getAlbumNum()));
            }else{
                if(holder.albumNum.getVisibility() != View.GONE)
                    holder.albumNum.setVisibility(View.GONE);
            }

            int imageHeight = 0;
            if(holder.imageView.getLayoutParams().height != imageHeight){
                double ratio;
                if(img.getImageWidth() == null || img.getImageHeight() == null){
                    ratio = 1;
                }else{
                    ratio = (double)img.getImageHeight() / (double)img.getImageWidth();
                   // System.out.println("iWidth: "+img.getImageWidth()+";iHeight: "+img.getImageHeight()+";"+"ratio: "+ratio);
                }
                imageHeight = (int) ( itemWidth*ratio );

                mImageLayoutParams = new FrameLayout.LayoutParams(itemWidth, imageHeight);

               // System.out.println("height1: "+imageHeight+"; width1:"+itemWidth);

            }

            holder.imageView.setLayoutParams(mImageLayoutParams);

            Bitmap bm = Bitmap.createBitmap(holder.imageView.getLayoutParams().width, holder.imageView.getLayoutParams().height, Bitmap.Config.RGB_565);

            holder.imageView.setImageBitmap(bm);
            //holder.imageView.setImageDrawable(mDefaultImageDrawable);

            //System.out.println("height: "+holder.imageView.getLayoutParams().height+"; width:"+holder.imageView.getLayoutParams().width);

            imageLoader.displayImage(img.getImageUrl(), holder.imageView, options, animateFirstListener);
            holder.textView.setText(img.getTitle());
        }

        return view;
    }

    public void setItemWidth(int width){
        if(itemWidth == width) return;

        itemWidth = width;
        mImageLayoutParams = new
                FrameLayout.LayoutParams(width, FrameLayout.LayoutParams.MATCH_PARENT);
        notifyDataSetChanged();
    }

    static class ViewHolder{
        ImageView imageView;
        TextView textView;
        TextView albumNum;
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener{
        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if(loadedImage != null){
                ImageView iv = (ImageView) view;
                if(!displayedImages.contains(imageUri)) {
                    FadeInBitmapDisplayer.animate(iv, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
