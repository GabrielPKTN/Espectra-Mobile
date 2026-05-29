package com.example.espectra.model.perfilFamiliar

data class DataPerfilFamiliar (
    val foto: String,
    val nome: String,
    val idade: Int,
    val grauSuporte: String,
    val serieEscolar: String,
    val dataNascimento: String,
    val grafico: Array<Habilidade>,
    val diagnostico: Array<Diagnostico>
)