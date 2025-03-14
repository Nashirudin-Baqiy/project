package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranPoli;

/**
 * Created by xsanz on 1/12/2019.
 */

public interface ViewsPendPoli {
    void onSuccessPendPoli(RestPendaftaranPoli restPendaftaranPoli);
    void onErrorPendPoli(String msg);
}
