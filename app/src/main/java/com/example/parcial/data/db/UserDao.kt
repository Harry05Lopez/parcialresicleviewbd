package com.example.parcial.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.parcial.data.model.UserModel
import kotlinx.coroutines.selects.select


@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE correo = :email  AND  contrasena = :pass")
    fun getUserByCredentials(email: String, pass: String) : UserModel?

    @Query("select * from user where uid=:uid")
    fun getUserForUid(uid:Long):UserModel

    @Query("SELECT * FROM user")
    fun getUser(): List<UserModel>

    @Insert
    fun insert(user: UserModel)

    @Delete
    fun delete(user: UserModel)
@Update
fun update(user: UserModel):Int


}