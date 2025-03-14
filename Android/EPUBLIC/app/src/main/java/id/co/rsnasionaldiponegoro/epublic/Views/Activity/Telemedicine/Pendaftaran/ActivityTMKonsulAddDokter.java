package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.Pendaftaran;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPoints;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.ActivityTMDetailBooking;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterTMPendDokter;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDokter;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendSend;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityTMKonsulAddDokter extends AppCompatActivity implements ViewsPendDokter,ViewsPendSend {
    private LinearLayout btn_back;
    private TextView title_bar;
    private TextView tv_poli;
    private TextView tv_nodata;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private RecyclerView recyclerView;
    private Button button_kembali;
    private AlertDialog.Builder noticebuilder;
    private HashMap<String,String> queryLanjut= new HashMap<>();
    private HashMap<String,String> querySend= new HashMap<>();
    private ArrayList<PendaftaranDokter> arrayList = new ArrayList<>();
    private AdapterTMPendDokter adapterPendDokter;
    private DataTelemedicine dataInfo;
    private String dokter,jam,poli,shift;

    private EditText input_search;
    private LinearLayout btn_clear;
    private LinearLayout layout_pilihDokter;
    private ScrollView layout_Alasan;
    private Button button_simpan;
    private Button button_kembali2;
    private TextView txt_hello;
    private TextView txt_TMpoints;
    private EditText form_nama_value;
    private EditText form_desc_value;
    private EditText form_poli_value;
    private EditText form_dokter_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmkonsul_add_dokter);
        init();
    }

    private void init(){
        btn_back         = findViewById(R.id.btn_back);
        input_search        = findViewById(R.id.input_search);
        btn_clear        = findViewById(R.id.btn_clear);
        title_bar        = findViewById(R.id.title_bar);
        button_kembali        = findViewById(R.id.button_kembali);
        tv_poli        = findViewById(R.id.tv_poli);
        tv_nodata        = findViewById(R.id.tv_nodata);
        loading          = new Loading(this);
        recyclerView        = findViewById(R.id.recyclerView);

        layout_pilihDokter        = findViewById(R.id.layout_pilihDokter);
        layout_Alasan        = findViewById(R.id.layout_Alasan);
        button_simpan        = findViewById(R.id.button_simpan);
        txt_hello        = findViewById(R.id.txt_hello);
        txt_TMpoints        = findViewById(R.id.txt_TMpoints);
        form_nama_value        = findViewById(R.id.form_nama_value);
        form_desc_value        = findViewById(R.id.form_desc_value);
        form_poli_value        = findViewById(R.id.form_poli_value);
        form_dokter_value        = findViewById(R.id.form_dokter_value);
        button_kembali2        = findViewById(R.id.button_kembali2);

        layout_pilihDokter.setVisibility(View.VISIBLE);
        layout_Alasan.setVisibility(View.GONE);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataTelemedicine();
        title_bar.setText("Pilih Dokter");
        tv_poli.setText(""+session.getSessionString("poliPeriksa",""));
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        adapterPendDokter    = new AdapterTMPendDokter(this,arrayList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterPendDokter);
        adapterPendDokter.notifyDataSetChanged();
        noticebuilder = new AlertDialog.Builder(this);
        button_kembali2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_pilihDokter.setVisibility(View.VISIBLE);
                layout_Alasan.setVisibility(View.GONE);
            }
        });
        button_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                noticebuilder.setTitle("Verifikasi")
                        .setCancelable(false)
                        .setMessage("Anda Yakin Melakukan Pendaftaran Telemedicine pada:"
//                                +"\n Tanggal : "+session.getSessionString("tglPeriksa","")
                                +"\n Pukul : "+jam
                                +"\n Poli : "+session.getSessionString("poliPeriksa","")
                                +"\n Dokter : "+dokter)
                        .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                sendPend();


                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert11 = noticebuilder.create();
                alert11.show();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_search.setText("");
            }
        });
        input_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                search(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void sendPend(){
//        querySend.put("_tglPeriksa",""+session.getSessionString("tglPeriksa",""));
        querySend.put("dokter",""+dokter);
        querySend.put("jam",""+jam);
        querySend.put("jenispasien",""+session.getSessionString("jenisPasien",""));
        querySend.put("idAsuransi",""+session.getSessionString("idAsuransi",""));

        if(session.getSessionString("jenisPasien","").trim().equals("B")){
            querySend.put("noRujukan",""+session.getSessionString("noRujukan",""));
        }
        querySend.put("shift",""+shift);
        querySend.put("_poli",""+session.getSessionString("poliPeriksa",""));
        querySend.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
        querySend.put("judulKeluhan",""+form_nama_value.getText().toString());
        querySend.put("judulKeluhanKet",""+form_desc_value.getText().toString());
        querySend.put("FAPTELEPON",""+session.getSessionUser().getNOTELP());
        querySend.put("TIC_ID",""+session.getSessionString("TIC_ID","0"));
        dataInfo.sendPendOnline(this,this,querySend);
        loading.show();
    }


    private void search(CharSequence s){
        if(s.toString().equals("") || s.toString().equals(" ")){
//            queryLanjut.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
//            queryLanjut.put("tglPeriksa",""+session.getSessionString("tglPeriksa",""));
//            dataInfo.getPendPoli(this,this,queryLanjut);
            adapterPendDokter    = new AdapterTMPendDokter(this,arrayList,this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterPendDokter);
            adapterPendDokter.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);

        }else{
            final ArrayList<PendaftaranDokter> arrayListS = new ArrayList<>();
            arrayListS.clear();
            adapterPendDokter    = new AdapterTMPendDokter(this,arrayListS,this);
            for (int i = 0; i < arrayList.size() ; i++) {
                if(arrayList.get(i).getfMDDOKTERN().toLowerCase().contains(s.toString().toLowerCase())){
                    arrayListS.add(arrayList.get(i));
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterPendDokter);
            adapterPendDokter.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPoints();
        queryLanjut.put("tglPeriksa",""+session.getSessionString("tglPeriksa",""));
        queryLanjut.put("poli",""+session.getSessionString("poliPeriksa",""));
        dataInfo.getPendDokter(this,this,queryLanjut);
    }

    @Override
    public void onSuccessPendDokter(RestPendaftaranDokter restPendaftaranDokter) {
        if(restPendaftaranDokter.getData()!=null){
            if(restPendaftaranDokter.getData().size()>0){
                tv_nodata.setVisibility(View.GONE);
                arrayList.clear();
                arrayList.addAll(restPendaftaranDokter.getData());
                recyclerView.setAdapter(adapterPendDokter);
                adapterPendDokter.notifyDataSetChanged();
            }else{
                tv_nodata.setText("Kuota Habis");
                tv_nodata.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onErrorPendDokter(String msg) {
        tv_nodata.setText(""+msg);
        tv_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProsesSend(String dokters, String jams, String shifts) {
        dokter=dokters;
        jam=jams;
        shift=shifts;
        layout_Alasan.setVisibility(View.VISIBLE);
        layout_pilihDokter.setVisibility(View.GONE);
        form_poli_value.setText(""+session.getSessionString("poliPeriksa",""));
        form_dokter_value.setText(""+dokter);

    }

    @Override
    public void onSuccessPendSend(Rest rest) {
        loading.hide();
        session.setSessionBoolean("backDaftar",true);
        Intent intent = new Intent(this,ActivityTMDetailBooking.class);
        intent.putExtra("notrans",""+rest.getMessage());
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);

    }

    @Override
    public void onErrorPendSend(String msg) {
        loading.hide();
        dialogAlerts.CreateDialogAlertsPositive("",msg);
    }

    @Override
    public void onKuotaHabis() {
        loading.hide();
        dialogAlerts.CreateDialogAlertsPositive("","Maaf Dokter yg anda pilih kuota habis");
    }

    private void getPoints() {
        try{
            InterfaceTelemedicine interfaceInfo = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
            Observable<RestPoints> bannerObservable = interfaceInfo.getPoints(session.getSessionUser().getNOTELP());
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<RestPoints>() {
                        @Override
                        public void onNext(RestPoints restVersion) {
                            if(restVersion.getSuccess()){
                                txt_TMpoints.setText("PointsKu :"+restVersion.getpOINTS()+" Points");
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            txt_TMpoints.setText("0 Points");
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }catch (Exception ex){
            Test.look(ex.getMessage());
//            checkUpdate();
        }
    }
}
