package com.example.otpwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class VerificationActivity : AppCompatActivity() {
    private lateinit var teksEditKodeOTP: TextInputLayout
    private lateinit var tombolVerifikasi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        this.tombolVerifikasi = this.findViewById(R.id.tombolVerifikasi)
        this.teksEditKodeOTP = this.findViewById(R.id.teksEditKodeOTP)

//        Cek format nomor telepon
        this.teksEditKodeOTP.editText?.doOnTextChanged { kodeOTP, _, _, _ ->
            if (!this.cekKodeOTP(kodeOTP)) {
                this.teksEditKodeOTP.error = "Kode OTP salah format"
                this.tombolVerifikasi.isEnabled = false
            } else {
                this.teksEditKodeOTP.error = null
                this.tombolVerifikasi.isEnabled = true
            }
        }

//        TODO: Buat fungsi verifikasi OTP di sini

        this.tombolVerifikasi.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun cekKodeOTP(kodeOTP: CharSequence?): Boolean {
        if (kodeOTP == null) {
            return false
        }
        if (kodeOTP.length != 6) {
            return false
        }
        return true
    }
}