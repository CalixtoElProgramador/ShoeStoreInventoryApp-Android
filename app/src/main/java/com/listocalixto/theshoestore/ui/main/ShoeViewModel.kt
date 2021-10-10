package com.listocalixto.theshoestore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.listocalixto.theshoestore.data.model.Shoe

class ShoeViewModel : ViewModel() {

    private lateinit var list: MutableList<Shoe>

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _eventAddShoe = MutableLiveData<Boolean>()
    val eventAddShoe: LiveData<Boolean>
        get() = _eventAddShoe

    private val _eventToDetails = MutableLiveData<Boolean>()
    val eventToDetails: LiveData<Boolean>
        get() = _eventToDetails

    private val _eventLogout = MutableLiveData<Boolean>()
    val eventLogout: LiveData<Boolean>
        get() = _eventLogout

    private var _showSnackbarEvent = MutableLiveData<Boolean>()
    val showSnackbarEvent: LiveData<Boolean>
        get() = _showSnackbarEvent

    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()
    val shoeLinks = MutableLiveData<String>()

    init {
        _shoeList.value = createList()
        _eventAddShoe.value = false
        _eventToDetails.value = false
        _eventLogout.value = false
        _showSnackbarEvent.value = false

    }

    private fun createList(): MutableList<Shoe> {
        list = arrayListOf(
            Shoe("Shoe01", 1.0, "Nike", "Description01", arrayListOf("link1", "link2")),
            Shoe("Shoe02", 1.5, "Adidas", "Description02", arrayListOf("link1", "link2")),
            Shoe("Shoe03", 2.0, "Converse", "Description03", arrayListOf("link1", "link2")),
            Shoe("Shoe04", 2.5, "Vans", "Description04", arrayListOf("link1", "link2")),
            Shoe("Shoe05", 3.0, "Puma", "Description05", arrayListOf("link1", "link2")),
            Shoe("Shoe06", 3.5, "Jordan", "Description06", arrayListOf("link1", "link2")),
            Shoe("Shoe07", 4.0, "Nike", "Description07", arrayListOf("link1", "link2")),
            Shoe("Shoe08", 4.5, "Adidas", "Description08", arrayListOf("link1", "link2")),
            Shoe("Shoe09", 5.0, "Converse", "Description09", arrayListOf("link1", "link2")),
            Shoe("Shoe10", 5.5, "Vans", "Description10", arrayListOf("link1", "link2")),
        )
        return list
    }

    private fun addShoe(
        name: String,
        size: Double,
        company: String,
        description: String,
        links: String
    ) {
        _shoeList.value?.add(Shoe(name, size, company, description, arrayListOf(links, links)))
    }

    private fun onShoeApproved() {
        _eventAddShoe.value = true
    }

    private fun setParametersEmpty() {
        shoeName.value = ""
        shoeSize.value = ""
        shoeCompany.value = ""
        shoeDescription.value = ""
        shoeLinks.value = ""
    }

    fun onShoeAdded() {
        _eventAddShoe.value = false
    }

    fun onFABClick() {
        _eventToDetails.value = true
    }

    fun onDetailsFragment() {
        _eventToDetails.value = false
    }

    fun onLogout() {
        _eventLogout.value = true
    }

    fun onLoginFragment() {
        _eventLogout.value = false
    }

    fun doneShowingSnackbar() {
        _showSnackbarEvent.value = false
    }

    fun onAddShoe() {
        val name = shoeName.value
        val size = shoeSize.value
        val company = shoeCompany.value
        val description = shoeDescription.value
        val links = shoeLinks.value

        when {
            !name.isNullOrEmpty() && !size.isNullOrEmpty() && !company.isNullOrEmpty() && !description.isNullOrEmpty()
                    && !links.isNullOrEmpty() -> {
                addShoe(name, size.toDouble(), company, description, links)
                setParametersEmpty()
                onShoeApproved()
            }
            else -> {
                _showSnackbarEvent.value = true
            }
        }
    }

}