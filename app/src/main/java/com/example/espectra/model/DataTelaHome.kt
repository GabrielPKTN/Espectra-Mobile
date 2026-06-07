package com.example.espectra.model

data class DataTelaHome(
    val id: Int,
    val nome: String,
    val idade: Int,
    val diagnostico: String?,
    val fotoUrl: String?
)


data class PacientesResponse(
    val sucesso: Boolean,
    val mensagem: String?,
    val dados: List<DataTelaHome>?
)