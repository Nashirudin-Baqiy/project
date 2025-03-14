package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by xsanz on 9/23/2018.
 */

public class Test {
    private static Boolean debug = false;

    public static void look(String title, String Content) {
        if (debug) {
            Log.d(title, Content);
        } else {
            //Debug Dead
        }
    }

    public static void look(String Content) {
        if (debug) {
            Log.d("Test : ", "Content : " + Content);
        } else {
            //Debug Dead
        }
    }

    public static void look(Boolean Content) {
        if (debug) {
            Log.d("Test : ", "Content : " + Content);
        } else {
            //Debug Dead
        }
    }

    //Error Handler
    public static void onError(String Content) {
        if (debug) {
            Log.d("Test : ", "Error On: " + Content);
        } else {
            //Debug Dead
        }
    }

    public static void ToastSukses(Context context,String msg){
        Toast.makeText(context, "Sukses "+msg, Toast.LENGTH_LONG).show();
    }

    public static void ToastKoneksi(Context context){
        Toast.makeText(context, "Koneksi Buruk", Toast.LENGTH_LONG).show();
    }
}
