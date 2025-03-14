package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestHeadline;

/**
 * Created by xsanz on 9/25/2018.
 */

public interface ViewsHeadline {
    void onSuccessHeadline(RestHeadline restHeadline);

    void onErrorHeadline(RestHeadline restHeadline, String msg);
}
