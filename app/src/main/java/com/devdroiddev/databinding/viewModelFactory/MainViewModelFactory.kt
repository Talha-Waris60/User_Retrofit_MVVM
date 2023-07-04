package com.devdroiddev.databinding.viewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.devdroiddev.databinding.repository.Repository
import com.devdroiddev.databinding.repository.StudentRepository
import com.devdroiddev.databinding.viewModel.MainViewModel

class MainViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }
}
