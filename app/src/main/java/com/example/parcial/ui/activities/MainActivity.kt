package com.example.parcial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.parcial.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnlog = findViewById<Button>(R.id.log)
        btnlog.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }

        val btnreg = findViewById<Button>(R.id.registrar)
        btnreg.setOnClickListener{
            val intent = Intent(this, Register::class.java )
            startActivity(intent)
        }


    }
}