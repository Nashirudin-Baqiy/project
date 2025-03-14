package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.DiaryBundaRJ;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRJ;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ActivityDiaryBundaRJ extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<DiaryBundaRJ> spaceprobeList = new ArrayList<>();
    private String[] spaceProbeHeaders={"No","Nama Poli","Staff","Tgl Periksa","No Transaksi"};
    private TableView<String[]> tableView;
    private String[][] spaceProbes;
    private TextView start_date;
    private TextView end_date;
    private Date now;
    private String start;
    private String end;
    private EditText search;
    private LinearLayout btn_search;
    private LinearLayout btn_clear;
    private LinearLayout btn_back;
    private TextView title_bar;
    private Loading loading;
    private Session session;
    private DialogAlerts dialogAlerts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_bunda_rj);
        init();
    }

    private void init(){
        tableView        = (TableView<String[]>) findViewById(R.id.tableView);
        btn_search       = (LinearLayout) findViewById(R.id.btn_search);
        btn_clear        = (LinearLayout) findViewById(R.id.btn_clear);
        search           = (EditText) findViewById(R.id.input_search);
        btn_back         = (LinearLayout) findViewById(R.id.btn_back);
        title_bar        = (TextView) findViewById(R.id.title_bar);
        loading          = new Loading(this);
        session             = new Session(this);
        dialogAlerts =new DialogAlerts(this,this);
        //SET PROP
        tableView.setHeaderBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(this,spaceProbeHeaders);
        headerAdapter.setTextSize(11);
        tableView.setColumnWeight(0,2);
        tableView.setColumnWeight(1,3);
        tableView.setColumnWeight(2,3);
        tableView.setColumnWeight(3,3);
        tableView.setColumnWeight(4,3);
        tableView.setHeaderAdapter(headerAdapter);

//        int colorEvenRows = getResources().getColor(R.color.colorLine);
//        int colorOddRows = getResources().getColor(R.color.colorGray2);
//        tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));
        tableView.setColumnCount(5);
        tableView.addDataClickListener(new TableDataClickListener() {
            @Override
            public void onDataClicked(int rowIndex, Object clickedData) {
                if(((String[])clickedData)[4].equals("Total")){
                    Toast.makeText(ActivityDiaryBundaRJ.this, "Total = "+((String[])clickedData)[5]+" "+((String[])clickedData)[6]+" "+((String[])clickedData)[7], Toast.LENGTH_SHORT).show();
                }else {
//                    RawatJalan rawatJalan = new RawatJalan();
//                    rawatJalan.setId(((String[])clickedData)[1]);
//                    rawatJalan.setPasien(((String[])clickedData)[2]);
//                    rawatJalan.setDokter(((String[])clickedData)[3]);
//                    rawatJalan.setNamaRuangan(((String[])clickedData)[4]);
//                    rawatJalan.setTglMasuk(((String[])clickedData)[5]);
//                    popup(rawatJalan);
                    dialogAlerts.CreateDialogAlertsPositive("Detail Riwayat"
                    ,""
                                    +"\n "+((String[])clickedData)[1]
                                    +"\n "+((String[])clickedData)[2]
                                    +"\n "+((String[])clickedData)[3]
                                    +"\n "+((String[])clickedData)[4]
                    );
//                    Toast.makeText(ActivityDiaryBundaRJ.this, ((String[])clickedData)[1]+" "+((String[])clickedData)[2].substring(0,11)+" "+((String[])clickedData)[3]+" "+((String[])clickedData)[4]+" "+ ((String[])clickedData)[5]+" "+((String[])clickedData)[6]+" "+((String[])clickedData)[7], Toast.LENGTH_SHORT).show();
                }
            }
        });

//        Bundle bundle = getIntent().getExtras().getBundle("data");
//        start         = getIntent().getExtras().getString("start_date");
//        end           = getIntent().getExtras().getString("end_date");
        title_bar.setText("Rekam Medisku Rawat Jalan");
//        kode_ruang    = bundle.getString("koderuang");


        btn_clear.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        if(session.getSessionUser().getNORM()!=null
                && !session.getSessionUser().getNORM().equals("")){
            getData(session.getSessionUser().getNORM());
        }else{
            Toast.makeText(this,"Silahkan Isi No RM dengan mengedit Akun Anda",Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(ActivityDiaryBundaRJ.this,ActivityMain.class));
        }



        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<DiaryBundaRJ> lisSearch = getSearch(s);
                spaceProbes= new String[lisSearch.size()+1][11];
                Integer sum=0;
                Integer sumbpjs=0;
                for (int i=0;i<lisSearch.size();i++) {
                    DiaryBundaRJ diaryBundaRJ1=lisSearch.get(i);
                    spaceProbes[i][0]=Integer.toString(i+1);
                    spaceProbes[i][1]= diaryBundaRJ1.getfMPKLINIKN();
                    spaceProbes[i][2]= diaryBundaRJ1.getfMDDOKTERN();
                    spaceProbes[i][3]= diaryBundaRJ1.getkPTGLPERIKSA();
                    spaceProbes[i][4]= diaryBundaRJ1.getkPNOTRANSAKSI();

                }

                SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(ActivityDiaryBundaRJ.this, spaceProbes);
                dataAdapter.setTextSize(11);
                tableView.setDataAdapter(dataAdapter);
            }



            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    private void sorting(ArrayList<DiaryBundaRJ> data){
        Collections.sort(data, new Comparator<DiaryBundaRJ>() {
            @Override
            public int compare(DiaryBundaRJ o1, DiaryBundaRJ o2) {
                return o1.getkPTGLPERIKSA().compareTo(o2.getkPTGLPERIKSA());
            }
        });
    }

    private ArrayList<DiaryBundaRJ> getListKelas(){
        ArrayList<DiaryBundaRJ> listKelas = new ArrayList<>();
        for (int i = 0; i < spaceprobeList.size() ; i++) {
            if(i<spaceprobeList.size()-1){
                if(!spaceprobeList.get(i).getkPNOTRANSAKSI().equals(spaceprobeList.get(i+1).getkPNOTRANSAKSI())){
                    listKelas.add(spaceprobeList.get(i));
                }
            }else{
                listKelas.add(spaceprobeList.get(i));
            }
        }
        return listKelas;
    }

    private ArrayList<DiaryBundaRJ> getSearch(CharSequence search){
        ArrayList<DiaryBundaRJ> data = getListKelas();
        if(search.equals("") || search.equals(" ")){
            return data;
        }else {
            ArrayList<DiaryBundaRJ> listSearch = new ArrayList<>();
            for (int i = 0; i < data.size() ; i++) {
                if(i<data.size()){
                    if(data.get(i).getfMPKLINIKN().toLowerCase().contains(search.toString().toLowerCase())){
                        listSearch.add(data.get(i));
                    }
                }
            }
            return listSearch;
        }
    }


    private void getData(String noRM){
        final HashMap<String, String> query = new HashMap<>();
        loading.show();
        spaceprobeList.clear();
        query.put("noRm",noRM);
        InterfaceInfo interfaceInfo             = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestDiaryBundaRJ> observable = interfaceInfo.getDiaryBundaRJ(query);
        observable.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestDiaryBundaRJ>() {
                    @Override
                    public void onNext(RestDiaryBundaRJ restInfoBpjs) {
                        spaceprobeList.addAll(restInfoBpjs.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ActivityDiaryBundaRJ.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        Snackbar.make(findViewById(R.id.content),"Koneksi Buruk...",Snackbar.LENGTH_SHORT).show();
                        loading.hide();
                    }

                    @Override
                    public void onComplete() {
                        sorting(spaceprobeList);
                        ArrayList<DiaryBundaRJ> listPenyakit = getListKelas();
//                ArrayList<RawatInap> listKelas = getListKelas();
                        Integer sum=0;
                        Integer sumbpjs=0;
                        spaceProbes= new String[listPenyakit.size()+1][11];
                        for (int i=0;i<listPenyakit.size();i++) {
                            DiaryBundaRJ diaryBundaRJ1 =listPenyakit.get(i);
                            spaceProbes[i][0]=Integer.toString(i+1);
                            spaceProbes[i][1]= diaryBundaRJ1.getfMPKLINIKN();
                            spaceProbes[i][2]= diaryBundaRJ1.getfMDDOKTERN();
                            spaceProbes[i][3]= diaryBundaRJ1.getkPTGLPERIKSA();

                            spaceProbes[i][4]= diaryBundaRJ1.getkPNOTRANSAKSI();

                        }

                        SimpleTableDataAdapter dataAdapter = new SimpleTableDataAdapter(ActivityDiaryBundaRJ.this, spaceProbes);
                        dataAdapter.setTextSize(11);
                        tableView.setDataAdapter(dataAdapter);
                        loading.hide();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        loading.hide();
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back  : finish();
                break;
            case R.id.btn_clear : search.setText("");
                break;
        }
    }
}
