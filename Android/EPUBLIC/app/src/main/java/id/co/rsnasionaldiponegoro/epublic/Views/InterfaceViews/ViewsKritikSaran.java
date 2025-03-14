package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestKritikSaran;

/**
 * Created by xsanz on 9/27/2018.
 */

public interface ViewsKritikSaran {
    void onSuccessLoadKritik(RestKritikSaran restKritikSaran);
    void onErrorLoadKritik(String msg);
}
