package com.devdroiddev.databinding.model

import com.google.gson.annotations.SerializedName

data class NewModel(
    val id : String?,
    val title: String?,
    val firstName : String?,
    val lastName : String?,
    val picture : String?,
    val gender : String?,
    val email : String?,
    val dateOfBirth : String?,
    val phone : String?,
    val registerDate : String?,
    val updatedDate : String?
)
