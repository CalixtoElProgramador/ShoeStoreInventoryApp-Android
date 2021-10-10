package com.listocalixto.theshoestore.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.listocalixto.theshoestore.app.AppConstants.USER_EMAIL
import com.listocalixto.theshoestore.app.AppConstants.USER_PASSWORD

class LoginViewModel : ViewModel() {

    private val _eventUserApproved = MutableLiveData<Boolean>()
    val eventUserApproved: LiveData<Boolean>
        get() = _eventUserApproved

    private val _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    init {
        _eventUserApproved.value = false
        _showSnackbarEvent.value = false
    }

    fun onUserApproved() {
        _eventUserApproved.value = true
    }

    fun onUserLogIn() {
        _eventUserApproved.value = false
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onLogIn() {
        when {
            email.value == USER_EMAIL && password.value == USER_PASSWORD -> onUserApproved()
            else -> _showSnackbarEvent.value = true
        }
    }

}