package id.co.rsnasionaldiponegoro.epublic.Views.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Views.Fragment.FragmentAccount;
import id.co.rsnasionaldiponegoro.epublic.Views.Fragment.FragmentHome;
import id.co.rsnasionaldiponegoro.epublic.Views.Fragment.FragmentInbox;
import id.co.rsnasionaldiponegoro.epublic.Views.Fragment.FragmentKesehatanBunda;

/**
 * Created by xsanz on 9/18/2018.
 */

public class AdapterMenu {
    private int temp = 1;
    private FragmentManager fragmentManager;
    private Context context;
    private BottomNavigationViewEx mBottomNavigationViewEx;

    public AdapterMenu(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    public void setmBottomNavigationViewEx(BottomNavigationViewEx mBottomNavigationViewEx) {
        this.mBottomNavigationViewEx = mBottomNavigationViewEx;
    }

    private void displayView(int position) {
        Fragment fragment;
        selectedBottomNav(position);

        switch (position) {
            case 0:
                fragment = new FragmentHome();
                break;
            case 1:
                fragment = new FragmentKesehatanBunda();
                break;
            case 2:
                fragment = new FragmentInbox();
                break;
            case 3:
                fragment = new FragmentAccount();
                break;
            default:
                fragment = null;
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

    public BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.tabHome:
                    displayView(0);
                    return true;
                case R.id.tabKesBunda:
                    displayView(1);
                    return true;
                case R.id.tabInbox:
                    displayView(2);
                    return true;
                case R.id.tabAccount:
                    displayView(3);
                    return true;
            }
            return false;
        }
    };

    @SuppressLint("NewApi")
    private void selectedBottomNav(int position) {
        if (temp != position) {
            mBottomNavigationViewEx.setIconTintList(position, ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimary)));
            mBottomNavigationViewEx.setTextTintList(position, ColorStateList.valueOf(context.getResources().getColor(R.color.colorPrimary)));
            mBottomNavigationViewEx.setIconTintList(temp, ColorStateList.valueOf(Color.GRAY));
            mBottomNavigationViewEx.setTextTintList(temp, ColorStateList.valueOf(Color.GRAY));
            temp = position;
        }
    }

}
