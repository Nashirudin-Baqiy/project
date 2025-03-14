package id.co.rsnasionaldiponegoro.epublic.Views.Activity;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import id.co.rsnasionaldiponegoro.epublic.Model.Interface.InterfaceTelemedicine;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.Rest;
import id.co.rsnasionaldiponegoro.epublic.Network.NetworkClientNew;
import id.co.rsnasionaldiponegoro.epublic.Utils.LoadImages;
import id.co.rsnasionaldiponegoro.epublic.Utils.Loading;
import id.co.rsnasionaldiponegoro.epublic.Utils.Session;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Utils.Screenshot;
import id.co.rsnasionaldiponegoro.epublic.Utils.UploadPhoto;
import id.co.rsnasionaldiponegoro.epublic.Views.Activity.Telemedicine.Pendaftaran.ActivityTMKonsulAddPoli;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ActivityInformConsern extends AppCompatActivity {

    private Session session;
    private Loading loading;
    private HashMap<String, RequestBody> dataString = new HashMap<String, RequestBody>();
    private Uri selectedImage=null;
    private Bundle bundle;
    private Uri uriselectedImage;
    private UploadPhoto uploadPhoto;
    private String filenamephoto;
    private Button btnFindFile;
    private ImageView image_ttd;
    private Button btn_kirim;
    private TextView btn_nexts;
    private RadioGroup radioGroup;
    private RadioButton rg1_0;
    private RadioButton rg1_1;
    private RadioGroup radioGroup2;
    private RadioButton rg2_0;
    private RadioButton rg2_1;
    private RadioGroup radioGroup3;
    private RadioButton rg3_1;
    private RadioButton rg3_0;
    private LinearLayout layout_ic_2;
    private LinearLayout btn_back;
    //private HashMap<String, RequestBody> dataString   = new HashMap<String, okhttp3.RequestBody>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform_consern);

        init();

    }

    private void init() {
        session = new Session(ActivityInformConsern.this);
        image_ttd= findViewById(R.id.image_ttd);
        btn_back= findViewById(R.id.btn_back);
        btnFindFile= findViewById(R.id.btnFindFile);
        btn_kirim= findViewById(R.id.btn_kirim);
        layout_ic_2= findViewById(R.id.layout_ic_2);
        layout_ic_2.setVisibility(View.GONE);
        uploadPhoto = new UploadPhoto(ActivityInformConsern.this);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        radioGroup= findViewById(R.id.radioGroup);
        rg1_0= findViewById(R.id.rg1_0);
        rg1_1= findViewById(R.id.rg1_1);

        radioGroup2= findViewById(R.id.radioGroup2);
        rg2_0= findViewById(R.id.rg2_0);
        rg2_1= findViewById(R.id.rg2_1);

        radioGroup3= findViewById(R.id.radioGroup3);
        rg3_0= findViewById(R.id.rg3_0);
        rg3_1= findViewById(R.id.rg3_1);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rg1_0:
                        session.setSessionString("informConsern1","0");
                        layout_ic_2.setVisibility(View.GONE);
                        break;
                    case R.id.rg1_1:
                        session.setSessionString("informConsern1","1");
                        layout_ic_2.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });
        rg1_0.setChecked(true);


        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rg2_0:
                        session.setSessionString("informConsern2","0");
                        break;
                    case R.id.rg2_1:
                        session.setSessionString("informConsern2","1");
                        break;

                }
            }
        });
        rg2_1.setChecked(true);

        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rg3_0:
                        session.setSessionString("informConsern3","0");
                        break;
                    case R.id.rg3_1:
                        session.setSessionString("informConsern3","1");
                        break;

                }
            }
        });
        rg3_1.setChecked(true);

        btn_kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                send();
            }
        });

        btnFindFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                SimpleDateFormat trans = new SimpleDateFormat("yyyyMMddHHmmss");
                filenamephoto = trans.format(cal.getTime());
                uploadPhoto.onClickImages(filenamephoto);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String nPhotoPath = "";
        nPhotoPath = uploadPhoto.getmCurrentPhotoPath();
        if(requestCode == 0 && resultCode == RESULT_OK){
            Bitmap selectedImages = null;
            File fileku = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/RSND/"),"RSND_"+filenamephoto+".jpeg");
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                if (!nPhotoPath.equals("")) {
                    uriselectedImage = Uri.parse(nPhotoPath);
                }
            }else {
                uriselectedImage = Uri.fromFile(fileku);
                try {
                    selectedImages = uploadPhoto.decodeUri(uriselectedImage);
                    uploadPhoto.saveFileImage(selectedImages, uriselectedImage);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
           setImage(selectedImages, uriselectedImage);

        } else if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            uriselectedImage = data.getData();
            File fileku = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES+"/RSND/"),"RSND_"+filenamephoto+".jpeg");

            try {
                Bitmap selectedImages = uploadPhoto.decodeUri(uriselectedImage);
                uriselectedImage = Uri.fromFile(fileku);
                uploadPhoto.saveFileImage(selectedImages,uriselectedImage);
               setImage(selectedImages, uriselectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor!=null) {
            if (cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                res = cursor.getString(column_index);
            }
            cursor.close();
        } else {
            Toast.makeText(this, "Cursor null" + proj, Toast.LENGTH_SHORT).show();
        }
        return res;
    }
    private void send(){
        if(selectedImage!=null
            &&session.getSessionString("informConsern1","1").equals("1")
            &&session.getSessionString("informConsern2","1").equals("1")
            &&session.getSessionString("informConsern3","1").equals("1")
            ){
            dataString.put("noRM", NetworkClientNew.toRequestBody(session.getSessionUserPend().getkDPASIEN()));
            dataString.put("informConsern1", NetworkClientNew.toRequestBody(session.getSessionString("informConsern1","1")));
            dataString.put("informConsern2", NetworkClientNew.toRequestBody(session.getSessionString("informConsern2","1")));
            dataString.put("informConsern3", NetworkClientNew.toRequestBody(session.getSessionString("informConsern3","1")));

            MultipartBody.Part foto = null;
            if (selectedImage != null) {
                File imageFile = new File(selectedImage.getPath());
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
                foto = MultipartBody.Part.createFormData("input_img", imageFile.getName(), requestFile);
            }

            InterfaceTelemedicine interfaceTelemedicine = NetworkClientNew.getClient(ActivityInformConsern.this).create(InterfaceTelemedicine.class);
            Observable<Rest> addInformConsern = interfaceTelemedicine.addInformConsern(dataString, foto);
            addInformConsern.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<Rest>() {
                        @Override
                        public void onNext(Rest restInterior) {
                            if(restInterior.getSuccess()){
                                Toast.makeText(ActivityInformConsern.this," "+restInterior.getMessage(),Toast.LENGTH_LONG).show();
                                session.setSessionString("TIC_ID",restInterior.getTIC_ID());
                                startActivity(new Intent(ActivityInformConsern.this,ActivityTMKonsulAddPoli.class));
                            }else{
                                Toast.makeText(ActivityInformConsern.this," "+restInterior.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Toast.makeText(ActivityInformConsern.this,"Gagal Upload",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            Toast.makeText(ActivityInformConsern.this,"SILAHKAN MENERIMA INFORM CONSERN DAN TTD WAJIB DISI",Toast.LENGTH_LONG).show();
        }
    }

    public void setImage(Bitmap selectedImages, Uri uriselectedImage) {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.N) {
            image_ttd.setImageBitmap(selectedImages);
        }else {
//            image_ttd.setImageURI(uriselectedImage);
            Log.d("cekgambar",uriselectedImage.toString());
            LoadImages.LoadDataImagesUri(this, uriselectedImage, R.drawable.logo_rsnd, image_ttd, null);
        }


        selectedImage=uriselectedImage;
    }
}
