package com.example.herobio

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var et_user_name = findViewById(R.id.et_user_name) as EditText
        var et_password = findViewById(R.id.et_password) as EditText
        var btn_submit = findViewById(R.id.btn_submit) as Button

        btn_submit.setOnClickListener {
            val user_name = et_user_name.text;
            val password = et_password.text;
            AlertDialog.Builder(this)
                .setTitle("Login")
                .setMessage("Anda Berhasil Login")
                .setPositiveButton("Ya", DialogInterface.OnClickListener {
                        dialogInterface, i ->
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("No", DialogInterface.OnClickListener {
                        dialogInterface, i ->
                    Toast.makeText(this, "Jalankan perintah ketika user memilih tombol No", Toast.LENGTH_LONG).show()
                })
                .show()
        }
    }
}


