package com.example.espectra.model.Atividades

import com.google.gson.annotations.SerializedName

data class Atividade (

    val concluida: Int,

    val habilidade: Habilidade,

    @SerializedName("id_atividade")
    val idAtividade: Int,

    val comportamento: String,

    @SerializedName("numero_questao")
    val numeroQuestao: Int

)


data class AtividadeResponse(
    val items: List<Atividade>
)