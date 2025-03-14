package com.example.otpwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var teksEditNomorTelepon: TextInputLayout
    private lateinit var tombolKirimWA: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        this.teksEditNomorTelepon = this.findViewById(R.id.teksEditNomorTelepon)
        this.tombolKirimWA = this.findViewById(R.id.tombolKirimWA)

//        Cek format nomor telepon
        this.teksEditNomorTelepon.editText?.doOnTextChanged { nomorTelepon, _, _, _ ->
            if (!this.cekNomorTelepon(nomorTelepon)) {
                this.teksEditNomorTelepon.error = "Nomor telepon salah"
                this.tombolKirimWA.isEnabled = false
            } else {
                this.teksEditNomorTelepon.error = null
                this.tombolKirimWA.isEnabled = true
            }
        }

//        TODO: Buat fungsi kirim kode OTP di sini


        this.tombolKirimWA.setOnClickListener {
            val intent = Intent(this, VerificationActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun cekNomorTelepon(nomorTelepon: CharSequence?): Boolean {
        if (nomorTelepon == null) {
            return false
        }
        if (nomorTelepon.length < 9 || nomorTelepon.length > 20) {
            return false
        }
        if (!Patterns.PHONE.matcher(nomorTelepon).matches()) {
            return false
        }
        return true
    }
}