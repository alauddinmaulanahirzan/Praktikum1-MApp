package com.example.praktikum1_mapp

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

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

        database = Firebase.database.reference

        txtNama.setText("Nama : "+nama)
        txtEmail.setText("E-Mail : "+email)

        btnUbahData.setOnClickListener {
            val formUbahData = Intent(this,FormUbah::class.java)
            formUbahData.putExtra("nama",nama)
            formUbahData.putExtra("email",email)
            startActivityForResult(formUbahData,0)
        }
        btnHapusAkun.setOnClickListener {
            val buildMessage = AlertDialog.Builder(this@ProfileActivity)
            val input = EditText(this)
            input.setHint("Masukkan Password")
            input.inputType = InputType.TYPE_CLASS_TEXT
            buildMessage.setView(input)
            buildMessage.setMessage("Apakah Kamu yakin menghapus "+nama+"?")
                .setTitle("Peringatan!")
                .setCancelable(false)
                .setPositiveButton("Ya") {dialog, id ->
                    val queryNama = database.child("users")
                        .child("0")
                        .orderByChild("email")
                        .equalTo(email)

                    queryNama.addListenerForSingleValueEvent(object:ValueEventListener{})
                }
                .setNegativeButton("Tidak") { dialog, id -> }
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