package com.example.otpwhatsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var tombolLogin: Button
    private lateinit var tombolVerifikasi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.tombolLogin = this.findViewById(R.id.tombolLogin)
        this.tombolLogin.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
        }

        this.tombolVerifikasi = this.findViewById(R.id.tombolVerifikasi)
        this.tombolVerifikasi.setOnClickListener{
            val intent = Intent(this, VerificationActivity::class.java)
            this.startActivity(intent)
        }
    }
}