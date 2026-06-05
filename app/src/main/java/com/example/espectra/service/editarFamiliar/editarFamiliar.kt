package com.example.espectra.service.editarFamiliar

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface editarFamiliar{
    @Multipart
    @PUT("v1/espectra/paciente/{id_usuario}")
    suspend fun atualizarFamiliar(
        @Header("x-access-token") token: String,
        @Path("id_usuario") idUsuario: Int,

        @Part("nome") nome: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("data_nascimento") dataNascimento: RequestBody,
        @Part("diagnostico") diagnostico: RequestBody,

        @Part foto: MultipartBody.Part?
    ): ResponseBody
}
