package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;

public class ActivityDetailInfo extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout btn_back;
    private ImageView info_image;
    private TextView info_judul;
    private TextView info_tgl;
    private TextView info_desc;
    private TextView title_bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        init();
    }

    private void init() {
        info_image = findViewById(R.id.info_image);
        info_judul = findViewById(R.id.info_judul);
        info_tgl = findViewById(R.id.info_tgl);
        info_desc = findViewById(R.id.info_desc);
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);



        if (getIntent().getExtras().getString("title") != null) {
            title_bar.setText(""+getIntent().getExtras().getString("title"));
            info_judul.setText("" + getIntent().getExtras().getString("title"));
        }
        if (getIntent().getExtras().getString("desc") != null) {
            info_desc.setText(Html.fromHtml("" + getIntent().getExtras().getString("desc")));
        }
        if (getIntent().getExtras().getString("img") != null) {
            LoadImages.LoadDataImages(this, getIntent().getExtras().getString("img")
                    , R.drawable.logo_main, info_image, null, "FULL");
        }
        if (getIntent().getExtras().getString("tgl") != null) {
            info_tgl.setText(""+getIntent().getExtras().getString("tgl"));
        }
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
}
