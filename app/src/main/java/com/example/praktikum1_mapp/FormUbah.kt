package com.example.praktikum1_mapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FormUbah : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_ubah)

        // Inisialisasi
        var editNama = findViewById<EditText>(R.id.editNama)
        var editEmail = findViewById<EditText>(R.id.editEmail)
        var editPassword = findViewById<EditText>(R.id.editPassword)

        val nama = intent.getStringExtra("nama").toString()
        val email = intent.getStringExtra("email").toString()
        editNama.setText(nama)
        editEmail.setText(email)

        var btnUbahData = findViewById<Button>(R.id.btnUbahData)
        var btnBersihkan = findViewById<Button>(R.id.btnBersihkan)
        var btnBatalkan = findViewById<Button>(R.id.btnBatalkan)

        database = Firebase.database.reference

        btnUbahData.setOnClickListener {
            val Nama = editNama.text.toString()
            val Email = editEmail.text.toString()
            val Pass = editPassword.text.toString()
            if(Nama.equals("")||Email.equals("")||Pass.equals(""))
                Toast.makeText(applicationContext,"Kolom Data Kosong",Toast.LENGTH_SHORT).show()
            else
            {
                val user = UserInfo(Nama,Email,Pass)
                val userId = "0"
                database.child("users")
                    .child(userId)
                    .setValue(user)
                    .addOnCompleteListener {
                            task ->
                        if (task.isSuccessful()) {
                            Toast.makeText(applicationContext,"Akun Berhasil Diubah",Toast.LENGTH_SHORT)
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                        else
                        {
                            Toast.makeText(applicationContext,"Akun Gagal Diubah",Toast.LENGTH_SHORT)
                            setResult(Activity.RESULT_CANCELED)
                            finish()
                        }
                    }
            }
        }
        btnBersihkan.setOnClickListener {
            editNama.setText("")
            editEmail.setText("")
            editPassword.setText("")
        }
        btnBatalkan.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}