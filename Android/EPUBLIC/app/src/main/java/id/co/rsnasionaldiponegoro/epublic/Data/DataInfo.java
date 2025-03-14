package id.co.rsnasionaldiponegoro.epublic.Data;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfacePendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestContact;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRJ;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestEmergency;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHeadline;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHistory;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestListKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsAntrian;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsArtikel;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsBanner;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsContact;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsDiaryBunda;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsEmergency;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsGetTanggal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsHeadline;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsHistory;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsKritikSaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendBatal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDetail;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDokter;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendPoli;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendSend;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPromo;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xsanz on 9/25/2018.
 */

public class DataInfo {
    public void getEmergency(final Activity activity, final ViewsEmergency viewsEmergency) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestEmergency> observable = interfaceInfo.getEmergency();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestEmergency>() {
                    @Override
                    public void onNext(RestEmergency restEmergency) {
                        if (restEmergency.getSuccess()) {
                            viewsEmergency.onSuccessEmergency(restEmergency);
                        } else {
                            viewsEmergency.onErrorEmergency(restEmergency, restEmergency.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsEmergency.onErrorEmergency(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getHeadline(final Activity activity, final ViewsHeadline viewsHeadline, final HashMap<String, String> query) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestHeadline> observable = interfaceInfo.getHeadline(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestHeadline>() {
                    @Override
                    public void onNext(RestHeadline rest) {
                        if (rest.getSuccess()) {
                            viewsHeadline.onSuccessHeadline(rest);
                        } else {
                            viewsHeadline.onErrorHeadline(rest, rest.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsHeadline.onErrorHeadline(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getContact(final Activity activity, final ViewsContact viewsContact) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestContact> observable = interfaceInfo.getContact();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestContact>() {
                    @Override
                    public void onNext(RestContact rest) {
                        if (rest.getSuccess()) {
                            viewsContact.onSuccessContact(rest);
                        } else {
                            viewsContact.onErrorContact(rest, rest.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsContact.onErrorContact(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getHistory(final Activity activity, final ViewsHistory viewsHistory) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestHistory> observable = interfaceInfo.getHistory();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestHistory>() {
                    @Override
                    public void onNext(RestHistory rest) {
                        if (rest.getSuccess()) {
                            viewsHistory.onSuccessHistory(rest);
                        } else {
                            viewsHistory.onErrorHistory(rest, rest.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsHistory.onErrorHistory(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void getBanner(final Activity activity, final ViewsBanner viewsBanner) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestInfo> observable = interfaceInfo.getInfoBanner();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestInfo>() {
                    @Override
                    public void onNext(RestInfo rest) {
                        if (rest.getSuccess()) {
                            viewsBanner.onSuccessBanner(rest);
                        } else {
                            viewsBanner.onErrorBanner(rest, rest.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsBanner.onErrorBanner(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void getInfo(final Activity activity, final ViewsPromo viewsPromo) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestInfo> observable = interfaceInfo.getInfoPromo();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestInfo>() {
                    @Override
                    public void onNext(RestInfo rest) {
                        if (rest.getSuccess()) {
                            viewsPromo.onSuccessPromo(rest);
                        } else {
                            viewsPromo.onErrorPromo(rest, rest.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPromo.onErrorPromo(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getArtikel(final Activity activity, final ViewsArtikel viewsArtikel, final HashMap<String, String> query ) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestArtikel> observable = interfaceInfo.getArtikelParent(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestArtikel>() {
                    @Override
                    public void onNext(RestArtikel rest) {
                        if (rest.getSuccess()) {
                            viewsArtikel.onSuccessArtikel(rest);
                        } else {
                            viewsArtikel.onErrorArtikel(rest, "no success");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsArtikel.onErrorArtikel(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getArtikelChildren(final Activity activity, final ViewsArtikel viewsArtikel, final HashMap<String, String> query ) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestArtikel> observable = interfaceInfo.getArtikelChildren(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestArtikel>() {
                    @Override
                    public void onNext(RestArtikel rest) {
                        if (rest.getSuccess()) {
                            viewsArtikel.onSuccessArtikel(rest);
                        } else {
                            viewsArtikel.onErrorArtikel(rest, "no success");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsArtikel.onErrorArtikel(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }




    public void getKritikSaran(final Activity activity, final ViewsKritikSaran viewsKritikSaran, final HashMap<String, String> query ) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestKritikSaran> observable = interfaceInfo.getKritikSaran(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestKritikSaran>() {
                    @Override
                    public void onNext(RestKritikSaran rest) {
                        if (rest.getSuccess()) {
                            viewsKritikSaran.onSuccessLoadKritik(rest);
                        } else {
                            viewsKritikSaran.onErrorLoadKritik(rest.getMessage());

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsKritikSaran.onErrorLoadKritik(e.getMessage());
//                            viewsKritikSaran.onErrorPromo(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void addKritikSaran(final Activity activity, final ViewsKritikSaran viewsKritikSaran, final HashMap<String, String> query ) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestKritikSaran> observable = interfaceInfo.addKritikSaran(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestKritikSaran>() {
                    @Override
                    public void onNext(RestKritikSaran rest) {
                        if (rest.getSuccess()) {
                            viewsKritikSaran.onSuccessLoadKritik(rest);
                        } else {
                            viewsKritikSaran.onErrorLoadKritik( "no success");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsKritikSaran.onErrorLoadKritik( e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getDiaryBundaRJ(final Activity activity, final ViewsDiaryBunda viewsDiaryBunda, final HashMap<String, String> query ) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestDiaryBundaRJ> observable = interfaceInfo.getDiaryBundaRJ(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestDiaryBundaRJ>() {
                    @Override
                    public void onNext(RestDiaryBundaRJ rest) {
                        if (rest.getCount()>0) {
                            viewsDiaryBunda.onSuccessDiaryBundaRJ(rest);
                        } else {
                            viewsDiaryBunda.onSuccessDiaryBundaError( "no success");
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsDiaryBunda.onSuccessDiaryBundaError( "no success");
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getAntrianCS(final Activity activity, final ViewsAntrian viewsAntrian) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestAntrianCS> observable = interfaceInfo.getAntrianCs();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestAntrianCS>() {
                    @Override
                    public void onNext(RestAntrianCS rest) {
                        if (rest.getSuccess()) {
                            viewsAntrian.onSuccessAntrianCS(rest);
                        } else {
                            viewsAntrian.onErrorAntrianCS("0");
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsAntrian.onErrorAntrianCS(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAntrianApotik(final Activity activity, final ViewsAntrian viewsAntrian) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestAntrianApotik> observable = interfaceInfo.getAntrianApotik();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestAntrianApotik>() {
                    @Override
                    public void onNext(RestAntrianApotik rest) {
                        if (rest.getSuccess()) {
                            viewsAntrian.onSuccessAntrianApotik(rest);
                        } else {
                            viewsAntrian.onErrorAntrianApotik("0");
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsAntrian.onErrorAntrianCS(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getAntrianPoli(final Activity activity, final ViewsAntrian viewsAntrian) {
        InterfaceInfo interfaceInfo = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestAntrianPoli> observable = interfaceInfo.getAntrianPoli();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestAntrianPoli>() {
                    @Override
                    public void onNext(RestAntrianPoli rest) {
                        if (rest.getAntrianPoli().size()>0) {
                            viewsAntrian.onSuccessAntrianPoli(rest);
                        } else {
                            viewsAntrian.onErrorAntrianPoli(""+rest.getAntrianPoli().size());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsAntrian.onErrorAntrianPoli(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //Pendaftaran

    public void loginPendaftaran(final Activity activity, final ViewsPendaftaranLogin viewsPendaftaranLogin, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranLogin> observable = interfacePendaftaran.checkUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranLogin>() {
                    @Override
                    public void onNext(RestPendaftaranLogin restPendaftaranLogin) {
                        if (restPendaftaranLogin.getSuccess()) {
                            viewsPendaftaranLogin.onSuccessLogin(restPendaftaranLogin);
                        } else {
                            viewsPendaftaranLogin.onErrorLogin(""+restPendaftaranLogin.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendaftaranLogin.onErrorLogin(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getListAsuransi(final Activity activity, final ViewsGetTanggal viewsGetTanggal) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPenjaminAsuransi> observable = interfacePendaftaran.getPenjaminAsuransi();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPenjaminAsuransi>() {
                    @Override
                    public void onNext(RestPenjaminAsuransi restPenjaminAsuransi) {
                        if (restPenjaminAsuransi.getSuccess()) {
                            viewsGetTanggal.onLoadAsuransi(restPenjaminAsuransi);
                        } else {
                            viewsGetTanggal.onErrorLoadAsuransi("0");
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsGetTanggal.onErrorLoadAsuransi(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void listPendaftaran(final Activity activity, final ViewsPendaftaran viewsPendaftaran, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranBooking> observable = interfacePendaftaran.getPendaftaran(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranBooking>() {
                    @Override
                    public void onNext(RestPendaftaranBooking restPendaftaranBooking) {
                        if (restPendaftaranBooking.getSuccess()) {
                            viewsPendaftaran.onSuccessPendaftaran(restPendaftaranBooking);
                        } else {
                            viewsPendaftaran.onErrorPendaftaran(""+restPendaftaranBooking.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendaftaran.onErrorPendaftaran(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void listPendaftaranPoli(final Activity activity, final ViewsPendaftaranLogin viewsPendaftaranLogin, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranLogin> observable = interfacePendaftaran.checkUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranLogin>() {
                    @Override
                    public void onNext(RestPendaftaranLogin restPendaftaranLogin) {
                        if (restPendaftaranLogin.getSuccess()) {
                            viewsPendaftaranLogin.onSuccessLogin(restPendaftaranLogin);
                        } else {
                            viewsPendaftaranLogin.onErrorLogin(""+restPendaftaranLogin.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendaftaranLogin.onErrorLogin(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void listPendaftaranDokter(final Activity activity, final ViewsPendaftaranLogin viewsPendaftaranLogin, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranLogin> observable = interfacePendaftaran.checkUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranLogin>() {
                    @Override
                    public void onNext(RestPendaftaranLogin restPendaftaranLogin) {
                        if (restPendaftaranLogin.getSuccess()) {
                            viewsPendaftaranLogin.onSuccessLogin(restPendaftaranLogin);
                        } else {
                            viewsPendaftaranLogin.onErrorLogin(""+restPendaftaranLogin.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendaftaranLogin.onErrorLogin(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void getTanggalPend(final Activity activity, final ViewsGetTanggal viewsGetTanggal) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranTanggal> observable = interfacePendaftaran.getTanggalDaftar();
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranTanggal>() {
                    @Override
                    public void onNext(RestPendaftaranTanggal restPendaftaranTanggal) {
                        if (restPendaftaranTanggal.getSuccess()) {
                            viewsGetTanggal.onLoadTanggal(restPendaftaranTanggal);
                        } else {
                            viewsGetTanggal.onErrorTanggalLoad("0");
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsGetTanggal.onErrorTanggalLoad(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPendPoli(final Activity activity, final ViewsPendPoli viewsPendPoli, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranPoli> observable = interfacePendaftaran.getPoliklinik(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranPoli>() {
                    @Override
                    public void onNext(RestPendaftaranPoli restPendaftaranPoli) {
                        if (restPendaftaranPoli.getSuccess()) {
                            viewsPendPoli.onSuccessPendPoli(restPendaftaranPoli);
                        } else {
                            viewsPendPoli.onErrorPendPoli(""+restPendaftaranPoli.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendPoli.onErrorPendPoli(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void getPendDokter(final Activity activity, final ViewsPendDokter viewsPendDokter, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestPendaftaranDokter> observable = interfacePendaftaran.getDokter(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestPendaftaranDokter>() {
                    @Override
                    public void onNext(RestPendaftaranDokter restPendaftaranDokter) {
                        if (restPendaftaranDokter.getSuccess()) {
                            viewsPendDokter.onSuccessPendDokter(restPendaftaranDokter);
                        } else {
                            viewsPendDokter.onErrorPendDokter(""+restPendaftaranDokter.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsPendDokter.onErrorPendDokter(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void sendPendOnline(final Activity activity, final ViewsPendSend views, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<Rest> observable = interfacePendaftaran.sendPend(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        if (rest.getSuccess()) {
                            views.onSuccessPendSend(rest);
                        } else {
                            views.onErrorPendSend(rest.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            views.onErrorPendSend(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void batalPendOnline(final Activity activity, final ViewsPendBatal views, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<Rest> observable = interfacePendaftaran.batalPend(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Rest>() {
                    @Override
                    public void onNext(Rest rest) {
                        if (rest.getSuccess()) {
                            views.onSuccessPendBatal(rest);
                        } else {
                            views.onErrorPendBatal(rest.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            views.onErrorPendBatal(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getPendaftaranDetail(final Activity activity, final ViewsPendDetail views, final HashMap<String,String> query) {
        InterfacePendaftaran interfacePendaftaran = NetworkClient.getClientPendaftaran().create(InterfacePendaftaran.class);
        Observable<RestDetailPendaftaran> observable = interfacePendaftaran.getPendDetail(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestDetailPendaftaran>() {
                    @Override
                    public void onNext(RestDetailPendaftaran rest) {
                        if (rest.getSuccess()) {
                            views.onSuccessPendDetail(rest);
                        } else {
                            views.onErrorPendDetail(rest.getMessage());
                            Test.look("count 0");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            views.onErrorPendDetail(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void listKeluarga(final Activity activity, final View view, final ViewsKeluarga viewsKeluarga, final HashMap<String, String> dataString){
        final ArrayList<Keluarga> arrayList = new ArrayList<>();
        InterfaceInfo interfaceInfo   = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<RestListKeluarga> observable  = interfaceInfo.listKeluarga(dataString);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<RestListKeluarga>() {
            @Override
            public void onNext(RestListKeluarga restListKeluarga) {
                if(restListKeluarga.getSuccess()){
                    if(restListKeluarga.getNumData()==0){
                        viewsKeluarga.onErrorGetKeluarga(restListKeluarga.getNumData());
                    }else{
                        arrayList.addAll(restListKeluarga.getKeluarga());
                        viewsKeluarga.onSuccessGetKeluarga(arrayList);
                    }

                }else{
                    viewsKeluarga.onErrorGetKeluarga(restListKeluarga.getNumData());
                }
            }

            @Override
            public void onError(Throwable e) {

                viewsKeluarga.onErrorGetKeluarga(0);
            }

            @Override
            public void onComplete() {

            }
        });

    }


    public void deleteKeluarga(final ViewsKeluarga viewsKeluarga, final HashMap<String, String> dataString){

        InterfaceInfo interfaceDataUser   = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<Rest> observable  = interfaceDataUser.deleteKeluarga(dataString);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<Rest>() {
            @Override
            public void onNext(Rest rest) {
                if(rest.getSuccess()){
                    viewsKeluarga.onSuccessHapus();
                }else{
                    viewsKeluarga.onErrorHapus(rest.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                viewsKeluarga.onErrorHapus(e.getMessage());

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public void addKeluarga(final Activity activity, final View view, final ViewsKeluarga viewsKeluarga, final HashMap<String, String> dataString){

        InterfaceInfo interfaceDataUser   = NetworkClient.getClient().create(InterfaceInfo.class);
        Observable<Rest> observable  = interfaceDataUser.addKeluarga(dataString);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableObserver<Rest>() {
            @Override
            public void onNext(Rest restUserPrimer) {
//                Log.v("Ok", ""+restUserPrimer);
                if(restUserPrimer.getSuccess()){
                    viewsKeluarga.onSuccessAddKeluarga(restUserPrimer.getMessage());
                }else{
                    viewsKeluarga.onErrorAddKeluarga(restUserPrimer.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                viewsKeluarga.onErrorAddKeluarga(e.getMessage());
//                ErrorResponHandler.ErrorShows("addKeluarga",""+e.getMessage() +" "+ErrorResponHandler.getError(e));
            }

            @Override
            public void onComplete() {

            }
        });

    }
}
