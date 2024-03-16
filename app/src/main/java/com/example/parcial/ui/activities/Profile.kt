package com.example.parcial.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial.R
import com.example.parcial.adapters.UserAdapter
import com.example.parcial.ui.viewmodels.UserViewModel
import com.example.parcial.util.Common

class Profile : AppCompatActivity() {

    private val tag = "Profile"
    //lateinit var bntllamar:Button
    //lateinit var btnemail:Button
    //lateinit var btnmensaje:Button
    private lateinit var back_login: ImageView


    private lateinit var userViewModel: UserViewModel
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        back_login = findViewById(R.id.perfil)

       recyclerView = findViewById(R.id.recycleView)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.userList.observe(this){ userList ->
            if (userList != null) {
                Log.d(tag, "onCreate:$userList")
                val adapter= UserAdapter(userList)
                recyclerView.adapter=adapter
            } else {
                Common.showToast(this, "hubo un error al obtener el usuario")
            }
        }
        userViewModel.getUsers()
        val receivedIntent = intent

        val pcorreo = receivedIntent.getStringExtra("correo")
        val pcontraseña = receivedIntent.getStringExtra("pass")
        val pnombres = receivedIntent.getStringExtra("nombres")
        val ptelefono = receivedIntent.getStringExtra("telefono")

        //bntllamar= findViewById(R.id.llamar)
        //btnemail= findViewById(R.id.email)
        //btnmensaje= findViewById(R.id.mensaje)

        /*val btnlog = findViewById<ImageView>(R.id.perfil)
        btnlog.setOnClickListener{
            val intent = Intent(this, Login::class.java )
            startActivity(intent)
        }*/

        /*btnemail.setOnClickListener{
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:${pcorreo}")
            intent.putExtra(Intent.EXTRA_SUBJECT,"Asunto correo")
            intent.putExtra(Intent.EXTRA_TEXT, "Cuerpo correo")
            startActivity(intent)

        }*/

        /*bntllamar.setOnClickListener(){
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${ptelefono}")
            startActivity(intent)
        }*/

        /*btnmensaje.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("sms:${ptelefono}")
            intent.putExtra("sms_body", "hola como estas?")
            startActivity(intent)
        }*/

        /*val btneditar = findViewById<Button>(R.id.editarperfil)
        btneditar.setOnClickListener{
            val intent = Intent(this, Update::class.java )
            intent.putExtra("correo",pcorreo)
            intent.putExtra("pass",pcontraseña)
            intent.putExtra("nombres",pnombres)
            intent.putExtra("telefono",ptelefono)
            startActivity(intent)
        }*/

        val btnlogout = findViewById<Button>(R.id.llamar)
        btnlogout.setOnClickListener {
            logOut()
        }

        back_login.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }



    }

    private fun logOut(){
        closeSession()
        goLogin()
    }

    private fun goLogin(){
        val intent = Intent(this, Login::class.java)
        startActivity(intent)
    }

    private fun closeSession(){
        val sp = getSharedPreferences( "parcial", MODE_PRIVATE)
        val edit = sp.edit()
        edit.remove("correo")
        edit.remove("pass")
        edit.remove("nombres")
        edit.remove("telefono")
        edit.apply()

    }
}