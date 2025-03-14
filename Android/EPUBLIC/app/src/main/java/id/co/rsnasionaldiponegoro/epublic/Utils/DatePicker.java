package id.co.rsnasionaldiponegoro.epublic.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by xsanz on 10/19/2018.
 */

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private Activity activity;
    private EditText Identity;
    private TextView tvIdentity;


    public void setViewTimePickers(Activity activity, EditText tempatTanggal){
        this.activity = activity;
        Identity = tempatTanggal;
//        this.Identity = Identity;
    }

    public void setViewTimePickers(Activity activity, TextView tempatTanggal){
        this.activity = activity;
        tvIdentity = tempatTanggal;
//        this.Identity = Identity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, this, year, month, day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker view, int year, int month, int dayOfMonth) {
//        EditText dateku = (EditText) view.findViewById(Identity);
        month++;
        String waktu = ""+year+"-"+String.format("%02d", month)+"-"+String.format("%02d", dayOfMonth)+"";
        if(tvIdentity != null){
            tvIdentity.setText(waktu);
        }else{
            Identity.setText(waktu);
        }


    }

}
