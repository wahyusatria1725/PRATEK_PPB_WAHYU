package com.example.apppertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var databaseDBHalper:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        var inputUsername = findViewById<EditText>(R.id.inputUsername)
        var inputPassword = findViewById<EditText>(R.id.inputPassword)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnBersih = findViewById<Button>(R.id.btnBersih)
        val btnDaftar = findViewById<Button>(R.id.btnDaftar)
        val btnKeluar1 = findViewById<Button>(R.id.btnkeluar1)

        databaseDBHalper = DBHelper(context = this);

        btnSubmit.setOnClickListener {
            var Username = inputUsername.text.toString()
            var Password = inputPassword.text.toString()

        val query = "SELECT * FROM TBL_MHS WHERE Nim='" + Username + "'AND Password='" + Password + "'"

        val db = databaseDBHalper.readableDatabase
        val cursor = db.rawQuery(query, null)

        val result = cursor.moveToFirst()
        if (result == true){
            Toast.makeText(applicationContext, "login berhasil", Toast.LENGTH_LONG).show()

            val dataNim = cursor.getString(cursor.getColumnIndexOrThrow("nim"))
            val dataNama = cursor.getString(cursor.getColumnIndexOrThrow("nama"))
            val dataEmail = cursor.getString(cursor.getColumnIndexOrThrow("email"))


            val intent: Intent = Intent(this,ProfileActivity::class.java)
            intent.putExtra("nim", dataNim)
            intent.putExtra("nama", dataNama)
            intent.putExtra("email", dataEmail)
            startActivity(intent)
        }else{
            Toast.makeText(applicationContext, "Login Gagal", Toast.LENGTH_LONG).show()
        }
        }

        btnDaftar.setOnClickListener {
            val intent: Intent = Intent(this, DaftarActivity::class.java)
            startActivity(intent)
        }

        btnKeluar1.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnkeluar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}