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

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

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

    fun onLogIn(email: String, password: String) {
        when {
            email == USER_EMAIL && password == USER_PASSWORD -> {
                _eventUserApproved.value = true
            }
            else -> {
                _showSnackbarEvent.value = true
            }
        }
    }

}