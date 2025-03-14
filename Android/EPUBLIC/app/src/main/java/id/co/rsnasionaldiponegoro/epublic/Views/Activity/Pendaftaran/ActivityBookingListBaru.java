package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterBooking;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterBookingBaru;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;

public class ActivityBookingListBaru extends AppCompatActivity implements ViewsPendaftaran {

    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;
    private HashMap<String,String> query= new HashMap<>();
    private ArrayList<PendaftaranBooking> pendaftaranBookings = new ArrayList<>();
    private AdapterBooking adapterBooking;
    private AdapterBookingBaru adapterBookingBaru;
    private RecyclerView recyclerView;
    private TextView text_nodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list_baru);
        init();
    }


    void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        text_nodata        = (TextView) findViewById(R.id.text_nodata);

        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        recyclerView        = (RecyclerView) findViewById(R.id.recyclerView);
        dataInfo= new DataInfo();

            adapterBookingBaru    = new AdapterBookingBaru(this,pendaftaranBookings);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapterBookingBaru);
            adapterBookingBaru.notifyDataSetChanged();



        title_bar.setText("Tiket Booking");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void onSuccessPendaftaran(RestPendaftaranBooking restPendaftaranBooking) {
        loading.hide();
        pendaftaranBookings.clear();
        adapterBookingBaru.notifyDataSetChanged();
        if(restPendaftaranBooking.getData().size()>0){
            text_nodata.setVisibility(View.GONE);
        }else{
            text_nodata.setVisibility(View.VISIBLE);
        }
        if(restPendaftaranBooking.getData()!=null){
//            tv_antrian_noavaiable.setVisibility(View.GONE);

                pendaftaranBookings.clear();
                pendaftaranBookings.addAll(restPendaftaranBooking.getData());
                recyclerView.setAdapter(adapterBookingBaru);
                adapterBookingBaru.notifyDataSetChanged();


        }

    }

    @Override
    public void onErrorPendaftaran(String msg) {
        loading.hide();
        text_nodata.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loading.show();
        query.clear();

        query.put("_kodePasien","-");
        query.put("FAPTELEPON",""+session.getSessionUser().getNOTELP().trim());

        dataInfo.listPendaftaran(this,this,query);
    }
}
