package com.example.parcial.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.parcial.R
import com.example.parcial.ui.viewmodels.UserViewModel
import com.example.parcial.util.Common
import com.example.parcial.util.Common.Companion.showToast

class Register : AppCompatActivity() {

    lateinit var correo: EditText
    lateinit var contraseña: EditText
    lateinit var nombres: EditText
    lateinit var telefono: EditText
    lateinit var btnregistrar: Button
    private lateinit var userViewModel : UserViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        userViewModel=ViewModelProvider(this).get(UserViewModel::class.java)

        correo = findViewById(R.id.correoreg)
        contraseña = findViewById(R.id.passreg)
        nombres = findViewById(R.id.nombrereg)
        telefono = findViewById(R.id.telreg)
        btnregistrar = findViewById(R.id.regiss)

        /*btnregistrar.setOnClickListener {
            val correo=correo.text.toString()
            val pass=contraseña.text.toString()
            val nombres=nombres.text.toString()
            val telefono=telefono.text.toString()

            if (correo.isEmpty() || pass.isEmpty() || nombres.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "[ERROR]: debe llenar todos los campos", Toast.LENGTH_SHORT).show()
            }else{

                val intent = Intent(this,Profile::class.java)
                intent.putExtra("correo",correo)
                intent.putExtra("pass",pass)
                intent.putExtra("nombres",nombres)
                intent.putExtra("telefono",telefono)
                startActivity(intent)


            }

        }*/

        btnregistrar.setOnClickListener {
            val correo = correo.text.toString()
            val pass = contraseña.text.toString()
            val nombres = nombres.text.toString()
            val telefono = telefono.text.toString()

            if (correo.isEmpty() || pass.isEmpty() || nombres.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "[ERROR]: Debes llenar todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // validacion para que  el nombre tenga entre 3 y 15 caracteres el nombre
                if (nombres.replace(" ", "").length < 3 || !nombres.matches("[a-zA-Z\\s]+".toRegex())) {
                    Toast.makeText(this, "[ERROR]: El nombre debe tener entre 3 caracteres y solo letras", Toast.LENGTH_SHORT).show()
                } else if (!correo.contains("@") || !correo.contains(".") || correo.indexOf("@") > correo.lastIndexOf(".")) {
                    // Validacion para que el correo tenga  @ y un . Y QUE EL @ ESSTE ANTES DEL .
                    //IMPORTANTE QUE EL ARROBA ESTE ANTES DEL PUNTO!!
                    Toast.makeText(this, "[ERROR]: Correo electrónico inválido", Toast.LENGTH_SHORT).show()
                } else if (!pass.matches(".*\\d{4,}.*".toRegex())) {
                    // validacion contrase'a con almenos 4 numeros
                    Toast.makeText(this, "[ERROR]: La contraseña debe contener al menos 4 números", Toast.LENGTH_SHORT).show()
                } else if (telefono.length < 10 || telefono.length >  10 || !telefono.matches("[0-9]+".toRegex())) {
                    // validar que el telefono tenga 10 digitos y que solo tenga numeros
                    Toast.makeText(this, "[ERROR]: El número de teléfono debe tener al menos 10 dígitos y solo contener números", Toast.LENGTH_SHORT).show()
                } else {
                    // Todos los campos están llenos y pasaron las validaciones, puedes proceder con la lógica de tu aplicación
                   //val intent = Intent(this, Profile::class.java)
                    //intent.putExtra("correo", correo)
                    //intent.putExtra("pass", pass)
                    //intent.putExtra("nombres", nombres)
                    //intent.putExtra("telefono", telefono)
                    //startActivity(intent)

                    saveUser(correo,pass,nombres,telefono)
                    showToast(getString(R.string.txt_successful_register))
                    saveCredentials(correo,pass)
                    goToProfile()
                }
            }
        }


        val btnreg= findViewById<ImageView>(R.id.registrar)
        btnreg.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)

        }


        val btnallog = findViewById<Button>(R.id.button4)
        btnallog.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }
    }
    private fun saveUser(
        correo: String,
        pass: String,
        nombres: String,
        telefono: String,
    ) {
        userViewModel.saveUser(correo, pass, nombres, telefono)
    }
    private fun showToast(message: String) {
        Common.showToast(this, message)
    }

    private fun isValidInput(
        correo: String,
        pass: String,
        nombres: String,
        telefono: String,
    ): Boolean {
        if (correo.isEmpty() || pass.isEmpty() || nombres.isEmpty() ||  telefono.isEmpty()) {
            showToast(getString(R.string.txt_validar_datos))
            return false
        }
        return true
    }

    private fun saveCredentials(correo: String, pass: String) {
        val sharedPreferences = getSharedPreferences("ra2", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user", correo)
        editor.putString("password", pass)
        editor.apply()
    }
    private fun goToProfile() {
        val intent = Intent(this, Profile::class.java)
        startActivity(intent)
    }
    //error de compilacion
}