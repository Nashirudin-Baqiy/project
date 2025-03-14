package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianApotik;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianCS;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestAntrianPoli;

/**
 * Created by xsanz on 11/12/2018.
 */

public interface ViewsAntrian {
    void onSuccessAntrianCS(RestAntrianCS restAntrianCS);
    void onSuccessAntrianPoli(RestAntrianPoli restAntrianPoli);
    void onSuccessAntrianApotik(RestAntrianApotik restAntrianApotik);
    void onErrorAntrianCS(String msg);
    void onErrorAntrianPoli(String msg);
    void onErrorAntrianApotik(String msg);
}
