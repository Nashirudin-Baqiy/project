package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPendaftaranTanggal;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestPenjaminAsuransi;

/**
 * Created by xsanz on 1/9/2019.
 */

public interface ViewsGetTanggal {
    void onLoadTanggal(RestPendaftaranTanggal restPendaftaranTanggal);
    void onErrorTanggalLoad(String msg);
    void onLoadAsuransi(RestPenjaminAsuransi restPenjaminAsuransi);
    void onErrorLoadAsuransi(String msg);
}
