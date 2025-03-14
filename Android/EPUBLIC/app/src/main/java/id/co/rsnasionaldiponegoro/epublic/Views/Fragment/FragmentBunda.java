package id.co.rsnasionaldiponegoro.epublic.Views.Fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Staff;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.History;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.UnitStaff;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHistory;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestUnitStaff;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterFasilitas;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterStaffDokter;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterUnitStaff;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsArtikel;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsBunda;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsHistory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBunda extends Fragment implements ViewsBunda,ViewsHistory,ViewsArtikel {
    private View view;
    private String type;
    private LinearLayout layout_history;
    private LinearLayout layout_recycleview;
    private LinearLayout search_layout;
    private TextView text_history_desc;
    private TextView text_history_visi;
    private TextView text_history_misi;
    private TextView text_history_moto;
    private RecyclerView recyclerView;
    private DataInfo dataInfo;
    private ArrayList<Artikel> arrayListArtikel = new ArrayList<>();
    private ArrayList<Staff> arrayListStaff = new ArrayList<>();
    private ArrayList<UnitStaff> arrayListUnitStaff = new ArrayList<>();
    private HashMap<String, String> dataStringArtikel= new HashMap<String, String>();
    private Loading loading;
    private AdapterFasilitas adapterArtikel;
    private AdapterStaffDokter adapterStaffDokter;
    private AdapterUnitStaff adapterUnitStaff;
    private AdapterStaffDokter adapterStaffDokterSearch;
    private LinearLayout btn_clear;
    private EditText input_search;
    private HashMap<String, String> query = new HashMap<>();
    public FragmentBunda() {
        // Required empty public constructor
    }


    @SuppressLint("ValidFragment")
    public FragmentBunda(String types) {
        type = types;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bunda, container, false);
        init();
        return view;
    }

    private void init() {
        dataInfo = new DataInfo();
        recyclerView = view.findViewById(R.id.recyclerView);
        layout_recycleview = view.findViewById(R.id.layout_recycleview);
        layout_history = view.findViewById(R.id.layout_history);

        loading = new Loading(getActivity());
        text_history_desc = view.findViewById(R.id.text_history_desc);
        text_history_visi = view.findViewById(R.id.text_history_visi);
        text_history_misi = view.findViewById(R.id.text_history_misi);
        text_history_moto = view.findViewById(R.id.text_history_moto);
        btn_clear       = view.findViewById(R.id.btn_clear);
        input_search    = view.findViewById(R.id.input_search);
        search_layout    = view.findViewById(R.id.search_layout);
        if (type.equals("History")) {
            loading.show();
            layout_history.setVisibility(View.VISIBLE);
            search_layout.setVisibility(View.GONE);
            layout_recycleview.setVisibility(View.GONE);
            dataInfo.getHistory(getActivity(),this);
        } else if (type.equals("Fasilitas")) {
            search_layout.setVisibility(View.GONE);
            layout_history.setVisibility(View.GONE);
            layout_recycleview.setVisibility(View.VISIBLE);
            loading.show();
            dataStringArtikel.put("NAMA","FASILITAS");
            dataInfo.getArtikel(getActivity(), this,dataStringArtikel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterArtikel = new AdapterFasilitas(getActivity(), arrayListArtikel);
            recyclerView.setAdapter(adapterArtikel);
        }else if (type.equals("Layanan")) {
            search_layout.setVisibility(View.GONE);
            layout_history.setVisibility(View.GONE);
            layout_recycleview.setVisibility(View.VISIBLE);
            loading.show();
            dataStringArtikel.put("NAMA","LAYANAN");
            dataInfo.getArtikel(getActivity(), this,dataStringArtikel);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterArtikel = new AdapterFasilitas(getActivity(), arrayListArtikel);
            recyclerView.setAdapter(adapterArtikel);
        }else if (type.equals("Staff")) {
            layout_history.setVisibility(View.GONE);
            layout_recycleview.setVisibility(View.VISIBLE);
            loading.show();
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapterUnitStaff = new AdapterUnitStaff(getActivity(), arrayListUnitStaff);
            recyclerView.setAdapter(adapterUnitStaff);
            getUnitStaff();

        } else {
            layout_history.setVisibility(View.GONE);
            layout_recycleview.setVisibility(View.VISIBLE);
        }
    }


    private void getUnitStaff(){
        arrayListUnitStaff.clear();
        adapterUnitStaff.notifyDataSetChanged();
        query.put("idUnitStaff","all");
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestUnitStaff> observable = interfaceInfo.getUnitStaff(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestUnitStaff>() {
                    @Override
                    public void onNext(RestUnitStaff rest) {
                        if(rest!=null){
                            if (rest.getCount()>0) {
                                setStaffUnit(rest.getData());
                            } else {

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

    private void setStaffUnit(List<UnitStaff> listStaff){
        arrayListUnitStaff.addAll(listStaff);
        adapterUnitStaff.notifyDataSetChanged();
        if (arrayListStaff.size() > 0) {
//            layout_nodata.setVisibility(View.GONE);
            adapterUnitStaff.notifyDataSetChanged();
            recyclerView.setAdapter(adapterUnitStaff);
            adapterUnitStaff.notifyDataSetChanged();
        } else {
//            layout_nodata.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onSuccessHistory(RestHistory restHistory) {
        loading.hide();
        if(restHistory!=null){
            History history = restHistory.getHistorys().get(0);
            text_history_desc.setText(Html.fromHtml(history.getdESKRIPSI()));
            text_history_visi.setText(Html.fromHtml(history.getvISI()));
            text_history_misi.setText(Html.fromHtml(history.getmISI()));
            text_history_moto.setText(Html.fromHtml(history.getmOTO()));
        }
    }

    @Override
    public void onErrorHistory(RestHistory restHistory, String msg) {
        loading.hide();
    }

    @Override
    public void onSuccessArtikel(RestArtikel restArtikel) {
        loading.hide();
        arrayListArtikel.clear();
        adapterArtikel.notifyDataSetChanged();
        loading.hide();
        if (restArtikel.getData().size() > 0) {
//            layout_nodata.setVisibility(View.GONE);
            arrayListArtikel.addAll(restArtikel.getData());
            adapterArtikel.notifyDataSetChanged();
            recyclerView.setAdapter(adapterArtikel);
            adapterArtikel.notifyDataSetChanged();
        } else {
//            layout_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onErrorArtikel(RestArtikel restArtikel, String msg) {
        loading.hide();
    }
}
