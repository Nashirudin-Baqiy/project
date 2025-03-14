package id.co.rsnasionaldiponegoro.epublic.Views.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Artikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterArtikel;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsArtikel;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentKesehatanBunda extends Fragment implements View.OnClickListener, ViewsArtikel {
    private TextView title_bar;
    private TextView layout_nodata;
    private RecyclerView recyclerView;
    private DataInfo dataInfo;
    private ArrayList<Artikel> arrayListArtikel = new ArrayList<>();
    private HashMap<String, String> dataStringArtikel= new HashMap<String, String>();
    private Loading loading;
    private AdapterArtikel adapterArtikel;
    private View view;

    public FragmentKesehatanBunda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_kesehatan_bunda, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = view.findViewById(R.id.recyclerView);
        layout_nodata = view.findViewById(R.id.layout_nodata);
        title_bar = view.findViewById(R.id.title_bar);
        dataInfo = new DataInfo();
        loading = new Loading(getActivity());
        loading.show();
        dataStringArtikel.put("NAMA","INFO RS");
        dataInfo.getArtikel(getActivity(), this,dataStringArtikel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterArtikel = new AdapterArtikel(getActivity(), arrayListArtikel);
        recyclerView.setAdapter(adapterArtikel);
        title_bar.setText("Info RS");
    }

    @Override
    public void onClick(View view) {

    }


    @Override
    public void onSuccessArtikel(RestArtikel restArtikel) {
        loading.hide();
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

    @Override
    public void onErrorArtikel(RestArtikel restArtikel, String msg) {
        loading.hide();
        if(restArtikel!=null){
            if (restArtikel.getData().size() == 0) {

                layout_nodata.setVisibility(View.VISIBLE);
            }
        }
    }
}
