package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDokterJadwal;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityPendLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityDetailDokter extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout btn_back;
    private ImageView info_image;
    private TextView info_nama;
    private TextView info_motto;
    private TextView info_desc;
    private TextView info_jadwal;
    private TextView title_bar;
    private Button button_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dokter);
        init();
    }

    private void init() {
        Fresco.initialize(this);
        info_image = findViewById(R.id.info_image);
        info_nama = findViewById(R.id.info_nama);
        info_motto = findViewById(R.id.info_motto);
        info_desc = findViewById(R.id.info_desc);
        info_jadwal = findViewById(R.id.info_jadwal);
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        button_daftar = findViewById(R.id.button_daftar);
        button_daftar.setVisibility(View.GONE);
        title_bar.setText("Detail Dokter");

        if (getIntent().getExtras().getString("idDokter") != null) {
            loadJadwal(getIntent().getExtras().getString("idDokter"));
            button_daftar.setVisibility(View.VISIBLE);
        }
        if (getIntent().getExtras().getString("nama") != null) {
            info_nama.setText("" + getIntent().getExtras().getString("nama"));
        }
        if (getIntent().getExtras().getString("desc") != null) {
            info_desc.setText(Html.fromHtml("" + getIntent().getExtras().getString("desc")));
        }
        if (getIntent().getExtras().getString("img") != null) {

                LoadImages
                        .LoadDataImages(this
                                , ""+getIntent().getExtras().getString("img"), R.drawable.logo_main
                                , info_image
                                , null
                                , "FULL");
                info_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        List<String> listImg =new ArrayList<>();
                        listImg.clear();
                        listImg.add(""+getIntent().getExtras().getString("img"));
                        new ImageViewer.Builder(ActivityDetailDokter.this, listImg)
                                .setStartPosition(0)
                                .show();

                    }
                });

        }
//        if (getIntent().getExtras().getString("motto") != null && !getIntent().getExtras().getString("motto").toLowerCase().equals("null") ) {
//            info_motto.setText(Html.fromHtml(getIntent().getExtras().getString("motto")));
//        }
        btn_back.setOnClickListener(this);
        button_daftar.setOnClickListener(this);
    }

    private void loadJadwal(String idDokter){
        final HashMap<String, String> query = new HashMap<>();
        query.put("ID_DOKTER",idDokter);
        final String[] isi_jadwal = {""};
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestDokterJadwal> observable = interfaceInfo.getStaffDokterJadwal(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestDokterJadwal>() {
                    @Override
                    public void onNext(RestDokterJadwal rest) {
                        if(rest!=null){
                            if(rest.getCount()>0){
                                for (int i = 0; i < rest.getData().size(); i++) {
                                    if (i == 0) {
                                        isi_jadwal[0] = "\n PoliKlinik: "+rest.getData().get(i).getpOLIKLINIK()
                                                +"\n Minggu \t:\t"+rest.getData().get(i).getfMJHari01()
                                                +"\n Senin  \t:\t"+rest.getData().get(i).getfMJHari02()
                                                +"\n Selasa \t:\t"+rest.getData().get(i).getfMJHari03()
                                                +"\n Rabu   \t:\t"+rest.getData().get(i).getfMJHari04()
                                                +"\n Kamis  \t:\t"+rest.getData().get(i).getfMJHari05()
                                                +"\n Jumat  \t:\t"+rest.getData().get(i).getfMJHari06()
                                                +"\n Sabtu  \t:\t"+rest.getData().get(i).getfMJHari07()
                                                +"\n Shift      \t:\t"+rest.getData().get(i).getfMJShift();
                                    } else {
                                        isi_jadwal[0] = isi_jadwal[0]+"\n\n PoliKlinik: "+rest.getData().get(i).getpOLIKLINIK()
                                                +"\n Minggu \t:\t"+rest.getData().get(i).getfMJHari01()
                                                +"\n Senin  \t:\t"+rest.getData().get(i).getfMJHari02()
                                                +"\n Selasa \t:\t"+rest.getData().get(i).getfMJHari03()
                                                +"\n Rabu   \t:\t"+rest.getData().get(i).getfMJHari04()
                                                +"\n Kamis  \t:\t"+rest.getData().get(i).getfMJHari05()
                                                +"\n Jumat  \t:\t"+rest.getData().get(i).getfMJHari06()
                                                +"\n Sabtu  \t:\t"+rest.getData().get(i).getfMJHari07()
                                                +"\n Shift      \t:\t"+rest.getData().get(i).getfMJShift();
                                    }
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {

                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {
                        info_jadwal.setText("Jadwal Dokter: \n"+isi_jadwal[0]);
                    }
                });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.button_daftar:
                startActivity(new Intent(ActivityDetailDokter.this,ActivityPendLogin.class));
                break;
        }
    }
}
