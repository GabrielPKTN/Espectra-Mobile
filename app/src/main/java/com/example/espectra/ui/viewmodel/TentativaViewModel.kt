package com.example.espectra.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.tentativa.Tentativa
import com.example.espectra.service.RetrofitFactory

class TentativaViewModel : ViewModel() {

    private var _tentativas by mutableStateOf<List<Tentativa>>(emptyList())
    val tentativas get() = _tentativas

    suspend fun buscarDadosTentativas(idAtividade: Int, token: String) {

        try {

            val result = RetrofitFactory()
                .getEspectraService()
                .getTentativasByIdAtividade(

                    token = token,
                    idTentativa = idAtividade

                )

            // Se quiser ver o conteúdo de cada item no console:
            Log.d("API_SUCESSO", "Conteúdo retornado: $result")

            _tentativas = result.items

        } catch (error: Exception) {

            Log.d("Erro: ", "Requisição mal sucedida", error)
            _tentativas = emptyList()

        }

    }

}