package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestArtikel;

/**
 * Created by xsanz on 9/26/2018.
 */

public interface ViewsArtikel {
    void onSuccessArtikel(RestArtikel restArtikel);
    void onErrorArtikel(RestArtikel restArtikel,String msg);
}
