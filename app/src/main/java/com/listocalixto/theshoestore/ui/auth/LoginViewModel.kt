package com.listocalixto.theshoestore.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _eventUserApproved = MutableLiveData<Boolean>()
    val eventUserApproved: LiveData<Boolean>
        get() = _eventUserApproved

    init {
        _eventUserApproved.value = false
    }

    fun onUserApproved() {
        _eventUserApproved.value = true
    }

    fun onUserLogIn() {
        _eventUserApproved.value = false
    }

}