package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPGen;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestOTPLogin;

/**
 * Created by xsanz on 9/24/2018.
 */

public interface ViewsLogin {
    void onSuccessLogin(RestLogin restLogin);

    void onErrorLogin(RestLogin restLogin, String msg);

    void onSuccessNewuser(String userId, String nohp);

    void onSuccessGenOTP(RestOTPGen restGenOTP);

    void onSuccessLoginOTP(RestOTPLogin restOTPLogin);

    void onErrorOtp(String from,String msg);
}
