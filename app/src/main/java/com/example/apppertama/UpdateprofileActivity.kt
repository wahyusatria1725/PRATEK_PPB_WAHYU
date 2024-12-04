package com.example.apppertama

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apppertama.R.id.btnkeluar
import com.example.apppertama.R.id.btnprofileupdate
import com.example.apppertama.R.id.editpassword
import com.example.apppertama.R.id.textViewEdit4

class UpdateprofileActivity : AppCompatActivity() {

    private lateinit var databaseDBHalper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_updateprofile)

        databaseDBHalper = DBHelper(context = this);

        val datanim: String = intent.getStringExtra("nim").toString()
        val datanama: String = intent.getStringExtra("nama").toString()
        val dataemail: String = intent.getStringExtra("email").toString()

        val editnim = findViewById<EditText>(R.id.editnim)
        val editnama = findViewById<EditText>(R.id.editnama)
        val editemail = findViewById<EditText>(R.id.editemail)
        val editpassword = findViewById<EditText>(R.id.editpassword)

        editnim.isEnabled = false

        editnim.setText(datanim)
        editnama.setText(datanama)
        editemail.setText(dataemail)

        val btnkonfrimasi: Button = findViewById(R.id.btnkonfirmasi)

        var newnama: String = ""
        var newemail: String = ""
        var newpassword: String = ""


        btnkonfrimasi.setOnClickListener {

            newnama = editnama.text.toString()
            newemail = editemail.text.toString()
            newpassword = editpassword.text.toString()

            if (newnama.equals("") || newemail.equals("")) {
                Toast.makeText(applicationContext, "data tidak boleh kosong", Toast.LENGTH_LONG)
                    .show()
            } else {

                val db = databaseDBHalper.writableDatabase
                var updateValues: ContentValues? = null

                if (newpassword.equals("")) {
                    updateValues = ContentValues().apply {
                        put("nama", newnama)
                        put("email", newemail)
                    }
                } else {
                    updateValues = ContentValues().apply {
                        put("nama", newnama)
                        put("email", newemail)
                        put("password", newpassword)
                    }
                }

                val result = db.update("TBL_MHS", updateValues, "nim=?", arrayOf(datanim))

                if (result > 0) {
                    Toast.makeText(applicationContext, "Update Berhasil", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(applicationContext, "Update Gagal", Toast.LENGTH_LONG).show()
                }
            }
        }

        val btneditbatal : Button = findViewById(R.id.btneditbatal)
        btneditbatal.setOnClickListener {
            val intent: Intent = Intent(this,ProfileActivity::class.java)
            intent.putExtra("nim", datanim)
            intent.putExtra("nama", newnama)
            intent.putExtra("email", newemail)
            startActivity(intent)
        }

            ViewCompat.setOnApplyWindowInsetsListener(findViewById(textViewEdit4)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }




