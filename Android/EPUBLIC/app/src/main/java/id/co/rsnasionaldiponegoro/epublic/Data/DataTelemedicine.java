package id.co.rsnasionaldiponegoro.epublic.Data;

import android.app.Activity;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsGetTanggal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendBatal;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDetail;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendDokter;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendPoli;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendSend;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaran;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsPendaftaranLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xsanz on 24-Jun-2020.
 */

public class DataTelemedicine {
    //Pendaftaran

    public void loginPendaftaran(final Activity activity, final ViewsPendaftaranLogin viewsPendaftaranLogin, final HashMap<String,String> query) {
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranLogin> observable = interfaceTelemedicine.checkUser(query);
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


    public void listPendaftaran(final Activity activity, final ViewsPendaftaran viewsPendaftaran, final HashMap<String,String> query) {
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranBooking> observable = interfaceTelemedicine.getPendaftaran(query);
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

    public void listPendaftaranH(final Activity activity, final ViewsPendaftaran viewsPendaftaran, final HashMap<String,String> query) {
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranBooking> observable = interfaceTelemedicine.getPendaftaranHistory(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranLogin> observable = interfaceTelemedicine.checkUser(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranLogin> observable = interfaceTelemedicine.checkUser(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranTanggal> observable = interfaceTelemedicine.getTanggalDaftar();
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

    public void getListAsuransi(final Activity activity, final ViewsGetTanggal viewsGetTanggal) {
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPenjaminAsuransi> observable = interfaceTelemedicine.getPenjaminAsuransi();
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

    public void getPendPoli(final Activity activity, final ViewsPendPoli viewsPendPoli, final HashMap<String,String> query) {
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranPoli> observable = interfaceTelemedicine.getPoliklinik(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestPendaftaranDokter> observable = interfaceTelemedicine.getDokter(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<Rest> observable = interfaceTelemedicine.sendPend(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<Rest> observable = interfaceTelemedicine.batalPend(query);
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
        InterfaceTelemedicine interfaceTelemedicine = NetworkClient.getClientTele().create(InterfaceTelemedicine.class);
        Observable<RestDetailPendaftaran> observable = interfaceTelemedicine.getPendDetail(query);
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
}
