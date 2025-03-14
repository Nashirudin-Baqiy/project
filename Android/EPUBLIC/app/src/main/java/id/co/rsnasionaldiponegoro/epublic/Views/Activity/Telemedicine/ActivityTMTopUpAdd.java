package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPoints;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityTMTopUpAdd extends AppCompatActivity {
    private LinearLayout btn_back;
    private TextView title_bar;
    private Button button_simpan;
    private TextView txt_hello;
    private TextView txt_TMpoints;
    private EditText form_point_beli;
    private TextView txt_jumlah_bayar;
    private Button button_beli;
    private Session session;
    private LinearLayout layout_konfrim;
    private LinearLayout layout_topup;
    private Button button_oke;
    private HashMap<String, String> query= new HashMap<>();
    private RadioGroup radioGroup;
    private RadioButton rb_btn;
    private RadioButton rb_mandiri;
    private RadioButton rb_bni;
    private String metode_raw="";

    private int points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tmtop_up_add);
        init();
    }

    private void init() {
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        txt_TMpoints = findViewById(R.id.txt_TMpoints);
        button_beli = findViewById(R.id.button_beli);
        form_point_beli = findViewById(R.id.form_point_beli);
        txt_jumlah_bayar = findViewById(R.id.txt_jumlah_bayar);
        layout_konfrim = findViewById(R.id.layout_konfrim);
        layout_topup = findViewById(R.id.layout_topup);
        button_oke = findViewById(R.id.button_oke);

        layout_topup.setVisibility(View.VISIBLE);
        layout_konfrim.setVisibility(View.GONE);
        title_bar.setText("TopUp Point");
        session = new Session(this);
        //txt_TMpoints.setText(""+session.getSessionInteger("TMPoint",0)+" Points");

        //radiobutton
        radioGroup= findViewById(R.id.radioGroup);
        rb_btn= findViewById(R.id.rb_btn);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_btn:
                        session.setSessionString("jenisPemb","BTN");
                        metode_raw=rb_btn.getText().toString();
                        break;


                }
            }
        });
        rb_btn.setChecked(true);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        form_point_beli.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length()>0) {
                    Double pointsv = (Double.parseDouble(s.toString()) * 80000);
                    points=Integer.parseInt(s.toString());
                    txt_jumlah_bayar.setText(formatRupiah(pointsv) + ",- (Biaya Belum Termasuk kode unik)");
                }else{
                    Double pointsv = (Double.parseDouble("0") * 80000);
                    points=Integer.parseInt("0");
                    txt_jumlah_bayar.setText(formatRupiah(pointsv) + ",- (Biaya Belum Termasuk kode unik)");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button_beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(points>0){
                    reqBeliPoints();

                }else{
                    Toast.makeText(ActivityTMTopUpAdd.this,"Silahkan Input Point yang akan di beli",Toast.LENGTH_LONG).show();
                }
            }
        });

        button_oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(points>0){
                    layout_konfrim.setVisibility(View.VISIBLE);
                    layout_topup.setVisibility(View.GONE);
//                    session.setSessionInteger("TMPoint",session.getSessionInteger("TMPoint",0)+points);
                    finish();


                }else{
                    layout_konfrim.setVisibility(View.GONE);
                    layout_topup.setVisibility(View.VISIBLE);
                    Toast.makeText(ActivityTMTopUpAdd.this,"Silahkan Input Point yang akan di beli",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private String formatRupiah(Double number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
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
    private void reqBeliPoints(){
        button_beli.setVisibility(View.GONE);
        query.clear();
        query.put("NOHP",""+session.getSessionUser().getNOTELP());
        query.put("KET","Pembayaran topup points "+txt_jumlah_bayar.getText().toString());
        query.put("NOMINAL",""+points);
        query.put("METODE",""+session.getSessionString("jenisPemb","MANDIRI"));
        query.put("METODE_RAW",""+metode_raw);
        query.put("NOMINAL_RAW",""+txt_jumlah_bayar.getText().toString());

        InterfaceTelemedicine interfaceTelemedicine     = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<Rest> jadwalDokter       = interfaceTelemedicine.reqAddPoints(query);
        jadwalDokter.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest jadwalDokters) {
                        if(jadwalDokters.getSuccess()){
                            layout_konfrim.setVisibility(View.VISIBLE);
                            layout_topup.setVisibility(View.GONE);
                        }else{
                            Toast.makeText(ActivityTMTopUpAdd.this,"Koneksi Buruk...",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
//                Snackbar.make(findViewById(R.id.content),"Koneksi Buruk...",Snackbar.LENGTH_SHORT).show();
                        Toast.makeText(ActivityTMTopUpAdd.this,"Koneksi Buruk...",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPoints();
    }
}
