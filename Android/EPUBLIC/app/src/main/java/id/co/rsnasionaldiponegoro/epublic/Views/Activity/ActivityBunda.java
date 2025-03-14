package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Views.Fragment.FragmentBunda;

public class ActivityBunda extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private LinearLayout btn_back;
    private TextView title_bar;
    private ImageView image_bunda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunda);
        init();
    }

    private void init() {
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        image_bunda = findViewById(R.id.image_bunda);
        title_bar.setText("Profil RS");

        //tabs
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        adapter.addFragment(new FragmentBunda("History"), "History");
//        adapter.addFragment(new FragmentBunda("Fasilitas"), "Fasilitas");
        adapter.addFragment(new FragmentBunda("Staff"), "Staff");
//        adapter.addFragment(new FragmentBunda("Layanan"), "Layanan");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        btn_back.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
