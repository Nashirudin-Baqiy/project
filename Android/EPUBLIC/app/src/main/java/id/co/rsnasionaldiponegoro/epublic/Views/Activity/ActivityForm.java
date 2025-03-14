package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

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



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataUser;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPGen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPLogin;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsLogin;

public class ActivityForm extends AppCompatActivity implements View.OnClickListener, ViewsLogin {
    private Button button_simpan;
    private Session session;
    private LinearLayout btn_back;
    private TextView title_bar;
    private EditText form_nama_value;
    private EditText form_email_value;
    private EditText form_nohp_value;
    private EditText form_norm_value;
    private EditText form_tgllahir_value;
    private Date now;
    private String userId;
    private String tgllhir;
    private DataUser dataUser;
    private HashMap<String, String> query = new HashMap<>();
    private DatePicker datePicker = new DatePicker();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        init();
    }

    private void init() {
        btn_back = findViewById(R.id.btn_back);
        title_bar = findViewById(R.id.title_bar);
        form_nama_value = findViewById(R.id.form_nama_value);
        form_email_value = findViewById(R.id.form_email_value);
        form_nohp_value = findViewById(R.id.form_nohp_value);
        form_norm_value = findViewById(R.id.form_norm_value);
        form_tgllahir_value = findViewById(R.id.form_tgllahir_value);

        session = new Session(this);

        button_simpan = (Button) findViewById(R.id.button_simpan);
        button_simpan.setOnClickListener(this);
        form_tgllahir_value.setOnClickListener(this);
        dataUser = new DataUser();
        tgllhir = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

        if (getIntent().getExtras().getString("title") != null) {
            title_bar.setText(getIntent().getExtras().getString("title"));
        }

        if (getIntent().getExtras().getString("userId") != null) {
            userId = getIntent().getExtras().getString("userId");
        }

        if (getIntent().getExtras().getString("noHp") != null) {
            form_nohp_value.setText(getIntent().getExtras().getString("noHp"));
        }

        form_tgllahir_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setViewTimePickers(ActivityForm.this,form_tgllahir_value);
                DialogFragment newFragment = datePicker;
                newFragment.show(getFragmentManager(),"datepicker");
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.button_simpan:
//                finish();
//                session.setSessionBoolean("isLogin",true);
//                startActivity(new Intent(ActivityForm.this,ActivityMain.class));
                tgllhir=form_tgllahir_value.getText().toString();
                if (form_nama_value.getText().toString().trim().length() != 0 &&
                        form_nohp_value.getText().toString().trim().length() != 0 ) {
                    query.put("userId", "" + userId);
                    query.put("nama", "" + form_nama_value.getText().toString());
                    query.put("nohp", "" + form_nohp_value.getText().toString());

                    if(form_email_value.getText().toString().trim().length()!=0){
                        query.put("email", "" + form_email_value.getText().toString());
                    }else{
                        query.put("email", "-");
                    }

                    if(form_norm_value.getText().toString().trim().length()!=0){
                        query.put("norm", "" + form_norm_value.getText().toString());
                        if(tgllhir.trim().length()!=0){
                            query.put("tgllhr", ""+tgllhir);
                        }else{
                            query.put("tgllhr", "0");
                        }
                    }else{
                        query.put("norm", "0");
                        query.put("tgllhr", "0");
                    }




                    dataUser.addUser(this, this, query);
                } else {

                    Toast.makeText(this, "Silahkan Lengkapi Form Anda", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.form_tgllahir_value:


//                Calendar cal = Calendar.getInstance();
//                new DatePickerDialog(
//                        ActivityForm.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                form_tgllahir_value.setText("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(year - 1900, month, dayOfMonth)));
//                                tgllhir = new SimpleDateFormat("yyyy-MM-dd").format(new Date(year - 1900, month, dayOfMonth));
//                            }
//                        },
//                        cal.get(Calendar.YEAR),
//                        cal.getInstance().get(Calendar.MONTH),
//                        cal.getInstance().get(Calendar.DAY_OF_MONTH)
//                ).show();

                break;

        }
    }

    @Override
    public void onSuccessLogin(RestLogin restLogin) {
        if (restLogin != null) {
            session.setSessionBoolean("isLogin", true);
            session.setSessionUser(restLogin.getUser().get(0));
            finish();
            startActivity(new Intent(ActivityForm.this, ActivityMain.class));
        }
    }

    @Override
    public void onErrorLogin(RestLogin restLogin, String msg) {
        Toast.makeText(this, "" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessNewuser(String userId, String nohp) {

    }

    @Override
    public void onSuccessGenOTP(RestOTPGen restGenOTP) {

    }

    @Override
    public void onSuccessLoginOTP(RestOTPLogin restOTPLogin) {

    }

    @Override
    public void onErrorOtp(String from, String msg) {

    }
}
