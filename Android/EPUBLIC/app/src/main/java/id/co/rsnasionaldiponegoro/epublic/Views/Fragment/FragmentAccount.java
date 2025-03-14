package id.co.rsnasionaldiponegoro.epublic.Views.Fragment;


import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataUser;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestLogin;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityEditKeluarga;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.ActivityLogin;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsDetailUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAccount extends Fragment implements View.OnClickListener,ViewsDetailUser {

    private LinearLayout button_logout;
    private LinearLayout layout_edit;
    private LinearLayout layout_profile;
    private Button button_edit;
    private TextView button_edit_cancel;
    private ImageView iv_qrcode;

    private TextView form_nama_value;
    private TextView form_norm_value;
    private View view;
    private Session session;

    private EditText form_nama_edit_value;
    private EditText form_email_edit_value;
    private EditText form_norm_edit_value;
    private EditText form_tgllahir_edit_value;
    private CardView layout_qrcode;
    private Button button_simpan;
    private String tgllhir;
    private DataUser dataUser;
    private RelativeLayout layout_pasiencard;
    final HashMap<String, String> queryUpdate = new HashMap<>();
    private Loading loading;
    private DatePicker datePicker = new id.co.rsnasionaldiponegoro.epublic.Utils.DatePicker();
    private Button button_keluargasaya;
    public FragmentAccount() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_account, container, false);
        init();
        return view;
    }

    private void init() {
        session = new Session(getActivity());
        dataUser= new DataUser();
        loading= new Loading(getActivity());

        button_logout = view.findViewById(R.id.button_logout);
        button_edit = view.findViewById(R.id.button_edit);
        button_edit_cancel = view.findViewById(R.id.button_edit_cancel);
        layout_qrcode = view.findViewById(R.id.layout_qrcode);
        iv_qrcode = view.findViewById(R.id.iv_qrcode);
        layout_pasiencard = view.findViewById(R.id.layout_pasiencard);

        layout_profile = view.findViewById(R.id.layout_profile);
        layout_edit = view.findViewById(R.id.layout_edit);
        form_nama_value = view.findViewById(R.id.form_nama_value);
        form_norm_value = view.findViewById(R.id.form_norm_value);


        form_nama_edit_value = view.findViewById(R.id.form_nama_edit_value);
        form_email_edit_value = view.findViewById(R.id.form_email_edit_value);
        form_norm_edit_value = view.findViewById(R.id.form_norm_edit_value);
        form_tgllahir_edit_value = view.findViewById(R.id.form_tgllahir_edit_value);
        button_simpan = view.findViewById(R.id.button_simpan);
        button_keluargasaya = view.findViewById(R.id.button_keluargasaya);

        session = new Session(getActivity());
        button_logout.setOnClickListener(this);
        button_edit_cancel.setOnClickListener(this);
        button_edit.setOnClickListener(this);
        form_tgllahir_edit_value.setOnClickListener(this);
        button_simpan.setOnClickListener(this);
        layout_pasiencard.setOnClickListener(this);
        layout_qrcode.setOnClickListener(this);
        layout_qrcode.setVisibility(View.GONE);

        button_keluargasaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),ActivityEditKeluarga.class).putExtra("title","Keluarga Saya"));
            }
        });


        try {

            form_nama_value.setText(session.getSessionUser().getNAMA());
            form_norm_value.setText(session.getSessionUser().getNORM());

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(
                        ""+session.getSessionUser().getNORM().trim()
                        , BarcodeFormat.CODE_128
                        ,200
                        ,50);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                iv_qrcode.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }

        }catch (Exception ex){}
        form_tgllahir_edit_value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.setViewTimePickers(getActivity(),form_tgllahir_edit_value);
                DialogFragment newFragment = datePicker;
                newFragment.show(getActivity().getFragmentManager(),"datepicker");
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_logout:
                session.clearSession();
                getActivity().finish();
                startActivity(new Intent(getActivity(), ActivityLogin.class));

                break;
            case R.id.layout_pasiencard:
                layout_qrcode.setVisibility(View.VISIBLE);

                break;
            case R.id.layout_qrcode:
                layout_qrcode.setVisibility(View.GONE);

                break;
//            case R.id.form_tgllahir_edit_value:
//                Calendar cal = Calendar.getInstance();
//                new DatePickerDialog(
//                        getActivity(),
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                                form_tgllahir_edit_value.setText("" + new SimpleDateFormat("dd-MM-yyyy").format(new Date(year - 1900, month, dayOfMonth)));
//                                tgllhir = new SimpleDateFormat("yyyy-MM-dd").format(new Date(year - 1900, month, dayOfMonth));
//                            }
//                        },
//                        cal.get(Calendar.YEAR),
//                        cal.getInstance().get(Calendar.MONTH),
//                        cal.getInstance().get(Calendar.DAY_OF_MONTH)
//                ).show();
//                break;
            case R.id.button_edit:
                layout_profile.setVisibility(View.GONE);
                layout_edit.setVisibility(View.VISIBLE);
                form_nama_edit_value.setText(session.getSessionUser().getNAMA());
                form_email_edit_value.setText(session.getSessionUser().getEMAIL());
                form_norm_edit_value.setText(session.getSessionUser().getNORM());
                form_tgllahir_edit_value.setText(session.getSessionUser().getTGLLAHIR());
                tgllhir=session.getSessionUser().getTGLLAHIR();
                break;
            case R.id.button_simpan:
                tgllhir=form_tgllahir_edit_value.getText().toString();
                loading.show();
                if(form_nama_edit_value.getText().toString().trim().length()!=0
                        &&form_email_edit_value.getText().toString().trim().length()!=0
                        &&form_norm_edit_value.getText().toString().trim().length()!=0
                        &&form_tgllahir_edit_value.getText().toString().trim().length()!=0){
                    queryUpdate.put("userId",""+session.getSessionUser().getIDUSERS().trim());
                    queryUpdate.put("nama",""+form_nama_edit_value.getText().toString());
                    queryUpdate.put("email",""+form_email_edit_value.getText().toString().trim());
                    queryUpdate.put("norm",""+form_norm_edit_value.getText().toString().trim());
                    queryUpdate.put("tgllhr",""+tgllhir.trim());
                    dataUser.updateUser(getActivity(),this,queryUpdate);
                }else{
                    loading.hide();
                    Toast.makeText(getActivity(),"Silahkan Lengkapi Form",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.button_edit_cancel:
                layout_profile.setVisibility(View.VISIBLE);
                layout_edit.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onSuccessUpdate() {
        queryUpdate.clear();
        queryUpdate.put("userId",""+session.getSessionUser().getIDUSERS());
        dataUser.detailUser(getActivity(),this,queryUpdate);

    }

    @Override
    public void onErrorUpdate(String msg) {
        loading.hide();
        Toast.makeText(getActivity(),""+msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessDetailUser(RestLogin restLogin) {
        loading.hide();
        Toast.makeText(getActivity(),"Sukses Update",Toast.LENGTH_LONG).show();
        layout_profile.setVisibility(View.VISIBLE);
        layout_edit.setVisibility(View.GONE);
        if(restLogin!=null){
            session.setSessionUser(restLogin.getUser().get(0));

            form_nama_value.setText(session.getSessionUser().getNAMA());
            form_norm_value.setText(session.getSessionUser().getNORM());

        }
    }

    @Override
    public void onErrorDetailUser(String msg) {
        loading.hide();
    }

}
