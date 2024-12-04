package com.example.apppertama

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.intellij.lang.annotations.Pattern

class DaftarActivity : AppCompatActivity() {

    private lateinit var databaseDBHalper:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_daftar)


        val btnbatal = findViewById<Button>(R.id.btnbatal)
        val btndaftar = findViewById<Button>(R.id.btndaftar)
        val btnbersih = findViewById<Button>(R.id.btnbersih)

        var inputnim = findViewById<EditText>(R.id.inputnim)
        var inputnama = findViewById<EditText>(R.id.inputnama)
        var inputemail = findViewById<EditText>(R.id.inputemail)
        var inputpassword = findViewById<EditText>(R.id.inputpassword)


        databaseDBHalper = DBHelper(context = this);


        btnbatal.setOnClickListener{
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btnbersih.setOnClickListener {
            inputnim.setText("")
            inputnama.setText("")
            inputemail.setText("")
            inputpassword.setText("")
        }

        btndaftar.setOnClickListener {
            if (inputnim.text.toString().equals("") ||
                inputnama.text.toString().equals("") ||
                inputemail.text.toString().equals("") ||
                inputpassword.text.toString().equals("")) {
                Toast.makeText(applicationContext, "input tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(inputemail.text.toString()).matches()){
                Toast.makeText(applicationContext,"input email anda salah", Toast.LENGTH_SHORT).show()
            }

            val db = databaseDBHalper.readableDatabase
            val insertValues = ContentValues().apply {
                put("nim", inputnim.text.toString())
                put("nama", inputnama.text.toString())
                put("email", inputemail.text.toString())
                put("password", inputpassword.text.toString())
            }
            val result = db.insert("TBL_MHS", null, insertValues)
            db.close()

            if(result != -1L) {
                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext,"Data gagal disimpan", Toast.LENGTH_SHORT).show()
            }
        }

        btnbatal.setOnClickListener {
            val intent: Intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        }

    }
