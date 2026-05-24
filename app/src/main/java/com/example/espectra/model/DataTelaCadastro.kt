package com.example.espectra.model

data class DataTelaCadastro(
    val tipoConta: String, // "PSICOPEDAGOGO" ou "FAMILIA"
    val nome: String,
    val email: String,
    val dataNascimento: String,
    val telefone: String,
    val senha: String

)