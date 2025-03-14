package id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityMain;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendBatal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDetail;

public class ActivityDetailPendaftaran extends AppCompatActivity implements View.OnClickListener,ViewsPendDetail,ViewsPendBatal {
    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    private DataInfo dataInfo;
    private Button button_keluar;
    private Button button_batal;

    private TextView tv_norm;
    private TextView tv_tgllhr;
    private TextView tv_detail_tgl;
    private TextView tv_detail_pukul;
    private TextView tv_detail_poli;
    private TextView tv_detail_dokter;
    private TextView tv_detail_nobook;
    private TextView tv_detail_nourut;
    private TextView headline_text;
    private TextView tv_nama;
    private TextView tv_detail_nocs;
    private TextView txt_waktu_perkiraan;
    private ImageView iv_qrcode;

    private HashMap<String,String> queryLanjut= new HashMap<>();
    private HashMap<String,String> queryBatal= new HashMap<>();
    private AlertDialog.Builder noticebuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pendaftaran);
        init();
    }

    private void init(){
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        button_keluar         = (Button) findViewById(R.id.button_keluar);
        button_batal         = (Button) findViewById(R.id.button_batal);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        tv_norm        = (TextView) findViewById(R.id.tv_norm);
        tv_tgllhr        = (TextView) findViewById(R.id.tv_tgllhr);
        tv_detail_tgl        = (TextView) findViewById(R.id.tv_detail_tgl);
        tv_detail_nobook        = (TextView) findViewById(R.id.tv_detail_nobook);
        tv_detail_nourut        = (TextView) findViewById(R.id.tv_detail_nourut);
        tv_detail_poli        = (TextView) findViewById(R.id.tv_detail_poli);
        tv_detail_pukul        = (TextView) findViewById(R.id.tv_detail_pukul);
        tv_detail_dokter        = (TextView) findViewById(R.id.tv_detail_dokter);
        tv_detail_dokter        = (TextView) findViewById(R.id.tv_detail_dokter);
        tv_detail_nocs        = (TextView) findViewById(R.id.tv_detail_nocs);
        txt_waktu_perkiraan        = (TextView) findViewById(R.id.txt_waktu_perkiraan);

        headline_text        = (TextView) findViewById(R.id.headline_text);
        tv_nama        = (TextView) findViewById(R.id.tv_nama);
        iv_qrcode        = (ImageView) findViewById(R.id.iv_qrcode);
        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        dataInfo = new DataInfo();
        noticebuilder = new AlertDialog.Builder(this);
        title_bar.setText("Detail Pendaftaran");


        queryLanjut.put("kodePasien",""+session.getSessionUserPend().getkDPASIEN());
        queryLanjut.put("notrans",""+getIntent().getExtras().getString("notrans"));
        dataInfo.getPendaftaranDetail(this,this,queryLanjut);

        String[] tgllhrs=session.getSessionUserPend().gettGLLAHIR().split(" ");
        tv_norm.setText(""+session.getSessionUserPend().getkDPASIEN());
        tv_tgllhr.setText(""+tgllhrs[0]);
        tv_nama.setText(""+session.getSessionUserPend().getnAMAPASIEN());

        headline_text.setText(""+session.getSessionUserPend().getaLAMAT());
        headline_text.setSelected(true);
        if(session.getSessionBoolean("backDaftar",false)){
            button_keluar.setText("Selesai");
        }else{
            button_keluar.setText("Kembali");
        }
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.getSessionBoolean("backDaftar",false)){
//                    startActivity(new Intent(ActivityDetailPendaftaran.this,ActivityPendHome.class));
                    Intent intent=new Intent(ActivityDetailPendaftaran.this,ActivityMain.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    session.setSessionBoolean("backDaftar",false);
                }else{
                    finish();
                }
            }
        });

        button_keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(session.getSessionBoolean("backDaftar",false)){
                    Intent intent=new Intent(ActivityDetailPendaftaran.this,ActivityMain.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    session.setSessionBoolean("backDaftar",false);
                }else{
                    finish();
                }
            }
        });

        button_batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticebuilder.setTitle("Verifikasi")
                        .setCancelable(false)
                        .setMessage("Apakah Anda Yakin Membatalkan Pendaftaran?")
                        .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                batal();
                            }
                        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert11 = noticebuilder.create();
                alert11.show();
            }
        });


        //getIntent().getExtras().getString("title")
    }

    private void batal(){
        queryBatal.put("notrans",""+getIntent().getExtras().getString("notrans"));
        dataInfo.batalPendOnline(this,this,queryBatal);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSuccessPendDetail(RestDetailPendaftaran rest) {
        String[] tglrest=rest.getData().get(0).getfAPTGLAPPOITMENT().split(" ");
        /*tv_detail_tgl.setText("" +
                "\nTanggal\t\t\t: "+tglrest[0]
                +"\nPukul\t\t\t\t\t: "+rest.getData().get(0).getfAPJAMANTRI()
                +"\nPoli\t\t\t\t\t\t: "+rest.getData().get(0).getfMPKLINIKN()
                +"\nDokter\t\t\t\t: "+rest.getData().get(0).getfMDDOKTERN()
                +"\nNo.Booking\t: "+rest.getData().get(0).getfAPNOTRANSAKSI()
                +"\nNo.Urut\t\t\t\t: "+rest.getData().get(0).getfAPANTRIDOKTER()
        );*/

        tv_detail_tgl.setText(": "+tglrest[0]);
        tv_detail_pukul.setText(": "+rest.getData().get(0).getfAPJAMANTRI());
        tv_detail_poli.setText(": "+rest.getData().get(0).getfMPKLINIKN());
        tv_detail_dokter.setText(": "+rest.getData().get(0).getfMDDOKTERN());
        tv_detail_nobook.setText(": "+rest.getData().get(0).getfAPNOTRANSAKSI());
        tv_detail_nourut.setText(": "+rest.getData().get(0).getfAPANTRIDOKTER());
        if(rest.getData().get(0).getFAPANTRICS()!=null){
            tv_detail_nocs.setText(": "+rest.getData().get(0).getFAPANTRICS());
        }else{
            tv_detail_nocs.setText(": -");
        }


        txt_waktu_perkiraan.setText(""+rest.getData().get(0).getDesc());
//        String jamEstimasi=null;
//        jamEstimasi=rest.getData().get(0).getJamEstimasi2();
//        if(jamEstimasi!=null){
//
//            txt_waktu_perkiraan.setText("Perkiraan Anda Dilayani Pukul "+rest.getData().get(0).getJamEstimasi2()+". Dimohon datang sebelumnya. Antrian ini tidak dapat digunakan apabila anda datang lebih dari 15 menit dari perkiraan waktu pelayanan. Terima Kasih");
//
//        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(
                    ""+rest.getData().get(0).getfAPANTRIDOKTER()
                    , BarcodeFormat.QR_CODE
                    ,200
                    ,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            iv_qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorPendDetail(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessPendBatal(Rest rest) {
        Toast.makeText(this,rest.getMessage(),Toast.LENGTH_LONG).show();
        if(session.getSessionBoolean("backDaftar",false)){
            if(!session.getSessionUserPend().getkDPASIEN().trim().equals("-")){
                startActivity(new Intent(ActivityDetailPendaftaran.this,ActivityPendHome.class));
                session.setSessionBoolean("backDaftar",false);
            }else {
                startActivity(new Intent(ActivityDetailPendaftaran.this,ActivityPendTgl.class));
                session.setSessionBoolean("backDaftar",false);
            }
//            startActivity(new Intent(ActivityDetailPendaftaran.this,ActivityPendHome.class));
//            session.setSessionBoolean("backDaftar",false);


        }else{
            finish();
        }
    }

    @Override
    public void onErrorPendBatal(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(session.getSessionBoolean("backDaftar",false)){
            Intent intent=new Intent(ActivityDetailPendaftaran.this,ActivityMain.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            session.setSessionBoolean("backDaftar",false);
        }else{
            finish();
        }
    }
}
