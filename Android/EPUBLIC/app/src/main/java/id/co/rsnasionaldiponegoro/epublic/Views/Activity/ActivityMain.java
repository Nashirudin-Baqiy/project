package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterMenu;

public class ActivityMain extends AppCompatActivity {
    private BottomNavigationViewEx navigation;
    private AdapterMenu adapterMenu;
    private boolean doubleclick = false;
    private Session session;

    //private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permission();
        init();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void init() {
        navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        session = new Session(this);
        adapterMenu = new AdapterMenu(this, getSupportFragmentManager());
        adapterMenu.setmBottomNavigationViewEx(navigation);

        navigation.inflateMenu(R.menu.navigation_bottom);
        navigation.setOnNavigationItemSelectedListener(adapterMenu.mOnNavigationItemSelectedListener);
        navigator();
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
//        Language.change(this,session.getSessionString("language","en"));

    }

    private void permission() {
        int RC_PERMISSION = 101;
        String[] permissionRequire = {
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, permissionRequire, RC_PERMISSION);
    }

 /*   Manifest.permission.ACCESS_FINE_LOCATION,
    Manifest.permission.ACCESS_COARSE_LOCATION,
    Manifest.permission.READ_SMS,
    Manifest.permission.READ_PHONE_STATE,*/
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (String require : permissions) {
            if ((ActivityCompat.checkSelfPermission(this, require)) != PackageManager.PERMISSION_GRANTED) {
                permission();
                break;
            }
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        switch (session.getSessionInteger("tab", 0)) {
            case 0:
                navigation.setSelectedItemId(R.id.tabHome);
                break;
            case 1:
                navigation.setSelectedItemId(R.id.tabKesBunda);
                break;
            case 2:
                navigation.setSelectedItemId(R.id.tabInbox);
                break;
            case 3:
                navigation.setSelectedItemId(R.id.tabAccount);
                break;
        }
    }

    private void navigator() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.getInt("tab") > 0 && bundle.getInt("tab") < 5) {
            switch (bundle.getInt("tab")) {
                case 1:
                    navigation.setSelectedItemId(R.id.tabKesBunda);
                    break;
                case 2:
                    navigation.setSelectedItemId(R.id.tabInbox);
                    break;
                case 3:
                    navigation.setSelectedItemId(R.id.tabAccount);
                    break;
            }
        } else {
            navigation.setSelectedItemId(R.id.tabHome);
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleclick) {
            super.onBackPressed();
            return;
        }
        this.doubleclick = true;
        Toast.makeText(this, getResources().getString(R.string.press_back_twice), Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleclick = false;
            }
        }, 2000);
    }
}
