package com.example.espectra.model.perfilFamiliar

import com.google.gson.annotations.SerializedName

data class Diagnostico (
     val id: Int,
     val sigla: String,

    @SerializedName("nome_completo_transtorno")
    val nomeCompleto: String
)

data class DiagnosticoResponse(
    val items: List<Diagnostico>
)