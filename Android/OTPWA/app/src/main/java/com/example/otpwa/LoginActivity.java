package com.example.otpwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private Button tombolKirimWA;
    private EditText teksEditNomorTelepon;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.tombolKirimWA = findViewById(R.id.tombolKirimWA);
        this.teksEditNomorTelepon = findViewById(R.id.teksEditNomorTelepon);

//        Cek format nomor telepon


//        TODO: Buat fungsi kirim kode OTP di sini
        this.tombolKirimWA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOTP(teksEditNomorTelepon.getText().toString());
            }
        });
    }

    private void sendOTP(String nomorTelepon) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.48:7000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<OTP> kirimOTP = api.sendOTP(nomorTelepon);

        kirimOTP.enqueue(new Callback<OTP>() {
            @Override
            public void onResponse(Call<OTP> call, Response<OTP> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(LoginActivity.this, "Kode OTP terkirim!", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nomor_telepon", nomorTelepon);
                editor.apply();

                String nomorTelepon2 = sharedPreferences.getString("nomor_telepon", "");
                Log.d("coba", nomorTelepon2);

                Intent intent = new Intent(LoginActivity.this, VerificationActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<OTP> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Gagal mengirim OTP!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean cekNomorTelepon(CharSequence nomorTelepon) {
        if (nomorTelepon == null) {
            return false;
        }
        if (nomorTelepon.length() < 9 || nomorTelepon.length() > 20) {
            return false;
        }
        if (!Patterns.PHONE.matcher(nomorTelepon).matches()) {
            return false;
        }
        return true;
    }
}