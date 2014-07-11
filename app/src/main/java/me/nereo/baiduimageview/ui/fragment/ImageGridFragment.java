package me.nereo.baiduimageview.ui.fragment;

import android.app.Fragment;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.etsy.android.grid.StaggeredGridView;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.nereo.baiduimageview.R;
import me.nereo.baiduimageview.data.BaiduData;
import me.nereo.baiduimageview.model.Image;
import me.nereo.baiduimageview.model.ImageData;
import me.nereo.baiduimageview.ui.ImageGallery;
import me.nereo.baiduimageview.ui.adapter.ImageGridAdapter;
import me.nereo.baiduimageview.ui.view.LoadingFooter;
import me.nereo.baiduimageview.util.Logger;
import me.nereo.baiduimageview.util.UrlBuilder;
import me.nereo.baiduimageview.vender.BaiduApi;
import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;

/**
 * Created by Administrator on 2014-07-07.
 */
public class ImageGridFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnRefreshListener{

    private static final int PAGE_SIZE = 25;

    private StaggeredGridView mGridView;
    private PullToRefreshLayout mPullToRefreshLayout;

    private ImageGridAdapter mAdapter;

    private int mGridItemSpace;
    private String category;
    private String sort = "0";

    private LoadingFooter mLoadingFooter;
    private boolean onLoading = false;

    private int mCurrentPage = 1;

    private ArrayList<String> urls = new ArrayList<String>();

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onLoading = false;
            if(msg.arg1 == 0){
                String json = msg.getData().getString("data");
                Logger.d(json);
                Gson gson = new Gson();
                ImageData imageData = gson.fromJson(json, ImageData.class);
                final List<Image> images =  imageData.getImgs();
                if(mCurrentPage == 1){
                    mAdapter.clearData();
                    if(mPullToRefreshLayout.isRefreshing())
                        mPullToRefreshLayout.setRefreshComplete();
                }
                mAdapter.setData(images);
                mLoadingFooter.setState(LoadingFooter.State.SUCCESS);
            }else if(msg.arg1 == 1){
                mLoadingFooter.setState(LoadingFooter.State.FAILED);
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        category = getArguments().getString("TAG");
        sort = getArguments().getString("SORT");
        Logger.i("current sort: "+sort);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLoadingFooter = new LoadingFooter(getActivity());

        mGridItemSpace = getResources().getDimensionPixelSize(R.dimen.grid_space);

        mPullToRefreshLayout = (PullToRefreshLayout) getView().findViewById(R.id.ptr_layout);

        ActionBarPullToRefresh.from(getActivity())
                .allChildrenArePullable()
                .listener(this)
                .setup(mPullToRefreshLayout);

        mGridView = (StaggeredGridView) getView().findViewById(R.id.grid_view);
        mAdapter = new ImageGridAdapter(getActivity());
        mGridView.addFooterView(mLoadingFooter.getFooter());
        mGridView.setAdapter(mAdapter);
        mGridView.setOnScrollListener(new PauseOnScrollListener(ImageLoader.getInstance(), false, true){
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
                int lastVisibleItem = firstVisibleItem + visibleItemCount;
                boolean arrivedBottom = (firstVisibleItem > visibleItemCount) &&  (lastVisibleItem >= totalItemCount);
                if(!onLoading && arrivedBottom){
                   // Logger.i("do loading more");
                    onLoading = true;
                    loadNextPage();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                super.onScrollStateChanged(view, scrollState);
            }
        });

        mGridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int numColumns = 2;
                final int columnWidth = ( mGridView.getWidth()/numColumns ) - mGridItemSpace;
                mAdapter.setItemWidth(columnWidth);
                mGridView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                List<Image> imgs = mAdapter.getData();
                if(imgs != null && imgs.size()>0){
                    for(Image img : imgs){
                        if(!urls.contains(img.getImageUrl()))
                            urls.add(img.getImageUrl());
                    }
                }

                Intent intent = new Intent(getActivity(), ImageGallery.class);
                intent.putStringArrayListExtra("URLS", urls);
                intent.putExtra("POSITION", i);
                startActivity(intent);
            }
        });

        //loadImageData();

        //System.out.println(mGridView.getColumnWidth());

        getLoaderManager().initLoader(0, null, this);

    }

    private void loadImageData(final String category, int page){
        Logger.d("do here loadImageData");
        Logger.d(Thread.currentThread().getName());
        OkHttpClient client = new OkHttpClient();
        String url = new UrlBuilder(BaiduApi.Image.HOST)
                .add("col", category)
                .add("tag", "全部")
                .add("sort", sort)
                .add("pn", String.valueOf( (page-1)*PAGE_SIZE ))
                .add("rn", String.valueOf(PAGE_SIZE))
                .add("p", "channel")
                .add("from", "1")
                .build();

        Request req = new Request.Builder()
                .url(url)
                .build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
                mHandler.sendEmptyMessage(1);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                if(mCurrentPage == 1) {
                    // clear db
                    getActivity().getContentResolver().delete(
                            BaiduData.Images.CONTENT_URI,
                            BaiduData.Images.COLUMN_TAG +" = ? and "+BaiduData.Images.COLUMN_SORT+"= ?",
                            new String[]{category, sort});
                    // insert
                    ContentValues values = new ContentValues();
                    values.put(BaiduData.Images.COLUMN_CONTENT, json);
                    values.put(BaiduData.Images.COLUMN_TAG, category);
                    values.put(BaiduData.Images.COLUMN_SORT, sort);
                    getActivity().getContentResolver().insert(BaiduData.Images.CONTENT_URI, values);
                }
                Message msg = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data", json);
                msg.setData(bundle);
                msg.arg1 = 0;
                mHandler.sendMessage(msg);
            }
        });
    }

    private void loadFirstPage(){
        mCurrentPage = 1;
        loadImageData(category, 1);
    }

    private void loadNextPage(){
        loadImageData(category, ++mCurrentPage);
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
            BaiduData.Images.COLUMN_TAG,
            BaiduData.Images.COLUMN_CACHE_DATE,
            BaiduData.Images.COLUMN_CONTENT
        };
        //System.out.println("1:"+BaiduData.Images.CONTENT_URI);
        CursorLoader loader = new CursorLoader(
                getActivity(),
                BaiduData.Images.CONTENT_URI,
                projection,
                BaiduData.Images.COLUMN_TAG +" = ? and "+BaiduData.Images.COLUMN_SORT+"= ?",
                new String[]{category, sort},
                null);
        return loader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor c) {
        if(c != null){
            if(c.getCount() == 0){
                loadFirstPage();
                System.out.println("no cache in database");
            }else{
                System.out.println("get cache from database");
                c.moveToFirst();
                String json = c.getString(c.getColumnIndex(BaiduData.Images.COLUMN_CONTENT));
                Gson gson = new Gson();
                ImageData imageData = gson.fromJson(json, ImageData.class);
                final List<Image> images =  imageData.getImgs();
                mAdapter.setData(images);
            }
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {
        mAdapter.clearData();
    }

    @Override
    public void onRefreshStarted(View view) {
        loadFirstPage();
    }
}
