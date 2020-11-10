package com.example.testframe.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel: ViewModel() {
    private val _profileName = MutableLiveData<Profile>().apply {
        value = Profile("Максим Овчинников","m.ovchinnikov6@gmail.com","8-952-422-64-58","студент")
    }
    val profileName: LiveData<Profile> = _profileName
}