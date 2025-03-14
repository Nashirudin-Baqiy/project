package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;

public class ActivityBookingLogin extends AppCompatActivity implements  View.OnClickListener,ViewsPendaftaranLogin {
    private EditText form_nocm;
    private EditText form_tanggal;
    private Button button_login;
    private Button button_pasienlama;
    private Button button_pasienbaru;
    private Button button_kembali1;
    private Button button_kembali2;
    private LinearLayout layout_pilihanJenisPasien;
    private LinearLayout panel_bottom;
    private LinearLayout panel_bottom1;


    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;
    private HashMap<String,String> query= new HashMap<>();
    private DatePicker datePicker = new DatePicker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_login);
        init();
    }

    private void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        form_nocm        = (EditText) findViewById(R.id.form_nocm);
        form_tanggal        = (EditText) findViewById(R.id.form_tanggal);
        button_login        = (Button) findViewById(R.id.button_login);
        button_pasienlama        = (Button) findViewById(R.id.button_pasienlama);
        button_pasienbaru        = (Button) findViewById(R.id.button_pasienbaru);
        button_kembali1        = (Button) findViewById(R.id.button_kembali1);
        button_kembali2        = (Button) findViewById(R.id.button_kembali2);
        layout_pilihanJenisPasien        = (LinearLayout) findViewById(R.id.layout_pilihanJenisPasien);
        panel_bottom        = (LinearLayout) findViewById(R.id.panel_bottom);
        panel_bottom1        = (LinearLayout) findViewById(R.id.panel_bottom1);

        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataInfo();
        title_bar.setText("Pendaftaran");

        panel_bottom.setVisibility(View.VISIBLE);
        button_kembali1.setVisibility(View.GONE);
        button_kembali2.setVisibility(View.GONE);
        panel_bottom1.setVisibility(View.GONE);
        layout_pilihanJenisPasien.setVisibility(View.GONE);
        if(session.getSessionBoolean("loginPend",false)){
            finish();
            startActivity(new Intent(ActivityBookingLogin.this,ActivityBookingList.class));
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        button_kembali1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_bottom.setVisibility(View.GONE);
                panel_bottom1.setVisibility(View.GONE);
                layout_pilihanJenisPasien.setVisibility(View.VISIBLE);
            }
        });
        button_kembali2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_bottom.setVisibility(View.GONE);
                panel_bottom1.setVisibility(View.GONE);
                layout_pilihanJenisPasien.setVisibility(View.VISIBLE);
            }
        });

        button_pasienlama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                panel_bottom1.setVisibility(View.GONE);
                layout_pilihanJenisPasien.setVisibility(View.GONE);
                panel_bottom.setVisibility(View.VISIBLE);


            }
        });

        button_pasienbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_pilihanJenisPasien.setVisibility(View.GONE);
                panel_bottom.setVisibility(View.GONE);
                panel_bottom1.setVisibility(View.VISIBLE);

            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(form_nocm.getText().toString().trim().length()>0
                        && form_tanggal.getText().toString().trim().length()>0){
                    query.put("kodePasien",""+form_nocm.getText().toString());
                    query.put("tanggalLahir",""+form_tanggal.getText().toString());
                    dataInfo.loginPendaftaran(ActivityBookingLogin.this,ActivityBookingLogin.this,query);
                }else{
                    Toast.makeText(ActivityBookingLogin.this,"Form Login Tidak boleh Kosong",Toast.LENGTH_LONG).show();
                }
            }
        });

        if(session.getSessionUser().getNORM()!=null&&session.getSessionUser().getNORM()!=null || session.getSessionUser().getNORM().equals("0") && session.getSessionUser().getTGLLAHIR().equals("0")){
            form_nocm.setText(""+session.getSessionUser().getNORM().trim());
            form_tanggal.setText(""+session.getSessionUser().getTGLLAHIR());
        }
        form_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setViewTimePickers(ActivityBookingLogin.this,form_tanggal);
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
        finish();
        session.setSessionUserPend(restPendaftaranLogin.getPendaftaranLogin());
        session.setSessionBoolean("loginPend",true);
        startActivity(new Intent(ActivityBookingLogin.this,ActivityBookingList.class));
    }

    @Override
    public void onErrorLogin(String Msg) {
        Toast.makeText(ActivityBookingLogin.this,"Password/No CM Salah",Toast.LENGTH_LONG).show();
    }
}
