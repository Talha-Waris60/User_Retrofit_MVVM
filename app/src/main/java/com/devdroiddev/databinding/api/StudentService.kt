package com.devdroiddev.databinding.api

import com.devdroiddev.databinding.model.DataModel
import com.devdroiddev.databinding.model.NewModel
import com.devdroiddev.databinding.model.PostModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StudentService {

    // https://dummyapi.io/data/v1/user/60d0fe4f5311236168a109ca

    // All these methods are HTTP methods
    @GET("user/{id}")
    suspend fun getStudentById(
        @Header("app-id") appId: String,
        @Path("id") id: String
        ) : Response<NewModel>

    @GET("user")
     suspend fun getALLStudent(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Header("app-id") appId: String
    ) : Response<DataModel>


    @POST("posts")
    fun sendUserData(
        @Body postModel : PostModel
    ) : Call<PostModel>



    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost(
        @Field("userId") userInt: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body : String
    ) : Response<PostModel>
}