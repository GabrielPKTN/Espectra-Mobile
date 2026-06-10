package com.example.espectra.model

import com.google.gson.annotations.SerializedName

data class DataTelaCadastro(
    @SerializedName("nome")
    val nome: String,

    @SerializedName("email")
    val email: String,

    @SerializedName("senha")
    val senha: String,

    @SerializedName("data_nascimento")
    val dataNascimento: String,

    @SerializedName("telefone")
    val telefone: String,

    @SerializedName("id_tipo_usuario")
    val idTipoUsuario: Int
)