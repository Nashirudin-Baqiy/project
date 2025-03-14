package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.Pendaftaran;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityInformConsern;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendPoli;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;

public class ActivityTMKonsulAdd extends AppCompatActivity  implements View.OnClickListener,ViewsPendaftaranLogin,ViewsPendPoli {
    private EditText form_nocm;
    private EditText form_tanggal;
    private Button button_login;

    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataTelemedicine dataInfo;
    private HashMap<String,String> query= new HashMap<>();
    private DatePicker datePicker = new id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmkonsul_add);
        init();
    }

    private void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        form_nocm        = (EditText) findViewById(R.id.form_nocm);
        form_tanggal        = (EditText) findViewById(R.id.form_tanggal);
        button_login        = (Button) findViewById(R.id.button_login);

        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(ActivityTMKonsulAdd.this,ActivityTMKonsulAdd.this);
        dataInfo = new DataTelemedicine();
        title_bar.setText("Pendaftaran");

//        if(session.getSessionBoolean("loginPend",false)){
//            finish();
//            startActivity(new Intent(ActivityTMKonsulAdd.this,ActivityPendHome.class));
//        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(form_nocm.getText().toString().trim().length()>0
                        && form_tanggal.getText().toString().trim().length()>0){
                    query.put("kodePasien",""+form_nocm.getText().toString());
                    query.put("tanggalLahir",""+form_tanggal.getText().toString());
                    dataInfo.loginPendaftaran(ActivityTMKonsulAdd.this,ActivityTMKonsulAdd.this,query);
                }else{
                    Toast.makeText(ActivityTMKonsulAdd.this,"Form Login Tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }
            }
        });

        if(session.getSessionUser().getNORM()!=null&&session.getSessionUser().getNORM()!=null || session.getSessionUser().getNORM().equals("0") && session.getSessionUser().getTGLLAHIR().equals("0")){
            form_nocm.setText(""+session.getSessionUser().getNORM());
            form_tanggal.setText(""+session.getSessionUser().getTGLLAHIR());
        }
        form_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setViewTimePickers(ActivityTMKonsulAdd.this,form_tanggal);
                DialogFragment newFragment = datePicker;
                newFragment.show(getFragmentManager(),"datepicker");
            }
        });
    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessLogin(RestPendaftaranLogin restPendaftaranLogin) {
//        finish();
        session.setSessionUserPend(restPendaftaranLogin.getPendaftaranLogin());
        session.setSessionBoolean("loginPend",true);
        query.clear();
        query.put("_kodePasien",""+session.getSessionUserPend().getkDPASIEN());
//        query.put("tglPeriksa",""+tglPeriksa);
        dataInfo.getPendPoli(this,this,query);
//        startActivity(new Intent(ActivityTMKonsulAdd.this,ActivityTMKonsulAddPoli.class));
    }

    @Override
    public void onErrorLogin(String Msg) {
        Toast.makeText(ActivityTMKonsulAdd.this,"Password/No CM Salah",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessPendPoli(RestPendaftaranPoli restPendaftaranPoli) {
//        loading.hide();
//        startActivity(new Intent(this,ActivityTMKonsulAddPoli.class));
        startActivity(new Intent(this,ActivityInformConsern.class));
    }

    @Override
    public void onErrorPendPoli(String msg) {
//        loading.hide();
//        Toast.makeText(ActivityTMKonsulAdd.this,"Mohon Maaf "+msg,Toast.LENGTH_LONG).show();
            dialogAlerts.CreateDialogAlertsPositive("Mohon Maaf", "" + msg);

    }
}
