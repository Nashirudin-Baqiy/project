package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import id.co.rsnasionaldiponegoro.epublic.BuildConfig;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityMain;

/**
 * Created by xsanz on 10/1/2018.
 */

public class DialogAlerts {
    private Activity activity;
    private Context context;
    private AlertDialog.Builder noticebuilder;
    private Session session;
    public DialogAlerts(Activity activity, Context context) {
        this.activity = activity;
        this.context = context;

        if(activity!=null){
            noticebuilder = new AlertDialog.Builder(activity);
            session = new Session(activity);
        }

        if(context!=null){
            noticebuilder = new AlertDialog.Builder(context);
            session = new Session(context);
        }
    }


    public void CreateDialogAlertsPositive( final String title, final String desc){
        noticebuilder.setTitle(title)
                .setCancelable(false)
                .setMessage(desc)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        activity.finish();
                    }
                });

        AlertDialog alert11 = noticebuilder.create();
        alert11.show();
    }

    public void CreateDialogAlertsPositiveUpdate( final String title, final String desc){
        noticebuilder.setTitle(title)
                .setCancelable(false)
                .setMessage(desc)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
                        try {
                            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                });

        AlertDialog alert11 = noticebuilder.create();
        alert11.show();
    }


    public void CreateDialogAlertsPositiveUpdateNon( final String title, final String desc){
        noticebuilder.setTitle(title)
                .setCancelable(false)
                .setMessage(desc)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final String appPackageName = BuildConfig.APPLICATION_ID; // getPackageName() from Context or Activity object
                        try {
                            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                        }
                    }
                }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alert11 = noticebuilder.create();
        alert11.show();
    }

    public void CreateDialogAlertsIsiNoRM( final String title, final String desc){
        noticebuilder.setTitle(title)
                .setCancelable(false)
                .setMessage(desc)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        activity.finish();

                        if(activity!=null){
                            activity.finish();
                            activity.startActivity(new Intent(context, ActivityMain.class)
                                    .putExtra("title", "ISINORM"));

                        }


                    }
                });

        AlertDialog alert11 = noticebuilder.create();
        alert11.show();
    }


}
