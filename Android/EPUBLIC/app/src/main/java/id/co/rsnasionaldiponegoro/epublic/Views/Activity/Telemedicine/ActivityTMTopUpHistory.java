package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.TopUpHistory;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestTopupHistory;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterTelemedicineTopUpHistory;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityTMTopUpHistory extends AppCompatActivity {
    private TextView title_bar;
    private LinearLayout btn_back;
    private RecyclerView recyclerView;
    private ArrayList<TopUpHistory> arrayList = new ArrayList<>();
    private Loading loading;
    private AdapterTelemedicineTopUpHistory adapterDokter;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmtop_up_history);
        init();
    }


    private void init(){

        recyclerView    = findViewById(R.id.recycler_view);
        loading         = new Loading(this);
        session         = new Session(ActivityTMTopUpHistory.this);
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        title_bar.setText(""+getIntent().getExtras().getString("title"));
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }



    private void getData(){
        loading.show();
        arrayList.clear();
        InterfaceTelemedicine interfaceTelemedicine     = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestTopupHistory> jadwalDokter       = interfaceTelemedicine.getPointsHistory(session.getSessionUser().getNOTELP());
        jadwalDokter.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestTopupHistory>() {
            @Override
            public void onNext(RestTopupHistory jadwalDokters) {
                arrayList.addAll(jadwalDokters.getData());
            }

            @Override
            public void onError(Throwable e) {
//                Snackbar.make(findViewById(R.id.content),"Koneksi Buruk...",Snackbar.LENGTH_SHORT).show();
                Toast.makeText(ActivityTMTopUpHistory.this,"Koneksi Buruk...",Toast.LENGTH_LONG).show();
                loading.hide();
            }

            @Override
            public void onComplete() {
                adapterDokter = new AdapterTelemedicineTopUpHistory(ActivityTMTopUpHistory.this,arrayList);
                recyclerView.setLayoutManager(new LinearLayoutManager(ActivityTMTopUpHistory.this));
                recyclerView.setAdapter(adapterDokter);
                recyclerView.setNestedScrollingEnabled(true);
                recyclerView.setHasFixedSize(true);
                loading.hide();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
}
