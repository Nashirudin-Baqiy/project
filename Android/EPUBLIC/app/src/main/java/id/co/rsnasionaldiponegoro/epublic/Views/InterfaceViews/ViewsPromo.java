package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestInfo;

/**
 * Created by xsanz on 9/26/2018.
 */

public interface ViewsPromo {
    void onSuccessPromo(RestInfo restInfo);

    void onErrorPromo(RestInfo restInfo, String msg);
}
