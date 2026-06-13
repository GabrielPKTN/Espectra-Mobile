package com.example.espectra.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.tentativa.Atividade
import com.example.espectra.service.RetrofitFactory

class AtividadeViewModel : ViewModel() {

    private var _atividade by mutableStateOf<Atividade?>(null)
    val atividade get() = _atividade

    suspend fun buscaAtividadeId(idAtividade: Int, idUsuario: Int, token: String) {

        try {

            val result = RetrofitFactory()
                .getEspectraService()
                .getAtividadeById(

                    token = token,
                    idAtividade = idAtividade,
                    idUsuario = idUsuario

                )

            // Se quiser ver o conteúdo de cada item no console:
            Log.d("API_SUCESSO", "Conteúdo retornado: $result")

            _atividade = result.items

        } catch (error: Exception) {

            Log.d("Erro: ", "Requisição mal sucedida", error)
            _atividade = null

        }

    }

}