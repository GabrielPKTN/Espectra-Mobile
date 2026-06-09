package com.example.espectra.model.editarFamiliar

import com.google.gson.annotations.SerializedName

data class AtualizarFamiliarRequest(

    val id: Int,

    val nome: String,

    @SerializedName(value = "cpf")
    val cpfFamiliar: String,

    @SerializedName("data_nascimento")
    val dataNascimento: String,

    val diagnostico: List<Int>,

    @SerializedName("id_serie_escolar")
    val serieEscolar: Int,

    @SerializedName("id_grau_suporte")
    val grauSuporte: Int
)