
package com.devdroiddev.databinding.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroiddev.databinding.model.ResponseModel
import com.devdroiddev.databinding.repository.UserRepository
import com.devdroiddev.databinding.utils.Constant
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    private val userRepository : UserRepository = UserRepository()
    private val _userLiveData = MutableLiveData<ResponseModel>()

    private var _isApiCall = MutableLiveData<Boolean>(false)
    var page: Int = 0

    fun getAllUserData() {
        _isApiCall.value = true
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.getAllStudent(page, Constant.limit)
            if (result.body() != null) {
                _userLiveData.postValue(result.body())
            }
            page++
            _isApiCall.postValue(false)
        }
    }

    // Access the live data of Repository
     val userLiveData : LiveData<ResponseModel>
          get() = _userLiveData

    val isApiCall : LiveData<Boolean>
        get() = _isApiCall

}