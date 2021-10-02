package com.example.praktikum1_mapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

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

        database = Firebase.database.reference

        // Button Action
        btnMasuk.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()
            if(email.equals("")||password.equals(""))
                Toast.makeText(applicationContext,"Kolom Data Kosong",Toast.LENGTH_SHORT).show()
            else
            {
                val userId = "0"
                database.child("users")
                    .child(userId)
                    .addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(p0: DataSnapshot) {
                            val userData = p0.getValue(UserInfo::class.java)
                            if(userData == null)
                                Toast.makeText(applicationContext,"Database Kosong",Toast.LENGTH_SHORT).show()
                            else
                            {
                                val resultNama = userData.nama.toString()
                                val resultEmail = userData.email.toString()
                                val resultPassword = userData.password.toString()

                                if(email.equals(resultEmail)&&password.equals(resultPassword))
                                {
                                    Toast.makeText(applicationContext,"Selamat Datang "+resultNama,Toast.LENGTH_SHORT)
                                        .show()
                                    val profile = Intent(applicationContext,ProfileActivity::class.java)
                                    profile.putExtra("nama",resultNama)
                                    profile.putExtra("email",resultEmail)
                                }
                                else
                                    Toast.makeText(applicationContext,"Data Login Salah",Toast.LENGTH_SHORT)
                                        .show()
                            }
                        }

                        override fun onCancelled(p0: DatabaseError) {
                            TODO("Not yet implemented")
                        }

                    })
            }

        }

        btnBersihkan.setOnClickListener {
            editEmail.setText("")
            editPassword.setText("")
        }

        btnDaftar.setOnClickListener {
            val formDaftar = Intent(this,FormDaftar::class.java)
            startActivity(formDaftar)
        }

        btnKeluar.setOnClickListener {
            finish()
        }
    }
}