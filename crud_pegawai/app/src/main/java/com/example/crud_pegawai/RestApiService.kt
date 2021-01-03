package com.example.crud_pegawai

import retrofit2.Call
import retrofit2.http.*

interface RestApiService{

    @GET("tampilkan.php")
    fun getListPegawai(): Call<ModelListPegawai>

    @FormUrlEncoded
    @POST("tambah.php")
    fun tambahPegawai(@Field("nama_pegawai") nama: String,
                      @Field("tanggalLahir") tglLahir:String,
                      @Field("devisi") divisi:String): Call<ModelStatusResponse>

    @FormUrlEncoded
    @POST("ubah.php")
    fun editPegawai(@Field("id") id: Int?,
                    @Field("nama_pegawai") nama: String,
                    @Field("tanggalLahir") tglLahir:String,
                    @Field("devisi") divisi:String): Call<ModelStatusResponse>

    @GET("hapus.php")
    fun hapusPegawai(@Query("id") id: Int): Call<ModelStatusResponse>
}