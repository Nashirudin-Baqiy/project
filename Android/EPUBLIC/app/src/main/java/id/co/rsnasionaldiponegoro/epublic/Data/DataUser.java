package id.co.rsnasionaldiponegoro.epublic.Data;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceUser;
import id.co.rsnasionaldiponegoro.epublic.Model.Models.User;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPGen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPLogin;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClient;
import id.co.rsnasionaldiponegoro.epublic.Utils.Test;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsDetailUser;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsLogin;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xsanz on 9/24/2018.
 */

public class DataUser {
    public void checkLogin(final Activity activity, final ViewsLogin viewsLogin, final HashMap<String, String> query, final String userId, final String nohp) {
        final ArrayList<User> arrayList = new ArrayList<>();
        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        Observable<RestLogin> observable = interfaceUser.checkUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestLogin>() {
                    @Override
                    public void onNext(RestLogin restLogin) {
                        if (restLogin.getSuccess()) {
                            viewsLogin.onSuccessLogin(restLogin);
                        } else {
                            if (restLogin.getMessage().equals("NEW_USERS")) {
                                viewsLogin.onSuccessNewuser(userId, nohp);
                            } else {
                                viewsLogin.onErrorLogin(restLogin, restLogin.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsLogin.onErrorLogin(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public void addUser(final Activity activity, final ViewsLogin viewsLogin, final HashMap<String, String> query) {
        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        Observable<RestLogin> observable = interfaceUser.addUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestLogin>() {
                    @Override
                    public void onNext(RestLogin restLogin) {
                        if (restLogin.getSuccess()) {
                            viewsLogin.onSuccessLogin(restLogin);
                        } else {
                            viewsLogin.onErrorLogin(restLogin, restLogin.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsLogin.onErrorLogin(null, e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void updateUser(final Activity activity, final ViewsDetailUser viewsDetailUser, final HashMap<String, String> query) {
        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        Observable<RestLogin> observable = interfaceUser.updateUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestLogin>() {
                    @Override
                    public void onNext(RestLogin restLogin) {
                        if (restLogin.getSuccess()) {
                            viewsDetailUser.onSuccessUpdate();
                        } else {
                            viewsDetailUser.onErrorUpdate(restLogin.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsDetailUser.onErrorUpdate(e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
    public void detailUser(final Activity activity, final ViewsDetailUser viewsDetailUser, final HashMap<String, String> query) {
        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        Observable<RestLogin> observable = interfaceUser.detailUser(query);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestLogin>() {
                    @Override
                    public void onNext(RestLogin restLogin) {
                        if (restLogin.getSuccess()) {
                            viewsDetailUser.onSuccessDetailUser(restLogin);
                        } else {
                            viewsDetailUser.onErrorDetailUser(restLogin.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsDetailUser.onErrorDetailUser( e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void generateOTP(final Activity activity, final ViewsLogin viewsLogin, final HashMap<String, String> query) {
//        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        InterfaceUser interfaceUser = NetworkClient.getClientOTP().create(InterfaceUser.class);
        Observable<RestOTPGen> observable = interfaceUser.genNewOTP(query.get("nohp"));
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestOTPGen>() {
                    @Override
                    public void onNext(RestOTPGen restOTPGen) {
                        if (restOTPGen.getStatus()) {
                            viewsLogin.onSuccessGenOTP(restOTPGen);
                        } else {
                            viewsLogin.onErrorOtp("OTPGen", "Jaringan Buruk");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            viewsLogin.onErrorOtp("Koneksi", e.getMessage());
                            Test.look(e.getMessage());
                        } catch (Exception ex) {
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public void loginOTP(final Activity activity, final ViewsLogin viewsLogin, final HashMap<String, String> query) {
//        InterfaceUser interfaceUser = NetworkClient.getClient().create(InterfaceUser.class);
        InterfaceUser interfaceUser = NetworkClient.getClientOTP().create(InterfaceUser.class);

        Log.e("coba", query.get("nohp") + query.get("pin"));
        Observable<RestOTPLogin> observable = interfaceUser.loginNewOTP(query.get("nohp"), query.get("pin"));
        Log.e("coba", String.valueOf(observable));

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RestOTPLogin>() {
                    @Override
                    public void onNext(RestOTPLogin restOTPLogin) {
                        Log.e("coba", String.valueOf(restOTPLogin.getStatus()));
                        if (restOTPLogin.getStatus()) {
                            viewsLogin.onSuccessLoginOTP(restOTPLogin);
                        } else {
                            Log.e("coba", String.valueOf(restOTPLogin));
                            viewsLogin.onErrorOtp("OTPLogin", "Jaringan Buruk");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.e("coba", String.valueOf(e));
                            viewsLogin.onErrorOtp("Koneksi", e.getMessage());
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
