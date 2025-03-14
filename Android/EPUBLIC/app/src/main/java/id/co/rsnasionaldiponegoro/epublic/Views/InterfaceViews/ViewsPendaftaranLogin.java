package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranLogin;

/**
 * Created by xsanz on 12/27/2018.
 */

public interface ViewsPendaftaranLogin {
    void onSuccessLogin(RestPendaftaranLogin restPendaftaranLogin);
    void onErrorLogin(String Msg);
}
