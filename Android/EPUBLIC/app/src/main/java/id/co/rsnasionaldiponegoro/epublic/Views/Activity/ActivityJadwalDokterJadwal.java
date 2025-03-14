package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceJadwal;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.JadwalDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestJadwalDokter;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterDokter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityJadwalDokterJadwal extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout btn_clear;
    private LinearLayout btn_back;
    private TextView titlebar;
    private EditText input_search;
    private RecyclerView recyclerView;
    private ArrayList<JadwalDokter> arrayList = new ArrayList<>();
    private Loading loading;
    private AdapterDokter adapterDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_dokter_jadwal);
        init();
    }


    private void init(){
        btn_back        = findViewById(R.id.btn_back);
        btn_clear       = findViewById(R.id.btn_clear);
        titlebar        = findViewById(R.id.title_bar);
        input_search    = findViewById(R.id.input_search);
        recyclerView    = findViewById(R.id.recycler_view);
        loading         = new Loading(this);

        titlebar.setText(""+getIntent().getExtras().getString("poliklinik"));
        btn_clear.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        getData();

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
        switch (v.getId()){
            case R.id.btn_back      : finish();
                break;
            case R.id.btn_clear     : input_search.setText("");
                break;
        }
    }

    private void search(CharSequence s){
        if(s.toString().equals("") || s.toString().equals(" ")){
            getData();
        }else{
            final ArrayList<JadwalDokter> search = new ArrayList<>();
            search.clear();
            adapterDokter    = new AdapterDokter(this,search);
            for (int i = 0; i < arrayList.size() ; i++) {
                if(arrayList.get(i).getDokter().toLowerCase().contains(s.toString().toLowerCase())
                        || arrayList.get(i).getHari().toLowerCase().contains(s.toString().toLowerCase())
                        || arrayList.get(i).getJam().toLowerCase().contains(s.toString().toLowerCase())){
                    search.add(arrayList.get(i));
                }
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterDokter);
            recyclerView.setHasFixedSize(true);

        }
    }

    private void getData(){
        loading.show();
        arrayList.clear();
        InterfaceJadwal interfaceJadwalDokter     = NetworkClient.getClient().create(InterfaceJadwal.class);
        Observable<RestJadwalDokter> jadwalDokter       = interfaceJadwalDokter.getJadwal(getIntent().getExtras().getString("id"));
        jadwalDokter.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestJadwalDokter>() {
            @Override
            public void onNext(RestJadwalDokter jadwalDokters) {
                arrayList.addAll(jadwalDokters.getData());
            }

            @Override
            public void onError(Throwable e) {
//                Snackbar.make(findViewById(R.id.content),"Koneksi Buruk...",Snackbar.LENGTH_SHORT).show();
                Toast.makeText(ActivityJadwalDokterJadwal.this,"Koneksi Buruk...",Toast.LENGTH_LONG).show();
                loading.hide();
            }

            @Override
            public void onComplete() {
                adapterDokter = new AdapterDokter(ActivityJadwalDokterJadwal.this,arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityJadwalDokterJadwal.this));
                recyclerView.setAdapter(adapterDokter);
                recyclerView.setNestedScrollingEnabled(true);
                recyclerView.setHasFixedSize(true);
                loading.hide();
            }
        });
    }
}
