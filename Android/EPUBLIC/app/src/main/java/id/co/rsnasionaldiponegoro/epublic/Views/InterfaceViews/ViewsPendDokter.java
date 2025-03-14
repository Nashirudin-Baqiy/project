package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranDokter;

/**
 * Created by xsanz on 1/16/2019.
 */

public interface ViewsPendDokter {
    void onSuccessPendDokter(RestPendaftaranDokter restPendaftaranDokter);
    void onErrorPendDokter(String msg);
}
