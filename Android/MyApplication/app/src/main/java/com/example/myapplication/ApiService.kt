package com.example.myapplication


import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiService {

    @GET("api/pharmacy/getAll")
    fun getAllPharmacy():Call<List<Pharmacy>>

    @POST("api/pharmacy")
    fun createPharmacy(@Body pharmacy:Pharmacy):Call<Pharmacy>

    @Multipart
    @POST("api/pharmacy/images")
    fun uploadImage(@Part file: MultipartBody.Part?): Call<String?>?

}