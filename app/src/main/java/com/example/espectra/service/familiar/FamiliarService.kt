package com.example.espectra.service.familiar

import com.example.espectra.model.familiar.Familiar
import com.example.espectra.model.familiar.ResponseFamiliar
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap

interface FamiliarService {

    @Multipart
    @POST("v1/espectra/paciente/")
    suspend fun adicionarFamiliar(
        @Header("x-access-token") token: String,
        @PartMap dadosForm: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part foto: MultipartBody.Part?
    )
}