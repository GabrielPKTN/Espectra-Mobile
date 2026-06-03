package com.example.espectra.service.familiar

import com.example.espectra.model.familiar.Familiar
import com.example.espectra.model.familiar.ResponseFamiliar
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FamiliarService {

    @Multipart
    @POST("v1/espectra/paciente/")
    suspend fun adicionarFamiliar(

        @Header("x-access-token") token: String,
        @Part("familiar") request: Familiar,

        @Part foto: MultipartBody.Part?
    ): ResponseFamiliar
}