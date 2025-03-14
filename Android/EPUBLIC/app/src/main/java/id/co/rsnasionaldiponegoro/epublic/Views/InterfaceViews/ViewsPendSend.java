package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;

/**
 * Created by xsanz on 1/16/2019.
 */

public interface ViewsPendSend {
    void onProsesSend(String dokter,String jam,String shift);
    void onSuccessPendSend(Rest rest);
    void onErrorPendSend(String msg);
    void onKuotaHabis();
}
