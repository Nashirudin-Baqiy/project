package id.co.rsnasionaldiponegoro.epublic.Views.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import id.co.rsnasionaldiponegoro.epublic.Data.DataInfo;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestContact;
import id.co.rsnasionaldiponegoro.epublic.Model.Respone.RestEmergency;
import id.co.rsnasionaldiponegoro.epublic.R;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsContact;
import id.co.rsnasionaldiponegoro.epublic.Views.InterfaceViews.ViewsEmergency;

public class ActivityEmergency extends AppCompatActivity implements View.OnClickListener,ViewsEmergency,ViewsContact {
    private LinearLayout btn_back;
    private LinearLayout btn_viatelepon;
    private LinearLayout btn_viateleponigd;
    private LinearLayout btn_viadirect;
    private TextView title_bar;
    private TextView ct_alamat;
    private TextView ct_notelp;
    private TextView ct_email;
    private TextView ct_ig;
    private ImageView image_bunda;
    private DataInfo dataInfo;
    private GoogleMap mGoogleMap;
    private MapView mapView;
    private Marker marker;
    private HashMap<String, String> query;
    private Bundle bundles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        bundles=savedInstanceState;
        init();

    }

    private void init(){
        btn_back         = findViewById(R.id.btn_back);
        title_bar        = findViewById(R.id.title_bar);
//        image_bunda        = findViewById(R.id.image_bunda);
        btn_viatelepon        = findViewById(R.id.btn_viatelepon);
        btn_viateleponigd        = findViewById(R.id.btn_viateleponigd);
        btn_viadirect        = findViewById(R.id.btn_viadirect);
        ct_alamat        = findViewById(R.id.ct_alamat);
        ct_notelp        = findViewById(R.id.ct_notelp);
        ct_email        = findViewById(R.id.ct_email);
        ct_ig        = findViewById(R.id.ct_ig);
        mapView         = findViewById(R.id.mapView);
        title_bar.setText("Emergency");
        dataInfo = new DataInfo();
        dataInfo.getEmergency(this,this);
        dataInfo.getContact(this,this);

        btn_back.setOnClickListener(this);


    }

    private void maps(final Double v1, final Double v2, Bundle bundle) {
        mapView.onCreate(bundle);
        mapView.onResume();
        try {
            MapsInitializer.initialize(ActivityEmergency.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap mMap) {
                mGoogleMap = mMap;
//                marker = mGoogleMap.addMarker(new MarkerOptions().position(
//                        new LatLng(-6.993267,110.433256)).title("").snippet(""));
                marker = mGoogleMap.addMarker(new MarkerOptions().position(
                        new LatLng(v1, v2)).title("").snippet(""));
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(
                        new LatLng(v1, v2)).zoom(15).build()));
                mGoogleMap.setOnMyLocationChangeListener(null);

            }
        });
    }
    private void telepon(String telp) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+telp));
        if (ContextCompat.checkSelfPermission(ActivityEmergency.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivityEmergency.this, new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else
        {
            startActivity(callIntent);
        }
    }

    private void direct(String lat,String longi){
//        Uri gmmIntentUri = Uri.parse("geo:"+lat+","+longi+"");
//        Uri gmmIntentUri = Uri.parse("geo:"+lat+","+longi+"");
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        Uri.Builder directionsBuilder = new Uri.Builder()
                .scheme("https")
                .authority("www.google.com")
                .appendPath("maps")
                .appendPath("dir")
                .appendPath("")
                .appendQueryParameter("api", "1")
                .appendQueryParameter("destination", lat + "," + longi);
//        mapIntent.setPackage("com.google.android.apps.maps");
//        if (mapIntent.resolveActivity(getPackageManager()) != null) {
//            startActivity(mapIntent);
//        }
        startActivity(new Intent(Intent.ACTION_VIEW, directionsBuilder.build()));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back  : finish();
                break;
        }
    }

    @Override
    public void onSuccessEmergency(final RestEmergency restEmergency) {
        if(restEmergency!=null){
            btn_viatelepon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    telepon(restEmergency.getData().get(0).getTLPNBUNDA());
                }
            });
            btn_viateleponigd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    telepon(restEmergency.getData().get(0).getTLPNIGD());
                }
            });
            btn_viadirect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    direct(restEmergency.getData().get(0).getLATITUDE(),restEmergency.getData().get(0).getLONGITUDE());
                }
            });

            maps(Double.valueOf(restEmergency.getData().get(0).getLATITUDE()),
                    Double.valueOf(restEmergency.getData().get(0).getLONGITUDE()),
                    bundles);
        }
    }

    @Override
    public void onErrorEmergency(RestEmergency restEmergency, String msg) {

    }


    @Override
    public void onSuccessContact(RestContact restContact) {
        if(restContact!=null){
            ct_alamat.setText(Html.fromHtml(restContact.getContact().get(0).getALAMAT()));
            ct_email.setText(""+restContact.getContact().get(0).getEMAIL());
            ct_notelp.setText(""+restContact.getContact().get(0).getTLPN());
            ct_ig.setText(""+restContact.getContact().get(0).getIG());

            ct_notelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    telepon(ct_notelp.getText().toString());
                }
            });


        }
    }

    @Override
    public void onErrorContact(RestContact restContact, String msg) {

    }
}
