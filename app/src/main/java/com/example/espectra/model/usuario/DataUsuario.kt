package com.example.espectra.model.usuario

import com.example.espectra.model.tentativa.Habilidade

data class Usuario(

    val id: Int,
    val foto: String,
    val nome: String,
    val telefone: String,
    val data_nascimento: String

)

data class ResponseUsuario(
    val development: String,
    val api_description: String,
    val request_date: String,
    val items: Usuario
)