package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.AntrianPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianPoli;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.AutoFitGridLayoutManager;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterAntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterAntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsAntrian;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityAntrian extends AppCompatActivity implements ViewsAntrian{
    private LinearLayout btn_back;
    private LinearLayout btn_back_cs;
    private LinearLayout btn_back_poli;
    private LinearLayout button_antrics;
    private LinearLayout button_antripoli;
    private LinearLayout button_antriapotik;
    private LinearLayout btn_back_apotik;
    private LinearLayout layout_antrian_menu;
    private LinearLayout layout_antrian_cs;
    private LinearLayout layout_antrian_poli;
    private LinearLayout layout_antrian_apotik;
    private TextView title_bar;

    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;

    private ArrayList<AntrianPoli> spaceprobeList = new ArrayList<>();
    private String[] spaceProbeHeaders={"No","Dokter","Antrian","Jm Antri","Terlayani"};
    private TableView<String[]> tableView;
    private String[][] spaceProbes;
    private TextView start_date;
    private TextView end_date;
    private TextView tv_antrian_noavaiable;
    private TextView tv_antriancs_noavaiable;
    private EditText search;
    private LinearLayout btn_search;
    private LinearLayout btn_clear;
    private RecyclerView rv_antrianCS;
    private RecyclerView rv_antrian;
    private Loading loading;
    private AdapterAntrianApotik adapterAntrianApotik;
    private ArrayList<AntrianApotik> antrianApotiks = new ArrayList<>();

    private AdapterAntrianCS adapterAntrianCS;
    private ArrayList<AntrianCS> antrianCS = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);
        init();
    }

    private void init(){
        loading= new Loading(this);
        btn_back = findViewById(R.id.btn_back);
//        title_bar = findViewById(R.id.title_bar);

        tv_antrian_noavaiable = findViewById(R.id.tv_antrian_noavaiable);
        tv_antriancs_noavaiable = findViewById(R.id.tv_antriancs_noavaiable);
        rv_antrian = findViewById(R.id.rv_antrian);
        rv_antrianCS = findViewById(R.id.rv_antrianCS);

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 500);
        button_antrics = findViewById(R.id.button_antrics);
        button_antripoli = findViewById(R.id.button_antripoli);
        btn_back_poli = findViewById(R.id.btn_back_poli);
        btn_back_cs = findViewById(R.id.btn_back_cs);
        layout_antrian_menu = findViewById(R.id.layout_antrian_menu);
        layout_antrian_cs = findViewById(R.id.layout_antrian_cs);
        layout_antrian_poli = findViewById(R.id.layout_antrian_poli);
        layout_antrian_apotik = findViewById(R.id.layout_antrian_apotik);
        btn_back_apotik = findViewById(R.id.btn_back_apotik);
        button_antriapotik = findViewById(R.id.button_antriapotik);

        loading.show();


        adapterAntrianApotik    = new AdapterAntrianApotik(this,antrianApotiks);
        rv_antrian.setLayoutManager(new LinearLayoutManager(this));
        rv_antrian.setAdapter(adapterAntrianApotik);
        adapterAntrianApotik.notifyDataSetChanged();

        adapterAntrianCS    = new AdapterAntrianCS(this,antrianCS);
        rv_antrianCS.setLayoutManager(new GridLayoutManager(this, 3));
        rv_antrianCS.setAdapter(adapterAntrianCS);
        adapterAntrianCS.notifyDataSetChanged();

//        title_bar.setText("Antrian");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dataInfo=new DataInfo();
        dataInfo.getAntrianCS(this,this);
        dataInfo.getAntrianApotik(this,this);

        btn_clear        = (LinearLayout) findViewById(R.id.btn_clear);
        search           = (EditText) findViewById(R.id.input_search);
        tableView = findViewById(R.id.tableView);
        tableView.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this,spaceProbeHeaders);
        headerAdapter.setTextSize(11);
        tableView.setColumnWeight(0,2);
        tableView.setColumnWeight(1,4);
        tableView.setColumnWeight(2,3);
        tableView.setColumnWeight(3,2);
        tableView.setColumnWeight(4,2);
        tableView.setHeaderAdapter(headerAdapter);
        tableView.setColumnCount(5);
        getAntrianPoli();
        tableView.addDataClickListener(new TableDataClickListener() {
            @Override
            public void onDataClicked(int rowIndex, Object clickedData) {
                Toast.makeText(ActivityAntrian.this, ((String[])clickedData)[1]+" "+((String[])clickedData)[2]+" "+((String[])clickedData)[3]+" "+
                        ((String[])clickedData)[4]/*+" "+((String[])clickedData)[5]*/, Toast.LENGTH_SHORT).show();
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<AntrianPoli> lisSearch = getSearch(s);
                spaceProbes= new String[lisSearch.size()+1][13];
                Integer sum=0;
                Integer sumbpjs=0;
                for (int i=0;i<lisSearch.size();i++) {
                    AntrianPoli antrianPoli=lisSearch.get(i);
                    spaceProbes[i][0]=Integer.toString(i+1);
                    spaceProbes[i][1]= antrianPoli.getDokterN();
                    spaceProbes[i][2]= antrianPoli.getNo();
                    spaceProbes[i][3]= antrianPoli.getJmAntrian();
                    spaceProbes[i][4]= antrianPoli.getJmLayani();

                }

                SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(ActivityAntrian.this, spaceProbes);
                dataAdapter.setTextSize(11);
                tableView.setDataAdapter(dataAdapter);
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        button_antrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_menu.setVisibility(View.GONE);
                layout_antrian_cs.setVisibility(View.VISIBLE);
                layout_antrian_poli.setVisibility(View.GONE);
                layout_antrian_apotik.setVisibility(View.GONE);
            }
        });

        button_antripoli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_menu.setVisibility(View.GONE);
                layout_antrian_cs.setVisibility(View.GONE);
                layout_antrian_apotik.setVisibility(View.GONE);
                layout_antrian_poli.setVisibility(View.VISIBLE);
            }
        });

        btn_back_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_menu.setVisibility(View.VISIBLE);
                layout_antrian_cs.setVisibility(View.GONE);
                layout_antrian_poli.setVisibility(View.GONE);
                layout_antrian_apotik.setVisibility(View.GONE);
            }
        });

        btn_back_poli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_menu.setVisibility(View.VISIBLE);
                layout_antrian_cs.setVisibility(View.GONE);
                layout_antrian_poli.setVisibility(View.GONE);
                layout_antrian_apotik.setVisibility(View.GONE);
            }
        });

        btn_back_apotik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_apotik.setVisibility(View.GONE);
                layout_antrian_menu.setVisibility(View.VISIBLE);
                layout_antrian_cs.setVisibility(View.GONE);
                layout_antrian_poli.setVisibility(View.GONE);
            }
        });
        button_antriapotik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_antrian_apotik.setVisibility(View.VISIBLE);
                layout_antrian_menu.setVisibility(View.GONE);
                layout_antrian_cs.setVisibility(View.GONE);
                layout_antrian_poli.setVisibility(View.GONE);
            }
        });
//        dataInfo.getAntrianPoli(this,this);

    }

    private void getAntrianPoli(){
        spaceprobeList.clear();
        InterfaceInfo interfaceInfo             = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestAntrianPoli> observable = interfaceInfo.getAntrianPoli();
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestAntrianPoli>() {
                    @Override
                    public void onNext(RestAntrianPoli restAntrianPoli) {
                        spaceprobeList.addAll(restAntrianPoli.getAntrianPoli());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ActivityAntrian.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Snackbar.make(findViewById(R.id.content),"Koneksi Buruk...",Snackbar.LENGTH_SHORT).show();
                        loading.hide();
                    }

                    @Override
                    public void onComplete() {
                        try {
//                        sorting(spaceprobeList);
                            ArrayList<AntrianPoli> listAntrianPoli = getListKelas();
                            Integer sum = 0;
                            Integer sumbpjs = 0;
                            spaceProbes = new String[listAntrianPoli.size() + 1][13];
                            for (int i = 0; i < listAntrianPoli.size(); i++) {
                                AntrianPoli antrianPoli = listAntrianPoli.get(i);
                                spaceProbes[i][0] = Integer.toString(i + 1);
                                spaceProbes[i][1] = antrianPoli.getDokterN();
                                spaceProbes[i][2] = antrianPoli.getNo();
                                spaceProbes[i][3] = antrianPoli.getJmAntrian();
                                spaceProbes[i][4] = antrianPoli.getJmLayani();

                            }

                            SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(ActivityAntrian.this, spaceProbes);
                            dataAdapter.setTextSize(11);
                            tableView.setDataAdapter(dataAdapter);
                            loading.hide();
                        }catch (Exception ex){}
                    }
                });
    }

    private ArrayList<AntrianPoli> getSearch(CharSequence search){
        ArrayList<AntrianPoli> data = getListKelas();
        if(search.equals("") || search.equals(" ")){
            return data;
        }else {
            ArrayList<AntrianPoli> listSearch = new ArrayList<>();
            for (int i = 0; i < data.size() ; i++) {
                try {
                if(i<data.size()){
                    if(data.get(i).getDokterN().toLowerCase().contains(search.toString().toLowerCase())){
                        listSearch.add(data.get(i));
                    }
                }
                }catch (Exception ex){}
            }
            return listSearch;
        }
    }

    private ArrayList<AntrianPoli> getListKelas(){
        ArrayList<AntrianPoli> listKelas = new ArrayList<>();
        for (int i = 0; i < spaceprobeList.size() ; i++) {
            if(i<spaceprobeList.size()-1){
               try{ if(!spaceprobeList.get(i).getDokterN().equals(spaceprobeList.get(i+1).getDokterN())){
                   listKelas.add(spaceprobeList.get(i));
               }}catch (Exception ex){
                   Log.e("error",""+ex.getMessage());
               }
            }else{
                listKelas.add(spaceprobeList.get(i));
            }
        }
        return listKelas;
    }
    @Override
    public void onSuccessAntrianCS(RestAntrianCS restAntrianCS) {
        if(restAntrianCS!=null){
            try {
                if(restAntrianCS.getData().size()!=0){
                    tv_antriancs_noavaiable.setVisibility(View.GONE);
                    antrianCS.clear();
                    antrianCS.addAll(restAntrianCS.getData());
                    rv_antrianCS.setAdapter(adapterAntrianCS);
                    adapterAntrianCS.notifyDataSetChanged();
                }else{
                    tv_antriancs_noavaiable.setVisibility(View.VISIBLE);
                }
            }catch (Exception ex){
                Log.e("error",""+ex.getMessage());
            }
        }
    }

    @Override
    public void onSuccessAntrianPoli(RestAntrianPoli restAntrianPoli) {
        if(restAntrianPoli.getSuccess()){
            if(restAntrianPoli.getAntrianPoli().size()>0){






            }
        }
    }

    @Override
    public void onSuccessAntrianApotik(RestAntrianApotik restAntrianApotik) {
        if(restAntrianApotik!=null){
            if(restAntrianApotik.getData().size()!=0){
                tv_antrian_noavaiable.setVisibility(View.GONE);
                antrianApotiks.clear();
                antrianApotiks.addAll(restAntrianApotik.getData());
                rv_antrian.setAdapter(adapterAntrianApotik);
                adapterAntrianApotik.notifyDataSetChanged();
            }else{
                tv_antrian_noavaiable.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onErrorAntrianCS(String msg) {
        tv_antrian_noavaiable.setVisibility(View.VISIBLE);
    }

    @Override
    public void onErrorAntrianPoli(String msg) {

    }

    @Override
    public void onErrorAntrianApotik(String msg) {
        tv_antrian_noavaiable.setVisibility(View.VISIBLE);
    }
}
