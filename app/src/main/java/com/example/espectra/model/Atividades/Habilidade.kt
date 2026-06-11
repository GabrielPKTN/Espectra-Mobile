package com.example.espectra.model.Atividades

import com.google.gson.annotations.SerializedName


data class Habilidade (

    @SerializedName("id_habilidade")
    val idHabilidade: Int,
    @SerializedName("nome_habilidade")
    val nomeHabilidade: String
)