package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPoints;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.Pendaftaran.ActivityTMKonsulAdd;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityTelemedicineDashboard extends AppCompatActivity {
    private LinearLayout btn_back;
    private LinearLayout btn_TMAdd;
    private LinearLayout btn_TMNow;
    private LinearLayout btn_TMHistory;
    private LinearLayout btn_TMPointTopUp;
    private LinearLayout btn_TMPointHistory;
    private TextView title_bar;
    private TextView txt_hello;
    private TextView txt_TMpoints;
    private Session session;
    private DialogAlerts dialogAlerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telemedicine_dashboard);
        init();

    }

    private void init() {
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);

        btn_TMAdd = findViewById(R.id.btn_TMAdd);
        btn_TMNow = findViewById(R.id.btn_TMNow);
        btn_TMHistory = findViewById(R.id.btn_TMHistory);
        btn_TMPointTopUp = findViewById(R.id.btn_TMPointTopUp);
        btn_TMPointHistory = findViewById(R.id.btn_TMPointHistory);
        txt_hello = findViewById(R.id.txt_hello);
        txt_TMpoints = findViewById(R.id.txt_TMpoints);

        session = new Session(this);
        dialogAlerts = new DialogAlerts(this,this);
        txt_hello.setText("Hello , "+session.getSessionUser().getNAMA()+" !");
        //txt_TMpoints.setText(""+session.getSessionInteger("TMPoint",0)+" Points");
        title_bar.setText("TeleMedicine");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        actionBtn();


    }

    private void getPoints() {
        try{
            InterfaceTelemedicine interfaceInfo = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
            Observable<RestPoints> bannerObservable = interfaceInfo.getPoints(session.getSessionUser().getNOTELP());
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<RestPoints>() {
                @Override
                public void onNext(RestPoints restVersion) {
                    if(restVersion.getSuccess()){
                        txt_TMpoints.setText(""+restVersion.getpOINTS()+" Points");
                    }
                }

                @Override
                public void onError(Throwable e) {
                    txt_TMpoints.setText("0 Points");
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
    public void actionBtn(){
        btn_TMAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTelemedicineDashboard.this, ActivityTMKonsulAdd.class)
                        .putExtra("title", "TeleMedicine"));
            }
        });

        btn_TMNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTelemedicineDashboard.this, ActivityTMKonsulKuHistory.class)
                        .putExtra("title", "KonsulKu"));
            }
        });

        btn_TMHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTelemedicineDashboard.this, ActivityTMKonsulHistory.class)
                        .putExtra("title", "KonsulHistory"));
            }
        });

        btn_TMPointTopUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTelemedicineDashboard.this, ActivityTMTopUpAdd.class)
                        .putExtra("title", "TopUpPoint"));
            }
        });

        btn_TMPointHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityTelemedicineDashboard.this, ActivityTMTopUpHistory.class)
                        .putExtra("title", "History TopUp"));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPoints();

        if(session.getSessionUser().getNORM()==null ){
//            dialogAlertsstar
            dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk melihat riwayat");
        }
        if(session.getSessionUser().getNORM().trim().equals("0")){
//            dialogAlertsstar
            dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk melihat riwayat");
        }
    }
}
