package com.example.espectra.model.perfilFamiliar

data class PerfilFamiliar (
    val foto: String,
    val nome: String,
    val idade: Int,
    val grauSuporte: String,
    val serieEscolar: String,
    val dataNascimento: String,
    val grafico: List<Habilidade>,
    val diagnostico: List<Diagnostico>
)

data class ResponsePerfil(

    val development: String,
    val api_description: String,
    val request_date: String,
    val items: PerfilFamiliar

)