package com.example.espectra.model.perfilFamiliar

import com.google.gson.annotations.SerializedName

data class Diagnostico (
    val sigla: String,
    val id_transtorno: Int,
    @SerializedName("nome_completo_transtorno")
    val nomeCompleto: String
)