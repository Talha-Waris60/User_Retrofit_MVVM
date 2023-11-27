package com.devdroiddev.databinding.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroiddev.databinding.model.User
import com.devdroiddev.databinding.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserDetailViewModel() : ViewModel() {

    private val userRepository = UserRepository()
    private var _userLiveData = MutableLiveData<User>()
    private var _isApiCalling = MutableLiveData<Boolean>(false)

    fun getUserById(id: String) {
        _isApiCalling.value = true
        viewModelScope.launch(Dispatchers.IO) {
           val result = userRepository.getStudentById(id)
            _isApiCalling.postValue(false)
            if (result.body() != null) {
                _userLiveData.postValue(result.body())
            }
        }
    }

    // Readable live data for Ui components
    val userLiveData: LiveData<User>
        get() = _userLiveData

    val isApiCalling: LiveData<Boolean>
        get() = _isApiCalling
}