package com.example.parcial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.parcial.R

class Forgotpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgotpass)

        val btnlog = findViewById<ImageView>(R.id.forgotpss)
        btnlog.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }
    }
}