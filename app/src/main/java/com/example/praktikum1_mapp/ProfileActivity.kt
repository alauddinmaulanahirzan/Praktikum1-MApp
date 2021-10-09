package com.example.praktikum1_mapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

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

        btnUbahData.setOnClickListener {
            val formUbahData = Intent(this,FormUbah::class.java)
            formUbahData.putExtra("nama",nama)
            formUbahData.putExtra("email",email)
            startActivityForResult(formUbahData,0)
        }
        btnHapusAkun.setOnClickListener {

        }
        btnKeluar.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Check that it is the SecondActivity with an OK result
        if (requestCode == 0)
            if (resultCode == Activity.RESULT_OK)
                Toast.makeText(this,"Data Telah Diperbarui",Toast.LENGTH_SHORT).show()
            else if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this,"User Membatalkan Aksi",Toast.LENGTH_SHORT).show()
        }
    }
}