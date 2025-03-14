package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityMain;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;

public class ActivityPendHome extends AppCompatActivity implements ViewsPendaftaran{
    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;

    private TextView pend_nama;
    private TextView pend_norm;
    private TextView pend_notelp;
    private TextView pend_tgllhr;
    private TextView pend_alamat;
    private EditText form_notelp;

    private HashMap<String,String> query= new HashMap<>();
    private Button button_lanjut;
    private Button button_keluar;
    private Boolean lanjut=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pend_home);
        init();
    }

    private void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        button_keluar        = (Button) findViewById(R.id.button_keluar);
        button_lanjut        = (Button) findViewById(R.id.button_lanjut);

        form_notelp        = (EditText) findViewById(R.id.form_notelp);
        pend_nama        = (TextView) findViewById(R.id.pend_nama);
        pend_norm        = (TextView) findViewById(R.id.pend_norm);
        pend_notelp        = (TextView) findViewById(R.id.pend_notelp);
        pend_tgllhr        = (TextView) findViewById(R.id.pend_tgllhr);
        pend_alamat        = (TextView) findViewById(R.id.pend_alamat);

        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataInfo();
        title_bar.setText("Pendaftaran");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{loading.show();}catch (Exception ex){}
                lanjut=true;
                query.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
                if(form_notelp.getText().toString().trim().length()>0){
                    query.put("notelepon",""+form_notelp.getText().toString());
                }
                dataInfo.listPendaftaran(ActivityPendHome.this,ActivityPendHome.this,query);
//                finish();
//                startActivity(new Intent(ActivityPendHome.this,ActivityPendPoli.class));
            }
        });
        button_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                session.setSessionBoolean("loginPend",false);
                startActivity(new Intent(ActivityPendHome.this,ActivityMain.class));
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();
       try{ loading.show();}catch (Exception ex){}
        query.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
        dataInfo.listPendaftaran(this,this,query);
    }

    @Override
    public void onSuccessPendaftaran(RestPendaftaranBooking restPendaftaranBooking) {
        try{loading.hide();}catch (Exception ex){}
        session.setSessionUserPend(restPendaftaranBooking.getPendaftaranLogin());

        pend_nama.setText(restPendaftaranBooking.getPendaftaranLogin().getnAMAPASIEN());
        pend_norm.setText(restPendaftaranBooking.getPendaftaranLogin().getkDPASIEN());
        pend_notelp.setText(restPendaftaranBooking.getPendaftaranLogin().gettELEPON());
        String[] parts = restPendaftaranBooking.getPendaftaranLogin().gettGLLAHIR().split(" ");
        pend_tgllhr.setText(""+parts[0]);
        pend_alamat.setText(restPendaftaranBooking.getPendaftaranLogin().getaLAMAT());
        pend_alamat.setSelected(true);

        if(lanjut){
            lanjut=false;
//            finish();
            startActivity(new Intent(ActivityPendHome.this,ActivityPendTgl.class));
        }
    }

    @Override
    public void onErrorPendaftaran(String msg) {
        try{loading.hide();}catch (Exception ex){}
        lanjut=false;
    }
}
