package com.example.espectra.service.editarFamiliar

import com.example.espectra.model.perfilFamiliar.Diagnostico
import com.example.espectra.model.perfilFamiliar.DiagnosticoResponse
import com.example.espectra.model.perfilFamiliar.PerfilResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface DiagnosticoService {

    @GET("v1/espectra/diagnostico")
    suspend fun getDiagnosticos(

        @Header("x-access-token") token: String

    ): DiagnosticoResponse

}