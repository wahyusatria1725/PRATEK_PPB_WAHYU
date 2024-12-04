package com.example.apppertama

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val dataNim: String= intent.getStringExtra("nim").toString()
        val dataNama: String= intent.getStringExtra("nama").toString()
        val dataEmail: String= intent.getStringExtra("email").toString()

        val tvNIM: TextView = findViewById(R.id.tvNIM)
        val tvNAMA: TextView = findViewById(R.id.tvNAMA)
        val tvEMAIL: TextView = findViewById(R.id.tvEMAIL)

        tvNIM.setText(dataNim)
        tvNAMA.setText(dataNama)
        tvEMAIL.setText(dataEmail)



        val btnprofileupdate: Button = findViewById(R.id.btnprofileupdate)
        val btnprofilekeluar: Button = findViewById(R.id.btnprofilekeluar)

        btnprofilekeluar.setOnClickListener {
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnprofileupdate.setOnClickListener {
            val intent: Intent = Intent(this,UpdateprofileActivity::class.java)
            intent.putExtra("nim", dataNim)
            intent.putExtra("nama", dataNama)
            intent.putExtra("email", dataEmail)
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.btnkeluar)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}