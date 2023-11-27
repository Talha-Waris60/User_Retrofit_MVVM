package com.devdroiddev.databinding.repository

import com.devdroiddev.databinding.api.RetrofitInstance
import com.devdroiddev.databinding.model.ResponseModel
import com.devdroiddev.databinding.model.User
import com.devdroiddev.databinding.utils.Constant
import retrofit2.Response

class UserRepository() {

    suspend fun getAllStudent(page: Int, limit: Int): Response<ResponseModel> {
        return RetrofitInstance.studentService.getALLStudent(page, limit, Constant.APP_ID)
    }

    suspend fun getStudentById(id: String): Response<User> {
        return RetrofitInstance.studentService.getStudentById(Constant.APP_ID, id)
    }
}