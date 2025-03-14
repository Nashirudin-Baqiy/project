package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDetailPendaftaran;

/**
 * Created by xsanz on 1/17/2019.
 */

public interface ViewsPendDetail {
    void onSuccessPendDetail(RestDetailPendaftaran rest);
    void onErrorPendDetail(String msg);

}
