package com.devdroiddev.databinding.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devdroiddev.databinding.api.RetrofitInstance
import com.devdroiddev.databinding.model.DataModel
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.utils.Constant
import retrofit2.Response

class StudentRepository() {

    suspend fun getAllStudent(page: Int, limit: Int): Response<DataModel> {
        return RetrofitInstance.studentService.getALLStudent(page, limit, Constant.APP_ID)
    }

    suspend fun getStudentById(id: String): Response<NewModel> {
        return RetrofitInstance.studentService.getStudentById(Constant.APP_ID, id)
    }
}