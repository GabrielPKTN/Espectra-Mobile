package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.DataTelaHome
import com.example.espectra.service.RetrofitInstance
import com.example.espectra.storage.GerenciarSessao
import kotlinx.coroutines.launch

class TelaHomeViewModel : ViewModel() {

    var listaPacientes by mutableStateOf<List<DataTelaHome>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var erroMensagem by mutableStateOf<String?>(null)
        private set

    var textoPesquisa by mutableStateOf("")
        private set

    fun atualizarPesquisa(novoTexto: String) {
        textoPesquisa = novoTexto
    }


    fun carregarDadosDoPaciente(gerenciarSessao: GerenciarSessao) {
        val token = gerenciarSessao.buscarToken()
        val idUsuario = gerenciarSessao.buscarIdUsuario()

        if (token == null || idUsuario == 0) {
            erroMensagem = "Sessão inválida. Faça login novamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            erroMensagem = null
            try {

                val tokenFormatado = "Bearer $token"

                val response = RetrofitInstance.espectraApiService.buscarPacientes(tokenFormatado, idUsuario)

                if (response.isSuccessful && response.body()?.sucesso == true) {
                    listaPacientes = response.body()?.dados ?: emptyList()
                } else {
                    erroMensagem = response.body()?.mensagem ?: "Falha ao carregar pacientes."
                }
            } catch (e: Exception) {
                erroMensagem = "Erro de conexão: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}