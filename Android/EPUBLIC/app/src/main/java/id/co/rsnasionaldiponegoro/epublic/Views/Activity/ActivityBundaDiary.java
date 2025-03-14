package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;

public class ActivityBundaDiary extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout btn_back;
    private LinearLayout button_bunda_rj;
    private LinearLayout button_bunda_ri;
    private TextView title_bar;
    private Session session;
    private DialogAlerts dialogAlerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bunda_diary);
        init();
    }

    private void init(){
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        button_bunda_rj = findViewById(R.id.button_bunda_rj);
        button_bunda_ri = findViewById(R.id.button_bunda_ri);
        session = new Session(this);
        dialogAlerts = new DialogAlerts(this,this);
        title_bar.setText("Riwayat Kunjungan");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_bunda_rj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityBundaDiary.this,ActivityDiaryBundaRJ.class));
            }
        });
        button_bunda_ri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ActivityBundaDiary.this,ActivityDiaryBundaRI.class));
            }
        });

        if(session.getSessionUser().getNORM()==null ){
//            dialogAlertsstar
            dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk melihat riwayat");
        }
        if(session.getSessionUser().getNORM().trim().equals("0")){
//            dialogAlertsstar
            dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk melihat riwayat");
        }
    }

    @Override
    public void onClick(View view) {

    }
}
