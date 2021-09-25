package com.example.praktikum1_mapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FormDaftar : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_daftar)

        // Inisialisasi
        var editNamaPenuh = findViewById<EditText>(R.id.editNamaPenuh)
        var editEmail = findViewById<EditText>(R.id.editEmail)
        var editPassword1 = findViewById<EditText>(R.id.editPassword1)
        var editPassword2 = findViewById<EditText>(R.id.editPassword2)

        var btnDaftar = findViewById<Button>(R.id.btnDaftar)
        var btnBersihkan = findViewById<Button>(R.id.btnBersihkan)
        var btnBatalkan = findViewById<Button>(R.id.btnBatalkan)

        database = Firebase.database.reference

        // Kode Action
        btnDaftar.setOnClickListener {
            val Nama = editNamaPenuh.text.toString()
            val Email = editEmail.text.toString()
            val Pass1 = editPassword1.text.toString()
            val Pass2 = editPassword2.text.toString()

            if(Nama.equals("")||Email.equals("")||Pass1.equals("")||Pass2.equals(""))
                Toast.makeText(applicationContext,"Terdapat Kolom Kosong",Toast.LENGTH_SHORT).show()
            else
            {
                if(Pass1.equals(Pass2))
                {
                    // Tulis Database
                    val user = UserInfo(Nama,Email,Pass1)
                    val userId = "0"
                    database.child("users")
                        .child(userId)
                        .setValue(user)
                        .addOnCompleteListener {
                            task ->
                            if (task.isSuccessful())
                            {
                                Toast.makeText(applicationContext,"Akun Berhasil Ditambahkan",Toast.LENGTH_SHORT)
                                finish()
                            }
                            else
                                Toast.makeText(applicationContext,"Akun Gagal Ditambahkan",Toast.LENGTH_SHORT)
                        }
                }
                else
                {
                    Toast.makeText(applicationContext,"Password Tidak Sama",Toast.LENGTH_SHORT).show()
                    editPassword1.setText("")
                    editPassword2.setText("")
                }
            }
        }
        btnBersihkan.setOnClickListener {
            editNamaPenuh.setText("")
            editEmail.setText("")
            editPassword1.setText("")
            editPassword2.setText("")
        }
        btnBatalkan.setOnClickListener {
            finish()
        }
    }
}