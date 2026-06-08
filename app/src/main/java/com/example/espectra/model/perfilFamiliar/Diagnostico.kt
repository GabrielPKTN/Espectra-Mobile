package com.example.espectra.model.perfilFamiliar

import com.google.gson.annotations.SerializedName

data class Diagnostico(
    val sigla: String,
    @SerializedName("id_transtorno") val idTranstorno: Int,
    @SerializedName("nome_completo") val nomeCompleto: String
)