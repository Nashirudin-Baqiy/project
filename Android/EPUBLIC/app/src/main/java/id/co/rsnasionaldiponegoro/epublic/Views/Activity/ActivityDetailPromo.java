package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.R;

public class ActivityDetailPromo extends AppCompatActivity {
    private LinearLayout btn_back;
    private ImageView info_image;
    private TextView info_judul;
    private TextView info_tgl;
    private TextView info_desc;
    private TextView title_bar;
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_promo);
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        webView = findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(""+ BuildConfig.BASE_URL_API+"epublic/info/infoBlog?ID="+getIntent().getExtras().getString("ID"));

        Log.e("wew",BuildConfig.BASE_URL_API+"epublic/info/infoBlog?ID="+getIntent().getExtras().getString("ID"));
        if (getIntent().getExtras().getString("title") != null) {
            title_bar.setText(""+getIntent().getExtras().getString("title"));
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
