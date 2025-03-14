package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.Pendaftaran;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterTMPendPoli;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendPoli;

public class ActivityTMKonsulAddPoli extends AppCompatActivity implements View.OnClickListener,ViewsPendPoli {

    private LinearLayout btn_back;
    private TextView title_bar;
    private EditText input_search;
    private LinearLayout btn_clear;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private RecyclerView recyclerView;
    private Button button_kembali;
    private HashMap<String,String> queryLanjut= new HashMap<>();
    private ArrayList<PendaftaranPoli> pendaftaranPolis = new ArrayList<>();
    //    private ArrayList<PendaftaranPoli> pendaftaranPolisSearch = new ArrayList<>();
    private AdapterTMPendPoli adapterPendPoli;
    private DataTelemedicine dataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmkonsul_add_poli);
        init();
    }

    private void init(){
        btn_back         = findViewById(R.id.btn_back);
        button_kembali         = findViewById(R.id.button_kembali);
        title_bar        = findViewById(R.id.title_bar);
        input_search        = findViewById(R.id.input_search);
        btn_clear        = findViewById(R.id.btn_clear);
        recyclerView        = findViewById(R.id.recyclerView);
        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataTelemedicine();
        title_bar.setText("Pilih Poliklinik");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


        adapterPendPoli    = new AdapterTMPendPoli(this,pendaftaranPolis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterPendPoli);
        adapterPendPoli.notifyDataSetChanged();

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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessPendPoli(RestPendaftaranPoli restPendaftaranPoli) {
        if(restPendaftaranPoli.getData()!=null){
//            tv_antrian_noavaiable.setVisibility(View.GONE);
            pendaftaranPolis.clear();
            pendaftaranPolis.addAll(restPendaftaranPoli.getData());
            recyclerView.setAdapter(adapterPendPoli);
            adapterPendPoli.notifyDataSetChanged();
        }
    }

    @Override
    public void onErrorPendPoli(String msg) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        queryLanjut.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
//        queryLanjut.put("tglPeriksa",""+session.getSessionString("tglPeriksa",""));
        dataInfo.getPendPoli(this,this,queryLanjut);


    }

    private void search(CharSequence s){
        if(s.toString().equals("") || s.toString().equals(" ")){
//            queryLanjut.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
//            queryLanjut.put("tglPeriksa",""+session.getSessionString("tglPeriksa",""));
//            dataInfo.getPendPoli(this,this,queryLanjut);
            adapterPendPoli    = new AdapterTMPendPoli(this,pendaftaranPolis);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterPendPoli);
            adapterPendPoli.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);

        }else{
            final ArrayList<PendaftaranPoli> pendaftaranPolisSearch = new ArrayList<>();
            pendaftaranPolisSearch.clear();
            adapterPendPoli    = new AdapterTMPendPoli(this,pendaftaranPolisSearch);
            for (int i = 0; i < pendaftaranPolis.size() ; i++) {
                if(pendaftaranPolis.get(i).getfMPKLINIKN().toLowerCase().contains(s.toString().toLowerCase())){
                    pendaftaranPolisSearch.add(pendaftaranPolis.get(i));
                }
            }

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterPendPoli);
            adapterPendPoli.notifyDataSetChanged();
            recyclerView.setHasFixedSize(true);

        }
    }
}
