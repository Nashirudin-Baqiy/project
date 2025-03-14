package com.example.otpwa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VerificationActivity extends AppCompatActivity {
    private Button tombolVerifikasi;
    private TextInputLayout teksEditKodeOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        this.tombolVerifikasi = findViewById(R.id.tombolVerifikasi);
        this.teksEditKodeOTP = findViewById(R.id.teksEditKodeOTP);

//        TODO: Buat fungsi verifikasi OTP

        this.tombolVerifikasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifOTP(teksEditKodeOTP.getEditText().getText());
            }
        });
    }

    private void verifOTP(Editable kodeOTP) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.48:7000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        SharedPreferences sharedPreferences = getSharedPreferences("Pref", MODE_PRIVATE);
        String nomorTelepon = sharedPreferences.getString("nomor_telepon", "");

        Log.d("coba", nomorTelepon);

        if (nomorTelepon.isEmpty()) {
            Toast.makeText(VerificationActivity.this, "Nomor telepon salah", Toast.LENGTH_SHORT).show();
            return;
        }

        Call<OTP> verifOTP = api.verifOTP(nomorTelepon, kodeOTP);

        verifOTP.enqueue(new Callback<OTP>() {
            @Override
            public void onResponse(Call<OTP> call, Response<OTP> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(VerificationActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(VerificationActivity.this, "Berhasil login!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(VerificationActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<OTP> call, Throwable t) {
                Toast.makeText(VerificationActivity.this, "Gagal memverifikasi OTP!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}