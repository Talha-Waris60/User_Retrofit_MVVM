package com.devdroiddev.databinding.model

import com.google.gson.annotations.SerializedName

data class DataModel(
       @SerializedName("data")
       val data : List<NewModel>

)
