package com.example.parcial.data.repository

import android.content.Context
import com.example.parcial.data.db.AppDatabase
import com.example.parcial.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(context: Context) {

    private val database = AppDatabase.getInstance(context)

    suspend fun validateLogin(email:String, password:String):Boolean{
        return withContext(Dispatchers.IO){
            val userFromDb = database.userDao(). getUserByCredentials(email,password)
            userFromDb!= null
        }
    }

    suspend fun  getUsers(): List<UserModel>{
        return withContext(Dispatchers.IO){
            database.userDao().getUser()
        }
    }

    suspend fun getUserForUid(uid: Long): UserModel {
        return withContext(Dispatchers.IO) {
            database.userDao().getUserForUid(uid)


        }
    }
    suspend fun update(user:UserModel): Int {
        return withContext(Dispatchers.IO) {
       database.userDao().update(user)



        }
    }


    suspend fun saveUser(
        email: String,
        password: String,
        names: String,
        phone: String
    ){
        withContext(Dispatchers.IO){
            val user=UserModel(
                email= email,
                password= password,
                names= names,
                phone= phone
            )
            database.userDao().insert(user)
        }
    }



}