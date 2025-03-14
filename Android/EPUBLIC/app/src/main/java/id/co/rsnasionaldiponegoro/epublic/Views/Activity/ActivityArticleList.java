package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Info;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.KritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.SuaraKonsumen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestSuaraKonsumen;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterArtikel;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterFasilitas;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterPromo;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterSuaraKonsumen;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsArtikel;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPromo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityArticleList extends AppCompatActivity implements View.OnClickListener, ViewsPromo,ViewsArtikel,ViewsKritikSaran {
    private LinearLayout btn_back;
    private TextView title_bar;
    private ImageView button_ask;
    private TextView layout_nodata;
    private RecyclerView recyclerView;
    private DataInfo dataInfo;
    private ArrayList<Info> arrayListInfo = new ArrayList<>();
    private ArrayList<Artikel> arrayListArtikel = new ArrayList<>();
    private ArrayList<KritikSaran> arrayListKritik = new ArrayList<>();
    private ArrayList<SuaraKonsumen> suaraKonsumenArrayList = new ArrayList<>();
    private HashMap<String, String> dataStringArtikel = new HashMap<String, String>();
    private HashMap<String, String> dataStringKritik= new HashMap<String, String>();
    private Loading loading;
    private AdapterPromo adapterPromo;
    private AdapterArtikel adapterArtikel;
    private AdapterKritikSaran adapterKritikSaran;
    private AdapterFasilitas adapterFasilitas;
    private AdapterSuaraKonsumen adapterSuaraKonsumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);
        init();
    }

    private void init() {
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        recyclerView = findViewById(R.id.recyclerView);
        layout_nodata = findViewById(R.id.layout_nodata);
        button_ask = findViewById(R.id.button_ask);
        dataInfo = new DataInfo();
        loading = new Loading(this);
        loading.show();
        button_ask.setVisibility(View.GONE);
        if (getIntent().getExtras().getString("title") != null) {
            title_bar.setText(getIntent().getExtras().getString("title"));
            if (getIntent().getExtras().getString("title").equals("Promo")) {

                dataInfo.getInfo(this, this);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterPromo = new AdapterPromo(this, arrayListInfo);
                recyclerView.setAdapter(adapterPromo);

            }else if (getIntent().getExtras().getString("title").equals("Layanan Unggulan")) {
                dataStringArtikel.put("NAMA","LAYANAN UNGGULAN");
                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterFasilitas = new AdapterFasilitas(this, arrayListArtikel);
                recyclerView.setAdapter(adapterFasilitas);
            }else if (getIntent().getExtras().getString("title").equals("Fasilitas")) {

                dataStringArtikel.put("NAMA","FASILITAS");
                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterFasilitas = new AdapterFasilitas(this, arrayListArtikel);
                recyclerView.setAdapter(adapterFasilitas);
            }else if (getIntent().getExtras().getString("title").equals("Layanan")) {

                dataStringArtikel.put("NAMA","LAYANAN");
                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterFasilitas = new AdapterFasilitas(this, arrayListArtikel);
                recyclerView.setAdapter(adapterFasilitas);
            }else if (getIntent().getExtras().getString("title").equals("RS Care")) {

                dataStringArtikel.put("NAMA","RS Care");
                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterArtikel = new AdapterArtikel(this, arrayListArtikel);
                recyclerView.setAdapter(adapterArtikel);
            }else if (getIntent().getExtras().getString("title").equals("Tanya RS")) {
                button_ask.setVisibility(View.VISIBLE);
                dataStringKritik.put("idUser","all");
                dataInfo.getKritikSaran(this,this,dataStringKritik);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterKritikSaran = new AdapterKritikSaran(this, arrayListKritik);
                recyclerView.setAdapter(adapterKritikSaran);
            }else if (getIntent().getExtras().getString("title").equals("Help")) {
                dataStringArtikel.put("NAMA","HELP");
                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterArtikel = new AdapterArtikel(this, arrayListArtikel);
                recyclerView.setAdapter(adapterArtikel);
            }else if (getIntent().getExtras().getString("title").equals("Suara Konsumen")) {

//                dataInfo.getArtikel(this,this,dataStringArtikel);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapterSuaraKonsumen = new AdapterSuaraKonsumen(this, suaraKonsumenArrayList);
                recyclerView.setAdapter(adapterSuaraKonsumen);
                getSuaraKonsumen();
            }else {
                if (getIntent().getExtras().getString("ID_JUDUL")!=null) {
                    dataStringArtikel.put("ID_JUDUL",getIntent().getExtras().getString("ID_JUDUL"));
                    dataInfo.getArtikelChildren(this,this,dataStringArtikel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapterArtikel = new AdapterArtikel(this, arrayListArtikel);
                    recyclerView.setAdapter(adapterArtikel);
                }

            }

        }

        btn_back.setOnClickListener(this);
        button_ask.setOnClickListener(this);
    }

    private void getSuaraKonsumen() {

        suaraKonsumenArrayList.clear();
        adapterSuaraKonsumen.notifyDataSetChanged();
        try{
            InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
            Observable<RestSuaraKonsumen> bannerObservable = interfaceInfo.getSuaraKonsumen();
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestSuaraKonsumen>() {
                @Override
                public void onNext(RestSuaraKonsumen restVersion) {
                    try{loading.hide();}catch (Exception err){}
                    if(restVersion.getSuccess()){
                            suaraKonsumenArrayList.addAll(restVersion.getData());
                            adapterSuaraKonsumen.notifyDataSetChanged();
                            recyclerView.setAdapter(adapterSuaraKonsumen);
                            adapterSuaraKonsumen.notifyDataSetChanged();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    try{loading.hide();}catch (Exception err){}
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
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.button_ask:
                startActivity(new Intent(ActivityArticleList.this,ActivityKritikSaranAdd.class));
                break;
        }
    }

    @Override
    public void onSuccessPromo(RestInfo restInfo) {
        loading.hide();
        if(restInfo!=null){
            arrayListInfo.clear();
            adapterPromo.notifyDataSetChanged();
            loading.hide();
            if (restInfo.getData().size() > 0) {
                layout_nodata.setVisibility(View.GONE);
                arrayListInfo.addAll(restInfo.getData());
                adapterPromo.notifyDataSetChanged();
                recyclerView.setAdapter(adapterPromo);
                adapterPromo.notifyDataSetChanged();
            } else {
                layout_nodata.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onErrorPromo(RestInfo restInfo, String msg) {
        loading.hide();
        layout_nodata.setVisibility(View.VISIBLE);
//        Test.ToastKoneksi(this);
    }

    @Override
    public void onSuccessArtikel(RestArtikel restArtikel) {
        loading.hide();
        if(!getIntent().getExtras().getString("title").equals("Fasilitas") && !getIntent().getExtras().getString("title").equals("Layanan Unggulan") ){
            if(restArtikel!=null){
                arrayListArtikel.clear();
                adapterArtikel.notifyDataSetChanged();
                loading.hide();
                if (restArtikel.getData().size() > 0) {
                    layout_nodata.setVisibility(View.GONE);
                    arrayListArtikel.addAll(restArtikel.getData());
                    adapterArtikel.notifyDataSetChanged();
                    recyclerView.setAdapter(adapterArtikel);
                    adapterArtikel.notifyDataSetChanged();
                } else {
                    layout_nodata.setVisibility(View.VISIBLE);
                }
            }
        }else{
            arrayListArtikel.clear();
            adapterFasilitas.notifyDataSetChanged();
            loading.hide();
            if (restArtikel.getData().size() > 0) {
//            layout_nodata.setVisibility(View.GONE);
                arrayListArtikel.addAll(restArtikel.getData());
                adapterFasilitas.notifyDataSetChanged();
                recyclerView.setAdapter(adapterFasilitas);
                adapterFasilitas.notifyDataSetChanged();
            } else {
//            layout_nodata.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onErrorArtikel(RestArtikel restArtikel, String msg) {
        loading.hide();
        layout_nodata.setVisibility(View.VISIBLE);
//        Test.ToastKoneksi(this);
    }

    @Override
    public void onSuccessLoadKritik(RestKritikSaran restKritikSaran) {
        loading.hide();
        if(restKritikSaran!=null){
            arrayListKritik.clear();
            adapterKritikSaran.notifyDataSetChanged();
            if (restKritikSaran.getData().size() > 0) {
                layout_nodata.setVisibility(View.GONE);
                arrayListKritik.addAll(restKritikSaran.getData());
                adapterKritikSaran.notifyDataSetChanged();
                recyclerView.setAdapter(adapterKritikSaran);
                adapterKritikSaran.notifyDataSetChanged();
            } else {
                layout_nodata.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onErrorLoadKritik(String msg) {
        loading.hide();
        layout_nodata.setVisibility(View.VISIBLE);
//        Test.ToastKoneksi(this);
    }
}
