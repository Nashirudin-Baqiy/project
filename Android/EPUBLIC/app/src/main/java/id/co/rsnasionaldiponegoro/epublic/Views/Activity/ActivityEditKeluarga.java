package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Adapter.AdapterKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKeluarga;

public class ActivityEditKeluarga extends AppCompatActivity  implements View.OnClickListener,ViewsKeluarga{
//
    private LinearLayout btn_back;
    private TextView title_bar;
    private TextView layout_nodata;
    private Button button_add_keluarga;
    private RecyclerView recycler_view;
    private Session session;

    private HashMap<String, String> dataString = new HashMap<String, String>();
    private HashMap<String, String> dataStringDelete = new HashMap<String, String>();
    private HashMap<String, String> dataStringAdd = new HashMap<String, String>();
    private DatePicker datePicker = new DatePicker();
    private DataInfo dataInfo;
    private ArrayList<Keluarga> arrayListKeluargas = new ArrayList<>();
    private String noCm;
    private String tglLhr;
    private AdapterKeluarga adapterKeluarga;
    private AlertDialog.Builder noticebuilder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_keluarga);   init();
    }

    private void init(){
        noticebuilder               = new AlertDialog.Builder(this);
        btn_back                    = findViewById(R.id.btn_back);
        title_bar                   = findViewById(R.id.title_bar);
        layout_nodata                   = findViewById(R.id.layout_nodata);
        button_add_keluarga         = findViewById(R.id.button_add_keluarga);
        recycler_view               = findViewById(R.id.recycler_view);
        session                     = new Session(this);
        dataInfo = new DataInfo();

        recycler_view.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapterKeluarga = new AdapterKeluarga(ActivityEditKeluarga.this,arrayListKeluargas,this);
        recycler_view.setAdapter(adapterKeluarga);
        title_bar.setText(getIntent().getExtras().getString("title"));
        btn_back.setOnClickListener(this);
        button_add_keluarga.setOnClickListener(this);

        dataString.put("idKeluarga",""+session.getSessionUser().getNORM());
        dataString.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
        dataInfo.listKeluarga(ActivityEditKeluarga.this,null,this,dataString);
    }

    private void popup_keluarga(){
        LayoutInflater layoutInflater = LayoutInflater.from(ActivityEditKeluarga.this);
        View view                     = layoutInflater.inflate(R.layout.popup_keluarga_add,null);
        AlertDialog.Builder adb       = new AlertDialog.Builder(ActivityEditKeluarga.this);
        adb.setView(view);

        final EditText keluarga_tgl_value          = view.findViewById(R.id.keluarga_tgl_value);
        final EditText keluarga_nocm_value          = view.findViewById(R.id.keluarga_nocm_value);
        keluarga_tgl_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker.setViewTimePickers(ActivityEditKeluarga.this,keluarga_tgl_value);
                DialogFragment newFragment = datePicker;
                newFragment.show(getFragmentManager(),"datepicker");
            }
        });
        DialogInterface.OnClickListener clickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(keluarga_tgl_value.getText().toString().trim().length()!=0 &&
                        keluarga_nocm_value.getText().toString().trim().length()!=0){

                    cekValidasi(keluarga_nocm_value.getText().toString(),keluarga_tgl_value.getText().toString());
                }else{
                    Toast.makeText(ActivityEditKeluarga.this,"Silahkan Isi NoCM dan Tgl-Lahir Anda",Toast.LENGTH_LONG).show();
                }
            }
        };

        adb.setPositiveButton("Simpan",clickListener);
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }

    private void cekValidasi(String nocm,String tgl){
        noCm=nocm;
        tglLhr=tgl;

        dataStringAdd.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
        dataStringAdd.put("nocm",""+noCm);
        dataStringAdd.put("tgllhr",""+tglLhr);
        dataStringAdd.put("idKeluarga",""+session.getSessionUser().getNORM());
        dataStringAdd.put("status","0");
        dataInfo.addKeluarga(this,null,this,dataStringAdd);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back  : finish();
                break;
            case R.id.button_add_keluarga  : popup_keluarga();
                break;
        }
    }

    @Override
    public void onSuccessGetKeluarga(ArrayList<Keluarga> arrayListKeluarga) {
        layout_nodata.setVisibility(View.GONE);
        arrayListKeluargas.clear();
        adapterKeluarga.notifyDataSetChanged();
        arrayListKeluargas.addAll(arrayListKeluarga);
        recycler_view.setAdapter(adapterKeluarga);
        adapterKeluarga.notifyDataSetChanged();
    }

    @Override
    public void onErrorGetKeluarga(int num_data) {
        if(num_data==0){
            layout_nodata.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSuccessAddKeluarga(String msg) {
        Toast.makeText(ActivityEditKeluarga.this,msg,Toast.LENGTH_LONG).show();
        dataString.put("idKeluarga",""+session.getSessionUser().getNORM());
        dataString.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
        dataInfo.listKeluarga(ActivityEditKeluarga.this,null,this,dataString);
    }

    @Override
    public void onErrorAddKeluarga(String msg) {
        Toast.makeText(ActivityEditKeluarga.this,msg,Toast.LENGTH_LONG).show();
    }



    @Override
    public void onHapusKeluarga(final Keluarga keluarga) {
        noticebuilder.setTitle("")
                .setCancelable(false)
                .setMessage("Hapus  "+keluarga.getNamaKeluarga()+"?")
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dataStringDelete.clear();
                        dataStringDelete.put("nocm",""+keluarga.getNocm());
                        dataStringDelete.put("idKeluarga",""+session.getSessionUser().getNORM());
                        dataStringDelete.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
                        dataInfo.deleteKeluarga(ActivityEditKeluarga.this,dataStringDelete);
                    }
                }).setNegativeButton("Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alert11 = noticebuilder.create();
        alert11.show();
    }

    @Override
    public void onSuccessHapus() {
        arrayListKeluargas.clear();
        adapterKeluarga.notifyDataSetChanged();
        dataString.put("idKeluarga",""+session.getSessionUser().getNORM());
        dataString.put("idUserPrimer",""+session.getSessionUser().getIDUSERS());
        dataInfo.listKeluarga(ActivityEditKeluarga.this,null,this,dataString);
        Toast.makeText(ActivityEditKeluarga.this,"Success Hapus ",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onErrorHapus(String msg) {
        Toast.makeText(ActivityEditKeluarga.this,"Jaringan Bermasalah ",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onErrorValid() {
        Toast.makeText(ActivityEditKeluarga.this,"NoCM tidak ada silahkan cek kembali ",Toast.LENGTH_LONG).show();
    }
}
