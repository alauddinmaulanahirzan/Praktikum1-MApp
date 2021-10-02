package com.example.praktikum1_mapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val nama = intent.getStringExtra("nama")
        val email = intent.getStringExtra("email")

        var txtNama = findViewById<TextView>(R.id.txtNama)
        var txtEmail = findViewById<TextView>(R.id.txtEmail)
        var btnUbahData = findViewById<Button>(R.id.btnUbahData)
        var btnHapusAkun = findViewById<Button>(R.id.btnHapusAkun)
        var btnKeluar = findViewById<Button>(R.id.btnKeluar)

        txtNama.setText("Nama : "+nama)
        txtEmail.setText("E-Mail : "+email)

    }
}