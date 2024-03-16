package com.example.parcial.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.parcial.data.model.UserModel
import com.example.parcial.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val UserRepository = UserRepository(application)

    private val _loginResult = MutableLiveData<Boolean>()
    val loginresult: LiveData<Boolean>
        get() = _loginResult

    private val _UserSaved = MutableLiveData<Boolean>()
    val UserSaved: LiveData<Boolean>
        get() = _UserSaved

    private val _userList = MutableLiveData<List<UserModel>>()
    val userList: LiveData<List<UserModel>>
        get() = _userList

    private val _userForUid=MutableLiveData<UserModel>()

    val userForUid:LiveData<UserModel>
        get()=_userForUid

    private val _updateUser = MutableLiveData<Int>()
    val updateUser:LiveData<Int>
        get()=_updateUser

    fun validateLogin(email: String, password: String) {
        viewModelScope.launch {
            val isValidLogin = UserRepository.validateLogin(email, password)
            _loginResult.value = isValidLogin
        }
    }

    fun getUserForIud(uid:Long){
        viewModelScope.launch {
            val user=UserRepository.getUserForUid(uid)
            _userForUid.value=user
        }
    }
//hay dos usuarios
    fun saveUser(
        email: String,
        password: String,
        names: String,
        phone: String
    ) {
        viewModelScope.launch {
            UserRepository.saveUser(email, password, names, phone)
            _UserSaved.value = true
        }

    }

    fun getUsers() {
        viewModelScope.launch {
            val users = UserRepository.getUsers()
            _userList.value = users
        }
    }
    fun updateUser(user:UserModel){
        viewModelScope.launch {
            val id=UserRepository.update(user)
            _updateUser.value = id

        }
    }


    //  mas nada ahora toca la actividad , asi es no lo borre , al parecer tiene un error el registro testea


}
