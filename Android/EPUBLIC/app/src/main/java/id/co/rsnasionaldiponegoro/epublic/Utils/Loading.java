package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.app.Activity;
import android.app.ProgressDialog;

/**
 * Created by xsanz on 9/23/2018.
 */

public class Loading {
    ProgressDialog progressDialog;

    public Loading(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
        progressDialog.setMax(100);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setCancelable(false);
    }

    public void show() {
        progressDialog.show();
    }

    public void hide() {
        progressDialog.hide();
//        progressDialog.dismiss();
    }
}
