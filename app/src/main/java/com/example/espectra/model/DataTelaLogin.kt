package com.example.espectra.model
import com.google.gson.annotations.SerializedName
data class DataTelaLogin (
    val email: String,
    val senha: String
)

data class RespostaAutenticacao(
    val token: String?,
    val mensagem: String?,
    val sucesso: Boolean,


    @SerializedName("idUsuario", alternate = ["id", "userId", "id_usuario"])
    val idUsuario: Int?
)