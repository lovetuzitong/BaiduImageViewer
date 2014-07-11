package me.nereo.baiduimageview.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import me.nereo.baiduimageview.R;

/**
 * Created by nereo on 2014/7/9.
 */
public class LoadingFooter {

    private LayoutInflater mInflater;

    private ProgressBar mProgressbar;
    private TextView mTextView;

    private State mState = State.LOADING;

    public LoadingFooter(Context context){
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getFooter(){
        View view = mInflater.inflate(R.layout.loading_footer, null, false);
        mProgressbar = (ProgressBar) view.findViewById(R.id.progressBar);
        mTextView = (TextView) view.findViewById(R.id.textView);
        return view;
    }

    public State getState(){
        return mState;
    }

    public void setState(State state){
        mState = state;
        switch (mState){
            case LOADING:
                if(mProgressbar.getVisibility() != View.VISIBLE)
                    mProgressbar.setVisibility(View.VISIBLE);
                if(mTextView.getVisibility() == View.VISIBLE)
                    mTextView.setVisibility(View.GONE);
                break;
            case SUCCESS:
                if(mProgressbar.getVisibility() == View.VISIBLE)
                    mProgressbar.setVisibility(View.GONE);
                break;
            case FAILED:
                if(mProgressbar.getVisibility() == View.VISIBLE)
                    mProgressbar.setVisibility(View.GONE);
                if(mTextView.getVisibility() != View.VISIBLE)
                    mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(R.string.loading_failed);
                break;
            default:
                throw new IllegalArgumentException("Unknown state : "+state);
        }
    }

    public static enum  State{
        LOADING, SUCCESS, FAILED
    }

}
