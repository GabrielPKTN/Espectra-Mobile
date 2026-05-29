package com.example.espectra.model.tentativa

import java.sql.Date

data class DataTelaHistoricoTentativa(

    val auxilio: String,
    val resultado: Boolean,
    val habilidade: ArrayList<Habilidade>,
    val observacao: String,
    val id_atividade: Int,
    val id_tentativa: Int,
    val comportamento: String,
    val data_tentativa: Date,
    val numero_questao: Int

)