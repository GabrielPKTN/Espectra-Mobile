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

interface FamiliarService {

    @Multipart
    @POST("v1/espectra/paciente/")
    suspend fun adicionarFamiliar(
        @Header("x-access-token") token: String,
        @Part("nome") nome: RequestBody,
        @Part("data_nascimento") dataNascimento: RequestBody,
        @Part("id_serie_escolar") idSerieEscolar: RequestBody,
        @Part("id_grau_suporte") idGrauSuporte: RequestBody,
        @Part("diagnostico") diagnostico: RequestBody,
        @Part("cpf") cpf: RequestBody,
        @Part("id_responsavel") idResponsavel: RequestBody,
        @Part foto: MultipartBody.Part?
    )
}