package id.co.rsnasionaldiponegoro.epublic.Views.Fragment;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Info;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHeadline;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLayananBantuan;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestVersion;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DialogAlerts;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityAntrian;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityBunda;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityBundaDiary;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityDetailInfo;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityEmergency;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityJadwalLayanan;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityHistoryPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Pendaftaran.ActivityPendLogin;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityArticleList;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.ActivityTelemedicineDashboard;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsBanner;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsHeadline;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import libs.mjn.prettydialog.PrettyDialog;
import libs.mjn.prettydialog.PrettyDialogCallback;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.events.OnBannerClickListener;
import ss.com.bannerslider.views.BannerSlider;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements View.OnClickListener, ViewsBanner, ViewsHeadline {
    private View view;
    private BannerSlider slider;
    private TextView btn_hub;
    private TextView headline_text;
    private TextView pbh_user_account;
    private LinearLayout button_bunda;
    private LinearLayout button_help;
    private LinearLayout button_pendaftaran;
    private LinearLayout button_jadwallayanan;
    private LinearLayout button_emergency;
    private LinearLayout button_promo;
    private LinearLayout button_bundacare;
    private LinearLayout button_diarycare;
    private LinearLayout button_tanyabunda;
    private LinearLayout button_fasilitas;
    private LinearLayout button_antrian;
    private LinearLayout button_ticketing;
    private LinearLayout button_tm;
    private LinearLayout button_lu;
    private LinearLayout button_suara_konsumen;
    private ArrayList<Info> arrayListInfoBanner = new ArrayList<>();
    private DataInfo dataInfo;
    final HashMap<String, String> query = new HashMap<>();
    private Session session;
    private DialogAlerts dialogAlerts;

    public FragmentHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        loadDataLayananBantuan();
//         setSlider();
        return view;
    }

    private void init() {
        slider = view.findViewById(R.id.banner);
        button_suara_konsumen = view.findViewById(R.id.button_suara_konsumen);
        pbh_user_account = view.findViewById(R.id.pbh_user_account);
        headline_text = view.findViewById(R.id.headline_text);
        button_bunda = view.findViewById(R.id.button_bunda);
        button_pendaftaran = view.findViewById(R.id.button_pendaftaran);
        button_jadwallayanan = view.findViewById(R.id.button_jadwallayanan);
        button_emergency = view.findViewById(R.id.button_emergency);
        button_promo = view.findViewById(R.id.button_promo);
        button_bundacare = view.findViewById(R.id.button_bundacare);
        button_diarycare = view.findViewById(R.id.button_diarycare);
        button_tanyabunda = view.findViewById(R.id.button_tanyabunda);
        button_help = view.findViewById(R.id.button_help);
        button_fasilitas = view.findViewById(R.id.button_fasilitas);
        button_antrian = view.findViewById(R.id.button_antrian);
        btn_hub = view.findViewById(R.id.btn_hub);
        button_ticketing = view.findViewById(R.id.button_ticketing);
        button_tm = view.findViewById(R.id.button_tm);
        button_lu = view.findViewById(R.id.button_lu);
        session = new Session(getActivity());

        dataInfo = new DataInfo();
        String nama = session.getSessionUser().getNAMA();
        if (nama.length() < 9) {
            pbh_user_account.setText("Hai," + nama + "");
        } else {
            pbh_user_account.setText("Hai," + nama.substring(0, 9) + "");
        }

//        bannerData();
        button_suara_konsumen.setOnClickListener(this);
        button_bunda.setOnClickListener(this);
        button_ticketing.setOnClickListener(this);
        button_pendaftaran.setOnClickListener(this);
        button_jadwallayanan.setOnClickListener(this);
        button_emergency.setOnClickListener(this);
        button_promo.setOnClickListener(this);
        button_bundacare.setOnClickListener(this);
        button_diarycare.setOnClickListener(this);
        button_tanyabunda.setOnClickListener(this);
        button_help.setOnClickListener(this);
        button_fasilitas.setOnClickListener(this);
        button_antrian.setOnClickListener(this);
        button_tm.setOnClickListener(this);
        button_lu.setOnClickListener(this);
        dialogAlerts = new DialogAlerts(getActivity(),getActivity());
    }

    private void setSlider() {
        List<Banner> list = new ArrayList<>();
        list.add(new RemoteBanner("http://www.bundasemarang.co.id/cni-content/uploads/modules/slider/20141104112305.jpg"));
        list.add(new RemoteBanner("http://seputarsemarang.com/images/2011/05/RSB_Bunda_semarang.jpg"));
        list.add(new RemoteBanner("https://i1.wp.com/portalsemarang.com/wp-content/uploads/2011/08/bunda-semarang.jpg?resize=300%2C181"));
//
        slider.setBanners(list);
        slider.setIndicatorSize(1);
        slider.setInterval(10000);
        slider.setCurrentSlide(0);
        slider.setLoopSlides(true);
        slider.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void onClick(int position) {
//                String id = ""+arrayListInfo.get(position).getId();
                Intent myIntent = new Intent(getActivity(), ActivityDetailInfo.class);
//                myIntent.putExtra("title", ""+arrayListInfo.get(position).getJudul());
//                myIntent.putExtra("desc", ""+arrayListInfo.get(position).getDeskripsi());
//                myIntent.putExtra("img", ""+arrayListInfo.get(position).getImageFull());
//                myIntent.putExtra("id", ""+arrayListInfo.get(position).getId());
//                myIntent.putExtra("tgl", ""+arrayListInfo.get(position).getUpdatedAt().getDate());
                myIntent.putExtra("title", "Judul Article");
                myIntent.putExtra("desc", "");
                myIntent.putExtra("img", "");
//                myIntent.putExtra("id", ""+arrayListInfo.get(position).getId());
                myIntent.putExtra("tgl", "");
                getActivity().startActivity(myIntent);
            }
        });


    }

    private void sendWa(Context context, String phone, String message) {
        PackageManager packageManager = context.getPackageManager();
        Intent i = new Intent(Intent.ACTION_VIEW);
        try {
            String url = "https://api.whatsapp.com/send?phone="+ phone +"&text=" + URLEncoder.encode(message, "UTF-8");
//                    Log.e("url",url);
            i.setPackage("com.whatsapp");
            i.setData(Uri.parse(url));
            if (i.resolveActivity(packageManager) != null) {
                startActivity(i);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private void loadDataLayananBantuan() {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestLayananBantuan> bannerObservable = interfaceInfo.getLayananBantuan();
        bannerObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestLayananBantuan>() {
            @Override
            public void onNext(RestLayananBantuan restLayananBantuan) {
               setLayananBantuan(restLayananBantuan);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private void checkUpdate() {
        try{
            InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
            Observable<RestVersion> bannerObservable = interfaceInfo.getVersion();
            bannerObservable.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestVersion>() {
                @Override
                public void onNext(RestVersion restVersion) {
                    if(restVersion.getSuccess()){
                        session.setSessionString("csWAMedis",restVersion.getCsWAMedis());
                        session.setSessionString("csWANonMedis",restVersion.getCsWANonMedis());
                        setcheckUpdate(restVersion);
                    }
                }

                @Override
                public void onError(Throwable e) {

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
    private void setLayananBantuan(final RestLayananBantuan restLayananBantuan){
       try{
           btn_hub.setText(restLayananBantuan.getTitle()+" "+restLayananBantuan.getTelp());
           btn_hub.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   telepon(restLayananBantuan.getTelp());
               }
           });
       }catch (Exception ex){}
    }

    private void telepon(String telp) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+telp));
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);

        }
        else
        {
            startActivity(callIntent);
        }


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_ticketing:
                new PrettyDialog(getActivity())
                        .setTitle("CheckIn Perndaftaran")
                        .setIcon(R.drawable.ticket_booking)
                        .addButton(
                                "Pasien Lama",     // button text
                                R.color.colorWhite,  // button text color
                                R.color.colorPrimary,  // button background color
                                new PrettyDialogCallback() {  // button OnClick listener
                                    @Override
                                    public void onClick() {
                                        // Do what you gotta do
                                        if(session.getSessionUser().getNORM()!=null && !session.getSessionUser().getNORM().trim().equals("0")){
                                            startActivity(new Intent(getActivity(), ActivityHistoryPendaftaran.class)
                                                    .putExtra("title", "CheckIn Perndaftaran")
                                                    .putExtra("pasien","lama") );
                                        }else{
                                            Toast.makeText(getActivity(),"Silahkan Daftarakan NoCM dan Tgl-Lahir Anda",Toast.LENGTH_LONG).show();
//                                            startActivity(new Intent(getActivity(),ActivityEditProfile.class).putExtra("title","Edit Profil"));
                                        }


                                    }
                                }
                        )
                        .addButton(
                                "Pasien Baru",
                                R.color.colorWhite,
                                R.color.colorPrimary,
                                new PrettyDialogCallback() {
                                    @Override
                                    public void onClick() {
                                        // Dismiss
                                        startActivity(new Intent(getActivity(), ActivityHistoryPendaftaran.class)
                                                .putExtra("title", "CheckIn Perndaftaran")
                                                .putExtra("pasien","baru"));
                                    }
                                }
                        )
                        .show();
//                new PrettyDialog(getActivity())
//                        .setTitle("Tiket Booking")
//                        .setIcon(R.drawable.ticket_booking)
//                        .addButton(
//                                "Pasien Lama",     // button text
//                                R.color.colorWhite,  // button text color
//                                R.color.colorPrimary,  // button background color
//                                new PrettyDialogCallback() {  // button OnClick listener
//                                    @Override
//                                    public void onClick() {
//                                        // Do what you gotta do
//                                        session.setSessionBoolean("listbook",true);
//                                        getActivity().startActivity(new Intent(getActivity(), ActivityBookingLogin.class));
//
//
//                                    }
//                                }
//                        )
//                        .addButton(
//                                "Pasien Baru",
//                                R.color.colorWhite,
//                                R.color.colorPrimary,
//                                new PrettyDialogCallback() {
//                                    @Override
//                                    public void onClick() {
//                                        // Dismiss
//                                        session.setSessionBoolean("listbook",false);
//
//                                        startActivity(new Intent(getActivity(),ActivityBookingListBaru.class));
//                                    }
//                                }
//                        )
//                        .show();

                break;
            case R.id.button_bunda:
                getActivity().startActivity(new Intent(getActivity(), ActivityBunda.class));
                break;
            case R.id.button_pendaftaran:
                getActivity().startActivity(new Intent(getActivity(), ActivityPendLogin.class));
//                getActivity().startActivity(new Intent(getActivity(), ActivityPendaftaranWeb.class));
                break;
            case R.id.button_jadwallayanan:
                getActivity().startActivity(new Intent(getActivity(), ActivityJadwalLayanan.class));
                break;
            case R.id.button_emergency:
                getActivity().startActivity(new Intent(getActivity(), ActivityEmergency.class));
                break;
            case R.id.button_help:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "Help"));
                break;
            case R.id.button_fasilitas:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "Fasilitas"));
                break;
            case R.id.button_lu:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "Layanan Unggulan"));
                break;
            case R.id.button_promo:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "Promo"));
                break;
            case R.id.button_bundacare:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "RS Care"));
                break;
            case R.id.button_diarycare:
                getActivity().startActivity(new Intent(getActivity(), ActivityBundaDiary.class)
                        .putExtra("title", "Riwayat Kunjungan"));
                break;
            case R.id.button_tanyabunda:
                new PrettyDialog(getActivity())
                        .setTitle("Tanya RS")
                        .setIcon(R.drawable.ic_tanyabunda)

                        .addButton(
                                "Layanan Umum",
                                R.color.colorWhite,
                                R.color.colorPrimary,
                                new PrettyDialogCallback() {
                                    @Override
                                    public void onClick() {
                                        // Dismiss
                                        String messages2="#TanyaLayananUmum ";
                                        sendWa(getActivity(),session.getSessionString("csWANonMedis",""),messages2);
                                    }
                                }
                        )
                        .addButton(
                                "Telp Customer Service",     // button text
                                R.color.colorWhite,  // button text color
                                R.color.colorPrimary,  // button background color
                                new PrettyDialogCallback() {  // button OnClick listener
                                    @Override
                                    public void onClick() {
                                        // Do what you gotta do
                                        String messages1="#TanyaLayananKesehatan ";
                                        telepon("+622476928020");
//                                    sendWa(getActivity(),session.getSessionString("csWAMedis",""),messages1);
                                    }
                                }
                        )
                        .show();
//                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
//                        .putExtra("title", "Tanya RS"));
//                String phones=session.getSessionString("csWa","");
//                String messages="#TanyaRSHarapanIbu";
//                sendWa(getActivity(),phones,messages);
                break;
            case R.id.button_antrian:
                getActivity().startActivity(new Intent(getActivity(), ActivityAntrian.class)
                        .putExtra("title", "Antrian"));
                break;
            case R.id.button_suara_konsumen:
                getActivity().startActivity(new Intent(getActivity(), ActivityArticleList.class)
                        .putExtra("title", "Suara Konsumen"));
                break;
            case R.id.button_tm:

                getActivity().startActivity(new Intent(getActivity(), ActivityTelemedicineDashboard.class)
                        .putExtra("title", "TeleMedicine"));

//                new PrettyDialog(getActivity())
//                        .setTitle("ComingSoon")
//                        .setIcon(R.drawable.ic_tanyabunda)
//                        .show();
                break;
        }
    }

    @Override
    public void onSuccessBanner(RestInfo restInfo) {
        if (restInfo != null) {
            List<Banner> list = new ArrayList<>();
            arrayListInfoBanner = restInfo.getData();
            if (restInfo.getData().size() > 0) {

                for (int i = 0; i < arrayListInfoBanner.size(); i++) {
                    list.add(new RemoteBanner(arrayListInfoBanner.get(i).getTHUMB_GAMBAR()));
                }

                slider.setBanners(list);
                slider.setIndicatorSize(25);
                slider.setInterval(10000);
                slider.setCurrentSlide(0);
                slider.setLoopSlides(true);
                slider.setOnBannerClickListener(new OnBannerClickListener() {
                    @Override
                    public void onClick(int position) {
                        Intent myIntent = new Intent(getActivity(), ActivityDetailInfo.class);
                        myIntent.putExtra("title", "" + arrayListInfoBanner.get(position).getNAMA());
                        myIntent.putExtra("desc", "" + arrayListInfoBanner.get(position).getDESKRIPSI());
                        myIntent.putExtra("img", "" + arrayListInfoBanner.get(position).getFULL_GAMBAR());
                        getActivity().startActivity(myIntent);
                    }
                });
            }
        }
    }

    @Override
    public void onErrorBanner(RestInfo restInfo, String msg) {

    }

    @Override
    public void onSuccessHeadline(RestHeadline restHeadline) {
        String isi_headline = "";
        if (restHeadline != null) {
            if (restHeadline.getCount() > 0) {
                for (int i = 0; i < restHeadline.getHeadline().size(); i++) {
                    if (i == 0) {
                        isi_headline = restHeadline.getHeadline().get(i).getISI();
                    } else {
                        isi_headline = isi_headline + " | " + restHeadline.getHeadline().get(i).getISI();
                    }
                }
            } else {
                headline_text.setSelected(true);
                headline_text.setText("");

            }
            headline_text.setText("" + isi_headline);
            headline_text.setSelected(true);
        }
    }

    @Override
    public void onErrorHeadline(RestHeadline restHeadline, String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        dataInfo.getBanner(getActivity(), this);
        dataInfo.getHeadline(getActivity(), this, query);
        checkUpdate();
    }
}
