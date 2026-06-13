package com.example.espectra.model

import com.google.gson.annotations.SerializedName

data class DataTelaLogin (
    @SerializedName("email")
    val email: String,

    @SerializedName("senha")
    val senha: String
)

data class RespostaAutenticacao(
    @SerializedName("token")
    val token: String?,


    @SerializedName("message", alternate = ["mensagem"])
    val mensagem: String?,


    @SerializedName("status", alternate = ["sucesso"])
    val sucesso: Boolean,


    @SerializedName("id", alternate = ["idUsuario", "userId", "id_usuario"])
    val idUsuario: Int?
)