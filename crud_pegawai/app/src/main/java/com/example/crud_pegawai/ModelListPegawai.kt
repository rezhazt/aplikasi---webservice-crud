package com.example.crud_pegawai

import com.google.gson.annotations.SerializedName

data class ModelListPegawai(
    @SerializedName("list_pegawai")
    val listPegawai: List<Pegawai>
)

data class Pegawai(
    @SerializedName("id")
    val id:Int,
    @SerializedName("nama_pegawai")
    val nama:String,
    @SerializedName("tanggalLahir")
    val tanggalLahir:String,
    @SerializedName("devisi")
    val divisi:String
)