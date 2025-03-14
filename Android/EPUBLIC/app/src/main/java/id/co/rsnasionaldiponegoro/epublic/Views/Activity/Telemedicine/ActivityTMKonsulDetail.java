package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestTMDetailKonsulHistory;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityTMKonsulDetail extends AppCompatActivity {
    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;
    private HashMap<String,String> query= new HashMap<>();
    private ArrayList<PendaftaranBooking> pendaftaranBookings = new ArrayList<>();
//    private AdapterBooking adapterBooking;
//    private AdapterBookingBaru adapterBookingBaru;
    private RecyclerView recyclerView;

    private TextView detail_kdantri;
    private TextView detail_judul;
    private TextView detail_tgl;
    private TextView detail_status;

    private TextView detail_keluhan;
    private TextView detail_diagnosa;
    private TextView detail_saranDokter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmkonsul_detail);
        init();
    }

    private void init() {
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);

        detail_kdantri        = (TextView) findViewById(R.id.detail_kdantri);
        detail_judul        = (TextView) findViewById(R.id.detail_judul);
        detail_tgl        = (TextView) findViewById(R.id.detail_tgl);
        detail_status        = (TextView) findViewById(R.id.detail_status);

        detail_keluhan        = (TextView) findViewById(R.id.detail_keluhan);
        detail_diagnosa        = (TextView) findViewById(R.id.detail_diagnosa);
        detail_saranDokter        = (TextView) findViewById(R.id.detail_saranDokter);


        title_bar.setText("Detail Konsul");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void getDetail() {
        try{
            InterfaceTelemedicine interfaceInfo = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
            Observable<RestTMDetailKonsulHistory> bannerObservable = interfaceInfo.getDetailKonsulHistory(getIntent().getExtras().getString("notrans"));
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<RestTMDetailKonsulHistory>() {
                        @Override
                        public void onNext(RestTMDetailKonsulHistory rest) {
                            if(rest.getSuccess()){
                                detail_kdantri.setText("Kode Antri: "+rest.getData().get(0).getfAPANTRIDOKTER());
                                detail_judul.setText(""+rest.getData().get(0).getfAPKELUHAN());
                                detail_tgl.setText(""+rest.getData().get(0).getFAP_DATE_CREATED());

                                if(rest.getData().get(0).getfAPSTATUSPROSES().equals("0")){
                                    detail_status.setText("Status: Dalam Antrian");
                                }else  if(rest.getData().get(0).getfAPSTATUSPROSES().equals("1")){
                                    detail_status.setText("Status: Siap Konsultasi");
                                }else{
                                    detail_status.setText("Status: Sudah Konsultasi");
                                }

                                detail_keluhan.setText(""+rest.getData().get(0).getfAPKELUHANKET());
                                detail_diagnosa.setText(""+rest.getData().get(0).getmRPKDPENYAKIT()+" "+rest.getData().get(0).getpENYAKIT());
                                detail_saranDokter.setText(""+rest.getData().get(0).getmRDSOAPIKET());



                            }
                        }

                        @Override
                        public void onError(Throwable e) {

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
    protected void onResume() {
        super.onResume();
        getDetail();
    }
}
