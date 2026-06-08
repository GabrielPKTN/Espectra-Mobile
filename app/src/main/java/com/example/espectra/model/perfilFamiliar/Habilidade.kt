package com.example.espectra.model.perfilFamiliar

import com.google.gson.annotations.SerializedName

data class Habilidade (
    val id: Int,
    val nome: String,
    @SerializedName("idade_meses")
    val valorMeses: Int
)