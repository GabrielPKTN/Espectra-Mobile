package com.example.espectra.model

data class Paciente(
    val id: Int,
    val nome: String,
    val idade: Int,
    val diagnostico: String? = null
)