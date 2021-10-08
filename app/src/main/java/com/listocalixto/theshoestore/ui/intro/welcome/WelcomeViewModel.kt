package com.listocalixto.theshoestore.ui.intro.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _eventNextFragment = MutableLiveData<Boolean>()
    val eventNextFragment: LiveData<Boolean>
    get() = _eventNextFragment

    init {
        _eventNextFragment.value = false
    }

    fun onNextFragment() {
        _eventNextFragment.value = true
    }

    fun eventNavigateFinish() {
        _eventNextFragment.value = false
    }

}