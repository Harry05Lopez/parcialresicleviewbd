package com.example.parcial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.parcial.R
import android.util.Patterns
import androidx.lifecycle.ViewModelProvider
import com.example.parcial.ui.viewmodels.UserViewModel
import com.example.parcial.util.Common

class Login : AppCompatActivity() {

    lateinit var correo:EditText
    lateinit var contraseña:EditText
    lateinit var btninisiosesion: Button

    private lateinit var userViewModel :UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        validateSession()

        correo = findViewById(R.id.correolog)
        contraseña = findViewById(R.id.passlog)
        btninisiosesion = findViewById(R.id.inisiarlog)

        userViewModel.loginresult.observe(this) { isValidLogin ->
            if (isValidLogin) {
                val email = correo.text.toString()
                val pass = contraseña.text.toString()
                saveSession(email, pass)
                goProfile()
            } else {
                Common.showToast(this, "Credenciales incorectas")
            }
        }

        btninisiosesion.setOnClickListener{
            val correo = correo.text.toString()
            val contrasena = contraseña.text.toString()



            if (correo.isEmpty() || contrasena.isEmpty()){
                Toast.makeText(this, "[ERROR]: Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                Toast.makeText(this, "[ERROR]: Correo electrónico inválido", Toast.LENGTH_SHORT).show()
            }
            else if (!contrasena.matches(".*\\d{4,}.*".toRegex())){
                Toast.makeText(this, "[ERROR]: La contraseña debe contener al menos 4 números", Toast.LENGTH_SHORT).show()
            }
            else {
                userViewModel.validateLogin(correo, contrasena)

            }
            //!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") > correo.lastIndexOf(".")

        }


        val btnlog = findViewById<ImageView>(R.id.log)
        btnlog.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)
        }

        val btnolpass = findViewById<Button>(R.id.olvidopass)
        btnolpass.setOnClickListener{
            val intent = Intent(this, Forgotpass::class.java )
            startActivity(intent)
        }


        val btnregistrate = findViewById<Button>(R.id.restrate)
        btnregistrate.setOnClickListener{
            val intent = Intent(this, Register::class.java )
            startActivity(intent)
        }

    }

    private fun validateSession(){
        val sp = getSharedPreferences("parcial", MODE_PRIVATE)
        val correo = sp.getString("correo","")
        val contraseña = sp.getString("pass","")

        if (correo != null && contraseña != null){
            if (correo.isNotEmpty() && contraseña.isNotEmpty()){
                goProfile()
            }

        }
    }

    private fun goProfile(){
        val intent = Intent(this, Profile::class.java)
        intent.putExtra("correo", "")
        intent.putExtra("pass", "")
        intent.putExtra("nombres", "")
        intent.putExtra("telefono", "")
        startActivity(intent)
    }

    private fun saveSession(correo: String, contraseña: String) {
        val sp = getSharedPreferences("parcial", MODE_PRIVATE)
        val edit = sp.edit()
        edit.putString("correo", correo)
        edit.putString("pass", contraseña)
        edit.apply()
    }

}