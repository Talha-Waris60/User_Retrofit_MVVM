package com.devdroiddev.databinding.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
       @SerializedName("data")
       val data : List<User>

)
