package me.nereo.baiduimageview.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Administrator on 2014-07-07.
 */
public class MenuFragment extends ListFragment {

    private OnMenuItemClickListener mListener;

    private ArrayAdapter<String> mAdapter;

    private static final String[] datas = {"美女","美食","宠物", "植物", "汽车", "摄影"};

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnMenuItemClickListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.getClass() + "must implements MenuFragment.OnMenuItemClickListener");
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);
        setListAdapter(mAdapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        //l.setItemChecked(position, true);
        mListener.onMenuItemClick(position, datas[position]);
    }

    public static interface OnMenuItemClickListener{
        public void onMenuItemClick(int position, String category);
    }
}
