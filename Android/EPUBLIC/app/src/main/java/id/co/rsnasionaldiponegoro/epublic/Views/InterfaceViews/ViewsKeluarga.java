package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import java.util.ArrayList;

import id.co.rsnasionaldiponegoro.epublic.Model.Models.Keluarga;

/**
 * Created by xsanz on 1/31/2020.
 */

public interface ViewsKeluarga {
    void onSuccessGetKeluarga(ArrayList<Keluarga> arrayListKeluarga);
    void onErrorGetKeluarga(int num_data);
    void onSuccessAddKeluarga(String msg);
    void onErrorAddKeluarga(String msg);
//    void onSuccessValidNoCm(ArrayList<RestCekValidasi> arrayListValidasi);
    void onHapusKeluarga(Keluarga keluarga);
    void onSuccessHapus();
    void onErrorHapus(String msg);
    void onErrorValid();
}
