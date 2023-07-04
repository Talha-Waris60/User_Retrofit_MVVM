package com.devdroiddev.databinding.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdroiddev.databinding.repository.StudentRepository
import com.devdroiddev.databinding.viewModel.ShowStudentViewModel

class ShowStudentFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShowStudentViewModel() as T
    }
}