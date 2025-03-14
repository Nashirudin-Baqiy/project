package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHistory;

/**
 * Created by xsanz on 9/25/2018.
 */

public interface ViewsHistory {
    void onSuccessHistory(RestHistory restHistory);

    void onErrorHistory(RestHistory restHistory, String msg);
}
