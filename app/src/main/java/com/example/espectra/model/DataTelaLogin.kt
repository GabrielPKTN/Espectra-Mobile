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

    // CORRIGIDO: Agora aceita "message" que vem do seu JSON
    @SerializedName("message", alternate = ["mensagem"])
    val mensagem: String?,

    // CORRIGIDO: Agora aceita "status" que vem do seu JSON
    @SerializedName("status", alternate = ["sucesso"])
    val sucesso: Boolean,

    // AJUSTADO: Garante que vai ler a propriedade "id" do seu JSON
    @SerializedName("id", alternate = ["idUsuario", "userId", "id_usuario"])
    val idUsuario: Int?
)