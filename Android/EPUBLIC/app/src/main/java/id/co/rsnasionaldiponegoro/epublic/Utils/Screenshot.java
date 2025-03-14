package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import static android.support.v4.content.FileProvider.getUriForFile;

/**
 * Created by xsanz on 10/31/2018.
 */

public class Screenshot {
    public static Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

    public static Bitmap takescreenshotOfRootView(View v) {
        return takescreenshot(v.getRootView());
    }

    public static  void takeScreenshot(Activity activity) {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = activity.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            openScreenshot(imageFile,activity);
        } catch (Throwable e) {
            // Several error may come out with file handling or DOM
            e.printStackTrace();
        }
    }

    public static void openScreenshot(File imageFile,Activity activity) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri=null;
        if(Build.VERSION.SDK_INT >=24){
             uri = getUriForFile(activity, " id.co.rsanugerahpekalongan.epublic.android.fileprovider", imageFile);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Test.look(""+uri.getPath());
        }else{
            uri = Uri.fromFile(imageFile);
        }
        intent.setDataAndType(uri, "image/*");
        activity.startActivity(intent);
    }
}
