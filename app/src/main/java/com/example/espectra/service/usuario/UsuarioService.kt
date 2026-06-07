package com.example.espectra.service.usuario

import com.example.espectra.model.tentativa.ResponseTentativa
import com.example.espectra.model.tentativa.Tentativa
import com.example.espectra.model.usuario.ResponseUsuario
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface UsuarioService {

    @GET("v1/espectra/usuario/{id_usuario}")
    suspend fun getUsuarioById(

        @Header("x-access-token") token: String,
        @Path(value = "id_usuario") idTentativa: Int,

    ): ResponseUsuario

}