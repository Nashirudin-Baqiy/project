package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestEmergency;

/**
 * Created by xsanz on 9/25/2018.
 */

public interface ViewsEmergency {
    void onSuccessEmergency(RestEmergency restEmergency);

    void onErrorEmergency(RestEmergency restEmergency, String msg);
}
