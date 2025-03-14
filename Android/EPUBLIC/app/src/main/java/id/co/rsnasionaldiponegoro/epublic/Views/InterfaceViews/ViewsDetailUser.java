package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;

/**
 * Created by xsanz on 9/30/2018.
 */

public interface ViewsDetailUser {
    void onSuccessUpdate();
    void onErrorUpdate(String msg);
    void onSuccessDetailUser(RestLogin restLogin);
    void onErrorDetailUser(String msg);
}
