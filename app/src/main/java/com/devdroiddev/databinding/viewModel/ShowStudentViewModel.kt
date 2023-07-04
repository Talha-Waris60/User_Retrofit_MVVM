package com.devdroiddev.databinding.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devdroiddev.databinding.model.DataModel
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.repository.StudentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowStudentViewModel() : ViewModel() {

    private val studentRepository = StudentRepository()
    private var studentByIdLiveData = MutableLiveData<NewModel>()
    private var isApiCalling = MutableLiveData<Boolean>(false)

    fun loadStudentById(id: String) {
        isApiCalling.value = true
        viewModelScope.launch(Dispatchers.IO) {
           val result = studentRepository.getStudentById(id)
            isApiCalling.postValue(false)
            if (result.body() != null) {
                studentByIdLiveData.postValue(result.body())
            }
        }
    }

    // Access the live data of Repository
    val currentStudent: LiveData<NewModel>
        get() = studentByIdLiveData

    val isCalling: LiveData<Boolean>
        get() = isApiCalling
}