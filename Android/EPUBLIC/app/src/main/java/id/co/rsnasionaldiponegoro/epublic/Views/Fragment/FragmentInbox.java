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
import id.co.rsnasionaldiponegoro.epublic.Model.Models.KritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKritikSaran;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentInbox extends Fragment implements ViewsKritikSaran {
    private TextView title_bar;
    private TextView layout_nodata;
    private RecyclerView recyclerView;
    private DataInfo dataInfo;
    private ArrayList<KritikSaran> arrayListKritik = new ArrayList<>();
    private HashMap<String, String> dataStringKritik= new HashMap<String, String>();
    private Loading loading;
    private AdapterKritikSaran adapterKritikSaran;
    private View view;
    private Session session;
    public FragmentInbox() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_inbox, container, false);
        init();
        return view;
    }

    private void init(){
        recyclerView = view.findViewById(R.id.recyclerView);
        layout_nodata = view.findViewById(R.id.layout_nodata);
        title_bar = view.findViewById(R.id.title_bar);
        dataInfo = new DataInfo();
        loading = new Loading(getActivity());
        session = new Session(getActivity());
        loading.show();

        dataStringKritik.put("idUser",""+session.getSessionUser().getIDUSERS());
        dataInfo.getKritikSaran(getActivity(),this,dataStringKritik);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapterKritikSaran = new AdapterKritikSaran(getActivity(), arrayListKritik);
        recyclerView.setAdapter(adapterKritikSaran);
        title_bar.setText("Inbox");
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
    }


}
