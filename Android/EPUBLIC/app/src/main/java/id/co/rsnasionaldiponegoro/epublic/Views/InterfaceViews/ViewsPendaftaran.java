package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranBooking;

/**
 * Created by xsanz on 12/26/2018.
 */

public interface ViewsPendaftaran {
    void onSuccessPendaftaran(RestPendaftaranBooking restPendaftaranBooking);
    void onErrorPendaftaran(String msg);
}
