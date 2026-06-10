package com.example.espectra.model.familiar
data class Familiar(
    val nome: String,
    val data_nascimento: String,
    val id_serie_escolar: Int,
    val id_diagnostico: List<Int>,
    val id_grau_suporte: Int,
    val foto_url: String?
)


data class ResponseFamiliar(
    val development: String,
    val api_description: String,
    val request_date: String,
    val items: List<Familiar>
)