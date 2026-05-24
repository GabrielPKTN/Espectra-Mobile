package com.example.espectra.model

data class DataTelaLogin (
        val email: String,
        val senha: String
        //java.lang.String
    )

    data class RespostaAutenticacao(
        val token: String?,
        val mensagem: String?,
        val sucesso: Boolean
    )
