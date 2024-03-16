package com.example.parcial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.parcial.R
import com.example.parcial.data.model.UserModel
import com.example.parcial.ui.viewmodels.UserViewModel
import com.example.parcial.util.Common


class Update : AppCompatActivity() {
    lateinit var ucorreo: EditText
    lateinit var ucontraseña: EditText
    lateinit var unombres: EditText
    lateinit var utelefono: EditText
    private lateinit var userViewModel: UserViewModel
    private  var uid:Long = 0
    private var tag:String="Update"
    private lateinit var userr:UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        ucorreo = findViewById(R.id.correoupdate)
        ucontraseña =findViewById(R.id.passupdate)
        unombres =findViewById(R.id.nombresupdate)
        utelefono =findViewById(R.id.numcelupdate)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        intent.getStringExtra("id_user").let {
            if (it.isNullOrEmpty()) {
                Log.d(tag,"it $it")
                Common.showToast(this, "ocurrio un error al mostrar los datos de dicho usuario")
            } else {
                Log.d(tag,"entrastes")
                uid = it.toLong()
                userViewModel.getUserForIud(uid)

            }

        }

        userViewModel.userForUid.observe(this) { user ->
            userr=user
            ucorreo.setText(user.email)
            ucontraseña.setText(user.password)
            unombres.setText(user.names)
            utelefono.setText(user.phone.toString() )

            Log.d(tag,"user $user")

        }

        userViewModel.updateUser.observe(this){
                isUpdate->
            Log.d(tag,"isUpdate $isUpdate")

        }

        intent.getStringExtra("correo")?.let {
            ucorreo.setText("$it")
        }

        intent.getStringExtra("pass")?.let {
            ucontraseña.setText("$it")
        }

        intent.getStringExtra("nombres")?.let {
            unombres.setText("$it")
        }

        intent.getStringExtra("telefono")?.let {
            utelefono.setText("$it")
        }

        val btnregback= findViewById<ImageView>(R.id.update)
        btnregback.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java )
            startActivity(intent)

        }


        val btnupdate = findViewById<Button>(R.id.registerupdate)
        btnupdate.setOnClickListener{
            val correo=ucorreo.text.toString()
            val pass=ucontraseña.text.toString()
            val nombres=unombres.text.toString()
            val telefono=utelefono.text.toString()
            if (correo.isEmpty() || pass.isEmpty() || nombres.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(this, "[ERROR]: deben estar llenos todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                ucorreo.text.clear()
                ucontraseña.text.clear()
                unombres.text.clear()
                utelefono.text.clear()
                val userr=UserModel(userr.uid,correo,pass,nombres,telefono)
                userViewModel.updateUser(userr)
                val intent = Intent(this, Profile::class.java)
                /*intent.putExtra("correo",correo)
                intent.putExtra("pass",pass)
                intent.putExtra("nombres",nombres)
                intent.putExtra("telefono",telefono)*/
                startActivity(intent)


            }
        }
    }
}