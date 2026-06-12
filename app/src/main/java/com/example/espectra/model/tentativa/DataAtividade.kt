package com.example.espectra.model.tentativa

data class Atividade(

    val concluida: Int,
    val habilidade: Habilidade,
    val id_paciente: Int,
    val id_atividade: Int,
    val comportamento: String,
    val numero_questao: Int?

)

data class ResponseAtividade(

    val development: String,
    val api_description: String,
    val request_date: String,
    val items: Atividade

)

data class ResponseAtividades(

    val development: String,
    val api_description: String,
    val request_date: String,
    val items: List<Atividade>?

)