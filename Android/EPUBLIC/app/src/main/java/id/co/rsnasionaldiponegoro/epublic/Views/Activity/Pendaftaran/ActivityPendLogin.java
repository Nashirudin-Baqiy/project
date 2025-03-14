package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.PendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityEditKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;

public class ActivityPendLogin extends AppCompatActivity implements View.OnClickListener,ViewsPendaftaranLogin,ViewsKeluarga {
    private EditText form_nocm;
    private EditText form_tanggal;
    private EditText form_baru_tanggal;
    private EditText form_baru_nama;
    private EditText form_baru_alamat;
    private EditText form_baru_nohp;
    private Button button_login;

    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;
    private HashMap<String,String> query= new HashMap<>();
    private DatePicker datePicker = new id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker();

    private Button button_pasienlama;
    private Button button_pasienbaru;
    private Button button_kembali1;
    private Button button_kembali2;
    private Button button_baru_daftar;
    private LinearLayout layout_pilihanJenisPasien;
    private LinearLayout panel_bottom;
    private LinearLayout panel_bottom1;
    private RadioGroup radioGroup;
    private RadioButton rb_pribadi;
    private RadioButton rb_keluarga;
    private LinearLayout layout_login_rm_manual;
    private LinearLayout layout_rm_keluarga;
    private Spinner spinner_keluarga;
    private ArrayAdapter<String> adapter_spinner_keluarga;
    private List<String> listKeluarga= new ArrayList<String>();
    private ArrayList<Keluarga> keluargaArrayList = new ArrayList<>();
    private HashMap<String, String> dataString = new HashMap<String, String>();
    private TextView btn_tmbh_norm_klg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pend_login);
        init();
    }

    private void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        form_nocm        = (EditText) findViewById(R.id.form_nocm);
        form_tanggal        = (EditText) findViewById(R.id.form_tanggal);
        form_baru_tanggal        = (EditText) findViewById(R.id.form_baru_tanggal);
        form_baru_nama        = (EditText) findViewById(R.id.form_baru_nama);
        form_baru_alamat        = (EditText) findViewById(R.id.form_baru_alamat);
        form_baru_nohp        = (EditText) findViewById(R.id.form_baru_nohp);

        button_login        = (Button) findViewById(R.id.button_login);
        button_pasienlama        = (Button) findViewById(R.id.button_pasienlama);
        button_pasienbaru        = (Button) findViewById(R.id.button_pasienbaru);
        button_kembali1        = (Button) findViewById(R.id.button_kembali1);
        button_kembali2        = (Button) findViewById(R.id.button_kembali2);
        button_baru_daftar        = (Button) findViewById(R.id.button_baru_daftar);
        layout_pilihanJenisPasien        = (LinearLayout) findViewById(R.id.layout_pilihanJenisPasien);
        panel_bottom        = (LinearLayout) findViewById(R.id.panel_bottom);
        panel_bottom1        = (LinearLayout) findViewById(R.id.panel_bottom1);


        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataInfo();
        title_bar.setText("Pendaftaran");
        form_baru_nohp.setText(""+session.getSessionUser().getNOTELP());


       form_baru_nama.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
        panel_bottom.setVisibility(View.GONE);
        panel_bottom1.setVisibility(View.GONE);
        layout_pilihanJenisPasien.setVisibility(View.VISIBLE);

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

        button_baru_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(form_baru_nama.getText().toString().trim().length()>0 &&
                       form_baru_nohp.getText().toString().trim().length()>0 &&
                       form_baru_alamat.getText().toString().trim().length()>0 &&
                       form_baru_tanggal.getText().toString().trim().length()>0){
                   RestPendaftaranLogin restPendaftaranLogin = new RestPendaftaranLogin();
                   PendaftaranLogin pendaftaranLogin = new PendaftaranLogin();
                   restPendaftaranLogin.setSuccess(true);
                   pendaftaranLogin.setkDPASIEN("-");
                   pendaftaranLogin.settGLLAHIR(""+form_baru_tanggal.getText().toString().trim());
                   pendaftaranLogin.setaLAMAT(""+form_baru_alamat.getText().toString().trim());
                   pendaftaranLogin.settELEPON(""+form_baru_nohp.getText().toString().trim());
                   pendaftaranLogin.setnAMAPASIEN(""+form_baru_nama.getText().toString().trim());
                   restPendaftaranLogin.setPendaftaranLogin(pendaftaranLogin);
                   session.setSessionUserPend(restPendaftaranLogin.getPendaftaranLogin());
                   startActivity(new Intent(ActivityPendLogin.this,ActivityPendTgl.class));
               }else {
                   Toast.makeText(ActivityPendLogin.this,"Silahkan Melengkapi Form",Toast.LENGTH_LONG).show();
               }
            }
        });

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
                    dataInfo.loginPendaftaran(ActivityPendLogin.this,ActivityPendLogin.this,query);
                }else{
                    Toast.makeText(ActivityPendLogin.this,"Form Login Tidak boleh Kosong",Toast.LENGTH_LONG).show();
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
                datePicker.setViewTimePickers(ActivityPendLogin.this,form_tanggal);
                DialogFragment newFragment = datePicker;
                newFragment.show(getFragmentManager(),"datepicker");
            }
        });

        form_baru_tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setViewTimePickers(ActivityPendLogin.this,form_baru_tanggal);
                DialogFragment newFragment = datePicker;
                newFragment.show(getFragmentManager(),"datepicker");
            }
        });


        radioGroup= findViewById(R.id.radioGroup);
        rb_pribadi= findViewById(R.id.rb_pribadi);
        rb_keluarga= findViewById(R.id.rb_keluarga);
        layout_rm_keluarga= findViewById(R.id.layout_rm_keluarga);
        layout_login_rm_manual= findViewById(R.id.layout_login_rm_manual);
        spinner_keluarga= findViewById(R.id.spinner_keluarga);
        spinner_keluarga= findViewById(R.id.spinner_keluarga);
        btn_tmbh_norm_klg= findViewById(R.id.btn_tmbh_norm_klg);

        if(session.getSessionBoolean("loginPend",false)){
            finish();
            startActivity(new Intent(ActivityPendLogin.this,ActivityPendHome.class));
        }else {
            dataString.put("idKeluarga", "" + session.getSessionUser().getNORM());
            dataString.put("idUserPrimer", "" + session.getSessionUser().getIDUSERS());
            dataInfo.listKeluarga(ActivityPendLogin.this, null, this, dataString);
        }
        btn_tmbh_norm_klg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.getSessionUser().getNORM()==null ){
//            dialogAlertsstar
                    dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk menambah RM Keluarga");
                }else if(session.getSessionUser().getNORM().trim().equals("0")){
//            dialogAlertsstar
                    dialogAlerts.CreateDialogAlertsIsiNoRM("NORM Kosong","Silahkan Mengisi NoRM Anda Untuk menambah RM Keluarga");
                }else{
                    startActivity(new Intent(ActivityPendLogin.this,ActivityEditKeluarga.class).putExtra("title","Keluarga Saya"));
                }

            }
        });
        layout_login_rm_manual.setVisibility(View.VISIBLE);
        layout_rm_keluarga.setVisibility(View.GONE);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_pribadi:
                        layout_login_rm_manual.setVisibility(View.VISIBLE);
                        layout_rm_keluarga.setVisibility(View.GONE);
                        break;
                    case R.id.rb_keluarga:
                        layout_login_rm_manual.setVisibility(View.GONE);
                        layout_rm_keluarga.setVisibility(View.VISIBLE);
                        break;


                }
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
        startActivity(new Intent(ActivityPendLogin.this,ActivityPendHome.class));
    }

    @Override
    public void onErrorLogin(String Msg) {
        Toast.makeText(ActivityPendLogin.this,"Password/No CM Salah",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessGetKeluarga(ArrayList<Keluarga> arrayListKeluarga) {
        listKeluarga.clear();


        keluargaArrayList=arrayListKeluarga;
        Keluarga primer = new Keluarga();
        primer.setNocm(session.getSessionUser().getNORM());
        primer.setNamaKeluarga(session.getSessionUser().getNAMA());
        primer.setTgllhr(session.getSessionUser().getTGLLAHIR());
        keluargaArrayList.add(primer);
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
                onSetKeluarga(parent.getSelectedItem().toString());
//                getDokter(parent.getItemAtPosition(position).toString());
            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onSetKeluarga(String s) {
        for (int i = 0; i < keluargaArrayList.size(); i++){
            if(s.equals(keluargaArrayList.get(i).getNamaKeluarga())){
                form_nocm.setText(""+keluargaArrayList.get(i).getNocm());
                form_tanggal.setText(""+keluargaArrayList.get(i).getTgllhr());
            }
        }
    }
    @Override
    public void onErrorGetKeluarga(int num_data) {

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

    }
}
