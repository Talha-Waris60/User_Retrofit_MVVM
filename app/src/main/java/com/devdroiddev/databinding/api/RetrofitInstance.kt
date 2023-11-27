package com.devdroiddev.databinding.api

import com.devdroiddev.databinding.utils.Constant.Companion.BASE_URL_
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // retrofit instance object class
    val studentService : StudentService
    init {
        // Create the retrofit instance
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        // Create an instance of the NewsInterfaceApi
        studentService = retrofit.create(StudentService::class.java)
    }
}