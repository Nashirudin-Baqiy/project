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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Staff;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.UnitStaff;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestStaff;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterStaffDokter;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityStaffDokterList extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout btn_back;
    private TextView title_bar;
    private TextView layout_nodata;
    private RecyclerView recyclerView;
    private DataInfo dataInfo;
    private ArrayList<Staff> arrayListStaff = new ArrayList<>();
    private ArrayList<UnitStaff> arrayListUnitStaff = new ArrayList<>();
    private Loading loading;
    private AdapterStaffDokter adapterStaffDokter;
    private AdapterStaffDokter adapterStaffDokterSearch;
    private LinearLayout btn_clear;
    private LinearLayout search_layout;
    private EditText input_search;
    private HashMap<String, String> query = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_dokter_list);
        init();
    }

    private void init(){
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        layout_nodata = findViewById(R.id.layout_nodata);
        recyclerView = findViewById(R.id.recyclerView);
        loading = new Loading(this);
        btn_back.setOnClickListener(this);
        if(getIntent().getExtras().getString("title")!=null){
            title_bar.setText(""+getIntent().getExtras().getString("title"));
        }
        btn_clear       = findViewById(R.id.btn_clear);
        input_search    = findViewById(R.id.input_search);
        search_layout    = findViewById(R.id.search_layout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapterStaffDokter = new AdapterStaffDokter(this, arrayListStaff);
        recyclerView.setAdapter(adapterStaffDokter);
        loading.show();
        if(getIntent().getExtras().getString("title").equals("DOKTER")){

            getDataStaffDokter();

        }else{
            if(getIntent().getExtras().getString("id")!=null){

                getDataStaff();
            }else{
                getDataDokterPoli();
            }

        }

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    private void search(CharSequence s){
        final ArrayList<Staff> filteredList = new ArrayList<>();
        for (int x = 0; x < arrayListStaff.size(); x++) {
            final String text = arrayListStaff.get(x).getNAMA().toLowerCase();
            if (text.contains(s)) {
                filteredList.add(arrayListStaff.get(x));
            }
        }

        adapterStaffDokter = new AdapterStaffDokter(this, filteredList);
        recyclerView.setAdapter(adapterStaffDokter);
        adapterStaffDokter.notifyDataSetChanged();

    }
    private void getDataStaff(){
        query.put("idUnitStaff",""+getIntent().getExtras().getString("id"));
        arrayListStaff.clear();
        adapterStaffDokter.notifyDataSetChanged();
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestStaff> observable = interfaceInfo.getlistStaff(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestStaff>() {
                    @Override
                    public void onNext(RestStaff rest) {
                        if(rest!=null){
                            if (rest.getCount()>0) {
                                setStaffDokter(rest.getData());
                            } else {
                                layout_nodata.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            loading.hide();
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {
                        loading.hide();

                    }
                });
    }

    private void getDataStaffDokter(){
        arrayListStaff.clear();
        adapterStaffDokter.notifyDataSetChanged();
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestDokter> observable = interfaceInfo.getStaffDokter();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestDokter>() {
                    @Override
                    public void onNext(RestDokter rest) {
                        if(rest!=null){
                            if (rest.getCount()>0) {
                                setStaffDokter(rest.getData());
                            } else {
                                layout_nodata.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            loading.hide();
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {
                        loading.hide();

                    }
                });
    }

    private void setStaffDokter(List<Staff> listStaff){
        arrayListStaff.addAll(listStaff);
        adapterStaffDokter.notifyDataSetChanged();
        if (arrayListStaff.size() > 0) {
            layout_nodata.setVisibility(View.GONE);
            adapterStaffDokter.notifyDataSetChanged();
            recyclerView.setAdapter(adapterStaffDokter);
            adapterStaffDokter.notifyDataSetChanged();
        } else {
            layout_nodata.setVisibility(View.VISIBLE);
        }
    }

    private void getDataDokterPoli(){
        query.put("kdPoli",""+getIntent().getExtras().getString("kdPoli"));
        arrayListStaff.clear();
        adapterStaffDokter.notifyDataSetChanged();
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestStaff> observable = interfaceInfo.getlistDokterPoli(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestStaff>() {
                    @Override
                    public void onNext(RestStaff rest) {
                        if(rest!=null){
                            if (rest.getData().size()>0) {
                                setStaffDokter(rest.getData());
                            } else {
                                layout_nodata.setVisibility(View.VISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            loading.hide();
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {
                        loading.hide();

                    }
                });
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
