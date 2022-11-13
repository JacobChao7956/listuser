package com.listuser

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class UserViewModel(app: Application) : AndroidViewModel(app) {

    private val userRepo = UserRepo()
    var users = userRepo.users

    fun getUserList() {
        viewModelScope.launch {
            userRepo.getUser().flowOn(Dispatchers.IO).collect {
                users.value = it.body()
            }
        }
    }
}