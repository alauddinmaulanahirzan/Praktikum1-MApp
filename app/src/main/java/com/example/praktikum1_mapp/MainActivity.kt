package com.example.praktikum1_mapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // EditText
        var editEmail = findViewById<EditText>(R.id.editEmail)
        var editPassword = findViewById<EditText>(R.id.editPassword)

        // Button
        var btnMasuk = findViewById<Button>(R.id.btnMasuk)
        var btnBersihkan = findViewById<Button>(R.id.btnBersihkan)
        var btnDaftar = findViewById<Button>(R.id.btnDaftar)
        var btnKeluar = findViewById<Button>(R.id.btnKeluar)

        // Button Action
        btnMasuk.setOnClickListener {

        }
        btnBersihkan.setOnClickListener {
            editEmail.setText("")
            editPassword.setText("")
        }
        btnDaftar.setOnClickListener {
            
        }
        btnKeluar.setOnClickListener {
            finish()
        }
    }
}