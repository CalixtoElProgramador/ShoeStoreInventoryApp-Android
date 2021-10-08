package com.listocalixto.theshoestore.ui.intro.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _eventToList = MutableLiveData<Boolean>()
    val eventToList: LiveData<Boolean>
        get() = _eventToList

    init {
        _eventToList.value = false
    }

    fun onNextFragment() {
        _eventToList.value = true
    }

    fun eventNavigateFinish() {
        _eventToList.value = false
    }

}