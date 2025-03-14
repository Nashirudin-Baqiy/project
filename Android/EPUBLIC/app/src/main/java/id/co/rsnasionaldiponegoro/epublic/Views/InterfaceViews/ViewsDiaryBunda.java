package id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews;

import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRI;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestDiaryBundaRJ;

/**
 * Created by xsanz on 10/1/2018.
 */

public interface ViewsDiaryBunda {
    void onSuccessDiaryBundaRJ(RestDiaryBundaRJ restDiaryBundaRJ);
    void onSuccessDiaryBundaError(String msg);
    void onSuccessDiaryBundaRI(RestDiaryBundaRI restDiaryBundaRI);

}
