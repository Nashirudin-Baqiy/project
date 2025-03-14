package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKritikSaran;

public class ActivityKritikSaranAdd extends AppCompatActivity implements View.OnClickListener,ViewsKritikSaran{
    private Button button_simpan;
    private Session session;
    private LinearLayout btn_back;
    private TextView title_bar;
    private EditText form_nama_value;
    private EditText form_nohp_value;
    private EditText form_email_value;
    private EditText form_desc_value;
    private Date now;
    private String userId;
    private String tgllhir;
    private DataInfo dataInfo;
    private HashMap<String, String> query = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kritik_saran_add);
        init();
    }


    private void init() {
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        form_nama_value = findViewById(R.id.form_nama_value);
        form_email_value = findViewById(R.id.form_email_value);
        form_nohp_value = findViewById(R.id.form_nohp_value);
        form_desc_value = findViewById(R.id.form_desc_value);
        session = new Session(this);
        button_simpan = (Button) findViewById(R.id.button_simpan);
        button_simpan.setOnClickListener(this);
        dataInfo = new DataInfo();
        title_bar.setText("Tanya RS");
        if(session.getSessionUser().getEMAIL()!=null){
            form_email_value.setText(" "+session.getSessionUser().getEMAIL());
        }

        if(session.getSessionUser().getNOTELP()!=null){
            form_nohp_value.setText(" "+session.getSessionUser().getNOTELP());
        }
        btn_back.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.button_simpan:
//                startActivity(new Intent(ActivityForm.this,ActivityMain.class));

                if (form_nama_value.getText().toString().trim().length() != 0 &&
                        form_nohp_value.getText().toString().trim().length() != 0 &&
                        form_desc_value.getText().toString().trim().length() != 0 &&
                        form_email_value.getText().toString().trim().length() != 0 ) {
                    query.put("ID_USER", "" + session.getSessionUser().getIDUSERS());
                    query.put("NAMA_USER", "" + session.getSessionUser().getNAMA());
                    query.put("JUDUL_KELUHAN", "" + form_nama_value.getText().toString());
                    query.put("DESKRIPSI_KELUHAN", "" + form_desc_value.getText().toString());
                    query.put("NO_HP", "" + form_nohp_value.getText().toString());
                    query.put("EMAIL", "" + form_email_value.getText().toString());

                    dataInfo.addKritikSaran(this,this,query);
                } else {

                    Toast.makeText(this, "Silahkan Lengkapi Form Anda", Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    @Override
    public void onSuccessLoadKritik(RestKritikSaran restKritikSaran) {
        Test.ToastSukses(ActivityKritikSaranAdd.this,"Mengirim");
        finish();
    }

    @Override
    public void onErrorLoadKritik(String msg) {
        Test.ToastKoneksi(ActivityKritikSaranAdd.this);
    }
}
