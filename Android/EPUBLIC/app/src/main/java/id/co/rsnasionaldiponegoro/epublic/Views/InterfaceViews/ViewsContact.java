package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestContact;

/**
 * Created by xsanz on 9/25/2018.
 */

public interface ViewsContact {
    void onSuccessContact(RestContact restContact);

    void onErrorContact(RestContact restContact, String msg);
}
