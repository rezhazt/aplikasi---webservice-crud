package com.example.crud_pegawai

import com.google.gson.annotations.SerializedName

data class ModelStatusResponse(
    @SerializedName("status")
    val status:String
)