package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterBooking;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterBookingBaru;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;

public class ActivityHistoryPendaftaran extends AppCompatActivity implements View.OnClickListener,ViewsPendaftaranLogin,ViewsKeluarga,ViewsPendaftaran {
    private LinearLayout btn_back;
    private TextView title_bar;
    private TextView layout_nodata;
    private RecyclerView recycler_view;
    private Session session;
    private DialogAlerts dialogAlerts;
    private HashMap<String, String> dataString = new HashMap<String, String>();
    private HashMap<String, String> dataStringList = new HashMap<String, String>();
    private HashMap<String, String> query = new HashMap<String, String>();
//    private DatePicker datePicker = new DatePicker();

    private ArrayList<Keluarga> arrayListKeluargas = new ArrayList<>();
    private String kdNoCM;
    private Spinner spinner;
    private DataInfo dataInfo;

    private ArrayList<PendaftaranBooking> pendaftaranBookings = new ArrayList<>();
    private AdapterBooking adapterBooking;
    private AdapterBookingBaru adapterBookingBaru;
    private RecyclerView recyclerView;
    private Loading loading;
    private android.widget.LinearLayout layout_pasienlama;
    private LinearLayout layout_pasienbaru;
    private LinearLayout btn_cari;
    private EditText input_search;

    private Spinner spinner_keluarga;
    private ArrayAdapter<String> adapter_spinner_keluarga;
    private List<String> listKeluarga= new ArrayList<String>();
    private ArrayList<Keluarga> keluargaArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pendaftaran);
        init();
    }

    private void init(){
        btn_back                    = findViewById(R.id.btn_back);
        title_bar                   = findViewById(R.id.title_bar);
        layout_nodata               = findViewById(R.id.layout_nodata);
        recyclerView               = findViewById(R.id.recyclerView);
        spinner_keluarga                     = findViewById(R.id.spinner);
        layout_pasienlama            = findViewById(R.id.layout_pasienlama);
        layout_pasienbaru            = findViewById(R.id.layout_pasienbaru);
        input_search                 = findViewById(R.id.input_search );
        btn_cari                     = findViewById(R.id.btn_cari );

        session                     = new Session(this);

        dialogAlerts                = new DialogAlerts(this,this);
        dataInfo                    = new DataInfo();
        loading                     = new Loading(this);
//        recycler_view.setAdapter(adapterKeluarga);
        title_bar.setText(getIntent().getExtras().getString("title"));
        btn_back.setOnClickListener(this);
        btn_cari.setOnClickListener(this);

//        if(getIntent().getExtras().getString("pasien").equals("lama")){
//            adapterBooking    = new AdapterBooking(this,pendaftaranBookings);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this));
//            recyclerView.setAdapter(adapterBooking);
//            adapterBooking.notifyDataSetChanged();
//        }else{
        adapterBookingBaru    = new AdapterBookingBaru(this,pendaftaranBookings);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterBookingBaru);
        adapterBookingBaru.notifyDataSetChanged();
//        }

    }

    private void getNoCM(String namaPasien){
//        loading.show();
        Log.v("Ok",namaPasien);
        if(namaPasien.equals(session.getSessionUser().getNAMA())){
            kdNoCM=session.getSessionUser().getNORM();
            dataStringList.clear();

//            query.put("FAPTELEPON",""+session.getSessionUser().getNOTELP().trim());
            if(kdNoCM!=null){
                query.put("_kodePasien",""+kdNoCM);
                dataInfo.listPendaftaran(this,this,query);
            }

//            NAMAPASIEN=userPrimers.getNAMA();
//                Toast.makeText(this,""+kdDokter,Toast.LENGTH_LONG).show();
//            register_nocm_value.setText(""+kdNoCM);
        }

//        else{
        for (int i = 0; i < arrayListKeluargas.size(); i++){
            if(namaPasien.equals(arrayListKeluargas.get(i).getNamaKeluarga())){
                kdNoCM=arrayListKeluargas.get(i).getNocm();
                dataStringList.clear();
//                query.put("FAPTELEPON",""+session.getSessionUser().getNOTELP().trim());
                query.put("_kodePasien",""+kdNoCM);

                dataInfo.listPendaftaran(this,this,query);
            }
        }
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        try{loading.show();}catch (Exception ex){}
        if(getIntent().getExtras().getString("pasien").equals("lama")){
            layout_pasienbaru.setVisibility(View.GONE);
            layout_pasienlama.setVisibility(View.VISIBLE);
            if(session.getSessionUser().getNORM()!=null){
                dataString.put("idKeluarga",""+session.getSessionUser().getNORM());
                dataString.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
                dataInfo.listKeluarga(ActivityHistoryPendaftaran.this,null,this,dataString);

            }else{
                dialogAlerts.CreateDialogAlertsIsiNoRM("NORM KOSONG","Silahkan Mengisi NORM untuk menggunakan tiket booking");
            }
        }
        else{
            layout_pasienbaru.setVisibility(View.VISIBLE);
            layout_pasienlama.setVisibility(View.GONE);
            query.put("_kodePasien","-");
            query.put("FAPTELEPON",""+session.getSessionUser().getNOTELP().trim());

            dataInfo.listPendaftaran(this,this,query);

//
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back  : finish();
                break;
            case R.id.btn_cari  :
                dataStringList.clear();
                if(input_search.getText().toString().trim().length()>0){
                    dataStringList.put("_kodePasien","-");
                    dataStringList.put("FAPTELEPON",""+input_search.getText().toString().trim());
                    dataInfo.listPendaftaran(this,this,query);
                    loading.show();
                }else{
                    Toast.makeText(this,"Silahkan Masukkan NoTelp untuk Mencari Antrian Anda",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public void onSuccessLogin(RestPendaftaranLogin restPendaftaranLogin) {

    }

    @Override
    public void onErrorLogin(String Msg) {

    }

    @Override
    public void onSuccessGetKeluarga(ArrayList<Keluarga> arrayListKeluarga) {

        listKeluarga.clear();


        arrayListKeluargas=arrayListKeluarga;
        Keluarga primer = new Keluarga();
        primer.setNocm(session.getSessionUser().getNORM());
        primer.setNamaKeluarga(session.getSessionUser().getNAMA());
        primer.setTgllhr(session.getSessionUser().getTGLLAHIR());
        arrayListKeluargas.add(primer);
        listKeluarga.add(session.getSessionUser().getNAMA());

        for (int i = 0; i < arrayListKeluarga.size(); i++){
            if(!listKeluarga.contains(arrayListKeluarga.get(i).getNamaKeluarga())){
                listKeluarga.add(arrayListKeluarga.get(i).getNamaKeluarga());
            }

        }


        adapter_spinner_keluarga = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                listKeluarga);
        adapter_spinner_keluarga.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_keluarga.setAdapter(adapter_spinner_keluarga);
        spinner_keluarga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getNoCM(parent.getItemAtPosition(position).toString());
//                getDokter(parent.getItemAtPosition(position).toString());
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onErrorGetKeluarga(int num_data) {
        try{ loading.hide();}catch (Exception e){}
        listKeluarga.clear();



        Keluarga primer = new Keluarga();
        primer.setNocm(session.getSessionUser().getNORM());
        primer.setNamaKeluarga(session.getSessionUser().getNAMA());
        primer.setTgllhr(session.getSessionUser().getTGLLAHIR());
        arrayListKeluargas.add(primer);
        listKeluarga.add(session.getSessionUser().getNAMA());

        adapter_spinner_keluarga = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,
                listKeluarga);
        adapter_spinner_keluarga.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_keluarga.setAdapter(adapter_spinner_keluarga);
        spinner_keluarga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                getNoCM(parent.getItemAtPosition(position).toString());
//                getDokter(parent.getItemAtPosition(position).toString());
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSuccessAddKeluarga(String msg) {

    }

    @Override
    public void onErrorAddKeluarga(String msg) {

    }

    @Override
    public void onHapusKeluarga(Keluarga keluarga) {

    }

    @Override
    public void onSuccessHapus() {

    }

    @Override
    public void onErrorHapus(String msg) {

    }

    @Override
    public void onErrorValid() {
        try{loading.hide();}catch (Exception e){}

    }

    @Override
    public void onSuccessPendaftaran(RestPendaftaranBooking restPendaftaranBooking) {
        try{loading.hide();}catch (Exception e){}
        session.setSessionUserPend(restPendaftaranBooking.getPendaftaranLogin());
        pendaftaranBookings.clear();
//        if(getIntent().getExtras().getString("pasien").equals("lama")) {
//            adapterBooking.notifyDataSetChanged();
//        }else{
        adapterBookingBaru.notifyDataSetChanged();
//        }

        if(restPendaftaranBooking.getData().size()>0){
            layout_nodata.setVisibility(View.GONE);
        }else{
            layout_nodata.setVisibility(View.VISIBLE);
        }
        if(restPendaftaranBooking.getData()!=null){
//            tv_antrian_noavaiable.setVisibility(View.GONE);


//            if(getIntent().getExtras().getString("pasien").equals("lama")){
//                pendaftaranBookings.clear();
//                pendaftaranBookings.addAll(restPendaftaranBooking.getData());
//                recyclerView.setAdapter(adapterBooking);
//                adapterBooking.notifyDataSetChanged();
//            }else{
            pendaftaranBookings.clear();
            pendaftaranBookings.addAll(restPendaftaranBooking.getData());
            recyclerView.setAdapter(adapterBookingBaru);
            adapterBookingBaru.notifyDataSetChanged();
//            }

        }
    }

    @Override
    public void onErrorPendaftaran(String msg) {
        try{loading.hide();}catch (Exception e){}
        layout_nodata.setVisibility(View.VISIBLE);
    }
}
