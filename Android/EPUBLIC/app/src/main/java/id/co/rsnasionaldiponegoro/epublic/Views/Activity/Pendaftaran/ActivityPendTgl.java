package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Asuransi;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterBooking;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsGetTanggal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendPoli;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;

public class ActivityPendTgl extends AppCompatActivity implements ViewsPendaftaran,View.OnClickListener,ViewsGetTanggal,ViewsPendPoli {
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
    private EditText form_tglperiksa;
    private Spinner spinner_tglperiksa;
    private ArrayAdapter<String> adapterTanggal;
    private List<String> listTanggal= new ArrayList<String>();
    private List<String> listAsuransi= new ArrayList<String>();
    private List<Asuransi> listAsuransiArray= new ArrayList<>();


    private RadioGroup radioGroup;
    private RadioButton rb_umum;
    private RadioButton rb_bpjs;
    private RadioButton rb_asuransi;
    private String jenisPasien;
    private String tglPeriksa;
    private LinearLayout layout_pilihAsuransi;

    private HashMap<String,String> query= new HashMap<>();
    private HashMap<String,String> queryLanjut= new HashMap<>();
    private Button button_lanjut;
    private Button button_kembali;

    private ArrayList<PendaftaranBooking> pendaftaranBookings = new ArrayList<>();
    private AdapterBooking adapterBooking;
    private RecyclerView recyclerView;

    private String sessionAsuransi;
    private ArrayAdapter<String> adapterPenjamin;
    private String idAsuransi;
    private Spinner spinner_penjamin;

    private LinearLayout layout_norujukan;
    private Button btnNoRujukanScan;
    private EditText noRujukan;
    private ImageView iv_contoh_scan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pend_tgl);
        init();
    }

    private void init(){
        Fresco.initialize(this);
        idAsuransi="-";

        layout_norujukan         = (LinearLayout) findViewById(R.id.layout_norujukan);
        iv_contoh_scan       = (ImageView) findViewById(R.id.iv_contoh_scan);
        btnNoRujukanScan       = (Button) findViewById(R.id.btnNoRujukanScan);
        noRujukan        = (EditText) findViewById(R.id.noRujukan);

        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);

        pend_nama        = (TextView) findViewById(R.id.pend_nama);
        pend_norm        = (TextView) findViewById(R.id.pend_norm);
        pend_tgllhr        = (TextView) findViewById(R.id.pend_tgllhr);
        pend_notelp        = (TextView) findViewById(R.id.pend_notelp);
        pend_alamat        = (TextView) findViewById(R.id.pend_alamat);

        button_lanjut        = (Button) findViewById(R.id.button_lanjut);
        button_kembali       = (Button) findViewById(R.id.button_kembali);
        layout_pilihAsuransi = (LinearLayout) findViewById(R.id.layout_pilihAsuransi);
        spinner_penjamin        = (Spinner) findViewById(R.id.spinner_penjamin);
        layout_pilihAsuransi.setVisibility(View.GONE);

        form_tglperiksa        = (EditText) findViewById(R.id.form_tglperiksa);
        spinner_tglperiksa        = (Spinner) findViewById(R.id.spinner_tglperiksa);
        recyclerView        = (RecyclerView) findViewById(R.id.recyclerView);

        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo= new DataInfo();
        //radiobutton
        radioGroup= findViewById(R.id.radioGroup);
        rb_umum= findViewById(R.id.rb_umum);
        rb_bpjs= findViewById(R.id.rb_bpjs);
        rb_asuransi= findViewById(R.id.rb_asuransi);
        layout_norujukan.setVisibility(View.GONE);
        btnNoRujukanScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new IntentIntegrator(ActivityPendTgl.this).initiateScan(); // `this` is the current Activity
            }
        });
        LoadImages.LoadDataImages(this, BuildConfig.BASE_URL+"img/contoh_scan_full.jpg",
                R.drawable.ic_image_load,iv_contoh_scan,null,"");
        iv_contoh_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> listImg =new ArrayList<>();
                listImg.clear();
                listImg.add(BuildConfig.BASE_URL+"img/contoh_scan_full.jpg");
                new ImageViewer.Builder(ActivityPendTgl.this, listImg)
                        .setStartPosition(0)
                        .show();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_umum:
                        sessionAsuransi="0";
                        idAsuransi="-";
                        jenisPasien="A";
                        session.setSessionString("jenisPasien","A");
                        layout_pilihAsuransi.setVisibility(View.GONE);
                        layout_norujukan.setVisibility(View.GONE);
                        break;
                    case R.id.rb_bpjs:
                        sessionAsuransi="0";
                        jenisPasien="B";
                        session.setSessionString("jenisPasien","B");
                        layout_pilihAsuransi.setVisibility(View.GONE);
                        layout_norujukan.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_asuransi:
                        sessionAsuransi="1";
                        jenisPasien="A";
                        session.setSessionString("jenisPasien","A");
                        layout_pilihAsuransi.setVisibility(View.VISIBLE);
                        layout_norujukan.setVisibility(View.GONE);
                        break;

                }
            }
        });
        rb_umum.setChecked(true);
        adapterBooking    = new AdapterBooking(this,pendaftaranBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterBooking);
        adapterBooking.notifyDataSetChanged();

        title_bar.setText("Pendaftaran");
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
        button_lanjut.setOnClickListener(this);
    }

    private void onSetTanggal(String tgl){
        String tglpilih[]=tgl.split(" ");
        tglPeriksa=tglpilih[1];
//        Toast.makeText(this,""+tgl,Toast.LENGTH_LONG).show();
    }

    private void onSetPenjamin(String penjamin){
        for (int i = 0; i < listAsuransiArray.size(); i++){
            if(penjamin.equals(listAsuransiArray.get(i).getnAME())){
                idAsuransi=listAsuransiArray.get(i).getcUSID();
            }
        }
    }

    @Override
    public void onSuccessPendaftaran(RestPendaftaranBooking restPendaftaranBooking) {
        try{loading.hide();}catch (Exception ex){}

        if(restPendaftaranBooking.getPendaftaranLogin()!=null){
            session.setSessionUserPend(restPendaftaranBooking.getPendaftaranLogin());

            pend_nama.setText(restPendaftaranBooking.getPendaftaranLogin().getnAMAPASIEN());
            pend_norm.setText(restPendaftaranBooking.getPendaftaranLogin().getkDPASIEN());
            pend_notelp.setText(restPendaftaranBooking.getPendaftaranLogin().gettELEPON());
            String[] parts = restPendaftaranBooking.getPendaftaranLogin().gettGLLAHIR().split(" ");
            pend_tgllhr.setText(""+parts[0]);
            pend_alamat.setText(restPendaftaranBooking.getPendaftaranLogin().getaLAMAT());
            pend_alamat.setSelected(true);
        }

        if(restPendaftaranBooking.getData()!=null){
//            tv_antrian_noavaiable.setVisibility(View.GONE);
            pendaftaranBookings.clear();
            pendaftaranBookings.addAll(restPendaftaranBooking.getData());
            recyclerView.setAdapter(adapterBooking);
            adapterBooking.notifyDataSetChanged();
        }

    }

    @Override
    public void onErrorPendaftaran(String msg) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        try{loading.show();}catch (Exception ex){}
        if(session.getSessionUserPend().getkDPASIEN().trim().equals("-")){
           try{ loading.hide();}catch (Exception ex){}

            pend_nama.setText(session.getSessionUserPend().getnAMAPASIEN());
            pend_norm.setText(session.getSessionUserPend().getkDPASIEN());
            pend_notelp.setText(session.getSessionUserPend().gettELEPON());
//            String[] parts = restPendaftaranBooking.getPendaftaranLogin().gettGLLAHIR().split(" ");
            pend_tgllhr.setText(""+session.getSessionUserPend().gettGLLAHIR());
            pend_alamat.setText(session.getSessionUserPend().getaLAMAT());
            pend_alamat.setSelected(true);
            query.put("FAPTELEPON",""+session.getSessionUser().getNOTELP().trim());
            query.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
            dataInfo.listPendaftaran(this,this,query);
        }else{
            query.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
            dataInfo.listPendaftaran(this,this,query);

        }

        dataInfo.getTanggalPend(this,this);
        dataInfo.getListAsuransi(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_lanjut:

                session.setSessionString("idAsuransi","-");

               try{ loading.show();}catch (Exception ex){}
                if(!sessionAsuransi.equals("0")) {
                    if(idAsuransi!=null && !idAsuransi.equals("-")) {
                        session.setSessionString("idAsuransi", "" + idAsuransi);
                    }
                }

                if(jenisPasien.equals("B")){
                    if(noRujukan.getText().toString().trim().length()>2){
                        queryLanjut.put("noKartu",""+noRujukan.getText());
                    }else{
                        Toast.makeText(ActivityPendTgl.this,"No Kartu BPJS tidak boleh kosong!",Toast.LENGTH_LONG).show();
                        loading.hide();
                        break;
                    }
                }else{
                    queryLanjut.put("noKartu","-");
                }

                if(!session.getSessionUserPend().getkDPASIEN().trim().equals("-")) {

                    queryLanjut.put("_kodePasien", "" + session.getSessionUserPend().getkDPASIEN());
                    queryLanjut.put("tglPeriksa", "" + tglPeriksa);
                    queryLanjut.put("jenisPendaftaran",""+session.getSessionString("jenisPendaftaran",""));
                    dataInfo.getPendPoli(this, this, queryLanjut);

                }else{
                    session.setSessionString("tglPeriksa",""+tglPeriksa);
                    session.setSessionString("_kodePasien","-");
                    queryLanjut.put("_kodePasien", "-" );
                    queryLanjut.put("tglPeriksa",""+tglPeriksa);
                    queryLanjut.put("FAPTELEPON", "" + session.getSessionUserPend().gettELEPON());
                    queryLanjut.put("jenisPendaftaran",""+session.getSessionString("jenisPendaftaran",""));
                    queryLanjut.put("FAPTELEPON", "" + session.getSessionUserPend().gettELEPON());
                    dataInfo.getPendPoli(this,this,queryLanjut);
                    //startActivity(new Intent(this,ActivityPendPoli.class));
                }

                break;
        }
    }

    @Override
    public void onLoadTanggal(RestPendaftaranTanggal restPendaftaranTanggal) {
        listTanggal.clear();
        listTanggal=restPendaftaranTanggal.getTanggal();
        adapterTanggal = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                listTanggal);
        adapterTanggal.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_tglperiksa.setAdapter(adapterTanggal);
        spinner_tglperiksa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSetTanggal(parent.getSelectedItem().toString());
//                getDokter(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onErrorTanggalLoad(String msg) {

    }

    @Override
    public void onLoadAsuransi(RestPenjaminAsuransi restPenjaminAsuransi) {
        listAsuransi.clear();

        listAsuransiArray=restPenjaminAsuransi.getData();
        for (int i = 0; i < restPenjaminAsuransi.getData().size(); i++){
            if(!listAsuransi.contains(restPenjaminAsuransi.getData().get(i).getnAME())){
                listAsuransi.add(restPenjaminAsuransi.getData().get(i).getnAME());
            }

        }
        adapterPenjamin = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                listAsuransi);
        adapterPenjamin.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_penjamin.setAdapter(adapterPenjamin);
        spinner_penjamin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onSetPenjamin(parent.getSelectedItem().toString());
//                getDokter(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onErrorLoadAsuransi(String msg) {

    }

    @Override
    public void onSuccessPendPoli(RestPendaftaranPoli restPendaftaranPoli) {
        try{loading.hide();}catch (Exception ex){}
        if(jenisPasien.equals("B")){
            session.setSessionString("noKartu",""+noRujukan.getText());
        }else{
            session.setSessionString("noKartu","-");
        }
        session.setSessionString("tglPeriksa",""+tglPeriksa);
        session.setSessionString("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
        startActivity(new Intent(this,ActivityPendPoli.class));
    }

    @Override
    public void onErrorPendPoli(String msg) {
        try{loading.hide();}catch (Exception ex){}
        dialogAlerts.CreateDialogAlertsPositive("Mohon Maaf",""+msg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Batal", Toast.LENGTH_LONG).show();
            } else {
//                loading.show();
//                dataInfo.getCekNoRujukan(ActivityPendaftaran.this,ActivityPendaftaran.this,""+result.getContents());
                noRujukan.setText(""+result.getContents());
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
