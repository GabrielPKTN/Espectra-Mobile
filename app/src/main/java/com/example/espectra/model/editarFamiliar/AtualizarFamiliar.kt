package com.example.espectra.model.editarFamiliar

data class AtualizarFamiliarRequest(
    val nome: String,
    val cpf: String,
    val dataNascimento: String,
    val diagnosticos: List<Int>
)