package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Data.DataUser;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPGen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestVersion;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityLogin extends AppCompatActivity implements ViewsLogin {
    private static final String TAG = "debug";
    private Session session;
    private DataUser dataUser;
    public static int APP_REQUEST_CODE = 99;
    private HashMap<String, String> query = new HashMap<>();
    private HashMap<String, String> queryOtpGen = new HashMap<>();
    private HashMap<String, String> queryOtpLogin = new HashMap<>();
    private Loading loading;
    private TextView version;
    private TextView tx_welcome;
    private LinearLayout layout_login;
    private LinearLayout btn_firstlogin;
    private LinearLayout layout_otp;
    private RelativeLayout layout_otputama;


    private Button button_lanjut;
    private Button button_verif;
    private Button button_reset;
    private EditText form_notelp;
    private EditText form_otp;
    private WebView webView;
    private DialogAlerts dialogAlerts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    public void init() {

        webView = (WebView)findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/animasibg/index.html");
        dialogAlerts = new DialogAlerts(ActivityLogin.this,ActivityLogin.this);
        layout_login=findViewById(R.id.layout_login);
        layout_otputama=findViewById(R.id.layout_otputama);
        btn_firstlogin=findViewById(R.id.btn_firstlogin);
        button_lanjut=findViewById(R.id.button_lanjut);
        form_notelp=findViewById(R.id.form_notelp);
        layout_otp=findViewById(R.id.layout_otp);
        form_otp=findViewById(R.id.form_otp);
        button_verif=findViewById(R.id.button_verif);
        button_reset=findViewById(R.id.button_reset);

        session = new Session(ActivityLogin.this);
        loading = new Loading(this);
        version = findViewById(R.id.version);
        dataUser = new DataUser();
        layout_otputama.setVisibility(View.GONE);
        if (session.getSessionBoolean("isLogin", false)) {
            finish();
            startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
        }
        version.setText("V "+ BuildConfig.VERSION_NAME);
        tx_welcome = findViewById(R.id.tx_welcome);
        try{
            tx_welcome.setText("Selamat Datang pada Aplikasi "+getResources().getString(R.string.rs_name));
            tx_welcome.setSelected(true);
        }catch (Exception ex){
        }


        btn_firstlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_login.setVisibility(View.GONE);
                layout_otputama.setVisibility(View.VISIBLE);
            }
        });

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_lanjut.setVisibility(View.VISIBLE);
                layout_otp.setVisibility(View.GONE);

            }
        });

        button_verif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(form_otp.getText().toString().trim().length()>0){
                    queryOtpLogin.put("pin",""+form_otp.getText().toString().trim());
                    queryOtpLogin.put("nohp",""+form_notelp.getText().toString().trim());
//                    loading.show();
                    dataUser.loginOTP(ActivityLogin.this,ActivityLogin.this,queryOtpLogin);
                }else{
                    Toast.makeText(ActivityLogin.this,"OTP tidak boleh kosong!",Toast.LENGTH_LONG).show();
                }
            }
        });

        button_lanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(form_notelp.getText().toString().trim().length()>0){
                    queryOtpGen.put("app",""+BuildConfig.APP);
                    queryOtpGen.put("nohp",""+form_notelp.getText().toString().trim());
//                    loading.show();
                    dataUser.generateOTP(ActivityLogin.this,ActivityLogin.this,queryOtpGen);
                }else{
                    Toast.makeText(ActivityLogin.this,"Nomor tidak boleh kosong!",Toast.LENGTH_LONG).show();
                }
            }
        });
//        final ImageView backgroundOne = (ImageView) findViewById(R.id.background_one);
//        final ImageView backgroundTwo = (ImageView) findViewById(R.id.background_two);
//
//        final ValueAnimator animator = ValueAnimator.ofFloat(0.0f, 1.0f);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setInterpolator(new LinearInterpolator());
//        animator.setDuration(10000L);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                final float progress = (float) animation.getAnimatedValue();
//                final float width = backgroundOne.getWidth();
//                final float translationX = width * progress;
//                backgroundOne.setTranslationX(translationX);
//                backgroundTwo.setTranslationX(translationX - width);
//            }
//        });
//        animator.start();
    }

    //



    private void checkUpdate() {
        try{
            InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
            Observable<RestVersion> bannerObservable = interfaceInfo.getVersion();
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestVersion>() {
                @Override
                public void onNext(RestVersion restVersion) {
                    if(restVersion.getSuccess()){
                        setcheckUpdate(restVersion);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    dialogAlerts.CreateDialogAlertsPositiveUpdateNon("Update Terbaru", "Silahkan Update Aplikasi anda karena System Update ke versi terbaru!");
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

    private void setcheckUpdate(final RestVersion restVersion) {
        try {
            int versionCode = Integer.valueOf(BuildConfig.VERSION_CODE);
            int versionCodeNow = Integer.valueOf(restVersion.getVersionCode());
            int force = Integer.valueOf(restVersion.getForceUpdate());
            session.setSessionString("csWA",""+restVersion.getCsWa());
            if (versionCode < versionCodeNow) {
                if (force == 1) {
                    dialogAlerts.CreateDialogAlertsPositiveUpdate("Update Terbaru", "Silahkan Update Aplikasi pada Versi " + restVersion.getVersionName());
                } else {
                    dialogAlerts.CreateDialogAlertsPositiveUpdateNon("Update Terbaru", "Silahkan Update Aplikasi pada Versi " + restVersion.getVersionName());
                }
            }
        }catch (Exception ex){
            Test.look(ex.getMessage());
        }
    }
    @Override
    public void onSuccessLogin(RestLogin restLogin) {
        loading.hide();
        if (restLogin != null) {
            session.setSessionUser(restLogin.getUser().get(0));
            session.setSessionBoolean("isLogin", true);
            startActivity(new Intent(ActivityLogin.this, ActivityMain.class));
            finish();
        }
    }

    @Override
    public void onErrorLogin(RestLogin restLogin, String msg) {
        loading.hide();
        Toast.makeText(this, "" + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessNewuser(String userId, String nohp) {
        loading.hide();
        startActivity(new Intent(ActivityLogin.this, ActivityForm.class)
                .putExtra("title", "Daftar Akun")
                .putExtra("userId", "" + userId)
                .putExtra("noHp", "" + nohp)
        );
    }

    @Override
    public void onSuccessGenOTP(RestOTPGen restGenOTP) {
        try{
            loading.hide();
        }catch (Exception ex){}

        if(restGenOTP!=null){
            button_lanjut.setVisibility(View.GONE);
            layout_otp.setVisibility(View.VISIBLE);
            form_otp.requestFocus();
        }
    }

    @Override
    public void onSuccessLoginOTP(RestOTPLogin restOTPLogin) {
        if(restOTPLogin!=null){
            try{
                loading.show();
            }catch (Exception ex){}
            query.put("nohp",""+restOTPLogin.getNohp());
            dataUser.checkLogin(ActivityLogin.this, ActivityLogin.this, query, restOTPLogin.getUserId(), restOTPLogin.getNohp());
        }


    }

    @Override
    public void onErrorOtp(String from, String msg) {
        try{
            loading.hide();
        }catch (Exception ex){}

        if(from.equals("Koneksi")){
            Toast.makeText(ActivityLogin.this,"Koneksi Buruk Silahkan Coba Lagi",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(ActivityLogin.this,""+msg,Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        layout_login.setVisibility(View.VISIBLE);
        layout_otputama.setVisibility(View.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkUpdate();
    }
}
