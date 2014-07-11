package me.nereo.baiduimageview.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import me.nereo.baiduimageview.R;
import me.nereo.baiduimageview.ui.fragment.ImageGridFragment;
import me.nereo.baiduimageview.ui.fragment.MenuFragment;


public class MainActivity extends Activity implements MenuFragment.OnMenuItemClickListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private String sort = "0";
    private String mCurCategory = "美女";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.drawer_open,
                R.string.drawer_close
        ){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);

        SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(getActionBar().getThemedContext(), R.array.sort_list, android.R.layout.simple_spinner_dropdown_item);
        ActionBar.OnNavigationListener mNavigationCallback = new ActionBar.OnNavigationListener() {
            @Override
            public boolean onNavigationItemSelected(int i, long l) {
                sort = String.valueOf(i);
                setCategory(mCurCategory);
                return false;
            }
        };

        final ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setDisplayShowTitleEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        mActionBar.setListNavigationCallbacks(mSpinnerAdapter, mNavigationCallback);

        if(savedInstanceState == null){
            getFragmentManager().beginTransaction()
                    .add(R.id.menu, new MenuFragment())
                    .commit();
        }

        setCategory(mCurCategory);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if(mDrawerToggle.onOptionsItemSelected(item)){
            return  true;
        }

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMenuItemClick(int position, String category) {
        setCategory(category);
        mDrawerLayout.closeDrawers();
    }

    private void setCategory(String category){
        mCurCategory = category;
        Bundle bundle = new Bundle();
        bundle.putString("TAG", category);
        bundle.putString("SORT", sort);
        getFragmentManager().beginTransaction().replace(R.id.container, Fragment.instantiate(this, ImageGridFragment.class.getName(), bundle)).commit();
    }
}
