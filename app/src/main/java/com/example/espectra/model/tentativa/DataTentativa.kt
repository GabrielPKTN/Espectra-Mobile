package com.example.espectra.model.tentativa

import java.sql.Date

data class Tentativa(

    val auxilio: String,
    val resultado: Int,
    val habilidade: Habilidade,
    val observacao: String,
    val id_atividade: Int,
    val id_tentativa: Int,
    val comportamento: String,
    val data_tentativa: String,
    val numero_questao: Int?

)

data class Habilidade(

    val id_habilidade: Int,
    val nome_habilidade: String

)

data class ResponseTentativa(

    val development: String,
    val api_description: String,
    val request_date: String,
    val items: List<Tentativa>

)