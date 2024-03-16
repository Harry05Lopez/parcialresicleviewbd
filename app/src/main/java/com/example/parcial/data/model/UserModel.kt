package com.example.parcial.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//la linea 8 es el nombre de la tabla
@Entity(tableName = "user")
data class UserModel(
    //la linea de abajo es xomo crear lprimary key autogeneable
    @PrimaryKey(autoGenerate = true)
    val uid:Long? = null,

    //las lineas sub siguientes son las columnas y el nombre de la variable de la columna
    @ColumnInfo("correo")
    val email:String?,

    @ColumnInfo("contrasena")
    val password:String?,

    @ColumnInfo("nombres")
    val names:String?,

    @ColumnInfo("telefono")
    val phone:String?,

)
