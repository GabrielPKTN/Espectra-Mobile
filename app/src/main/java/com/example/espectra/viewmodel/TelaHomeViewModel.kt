package com.example.espectra.viewmodel

import android.util.Log
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

    var nomeUsuarioLogado by mutableStateOf("")
        private set

    var tipoUsuarioLogado by mutableStateOf("")
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
        val tokenRaw = gerenciarSessao.buscarToken()
        val idUsuario = if (gerenciarSessao.buscarIdUsuario() == 0) 2 else gerenciarSessao.buscarIdUsuario()

        Log.d("ESPECTRA_JSON", "Token bruto recuperado da sessao: $tokenRaw")

        if (tokenRaw.isNullOrBlank()) {
            erroMensagem = "Sessão inválida. Faça login novamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            erroMensagem = null
            try {
                // Certifica-se apenas de remover "Bearer " se por acaso ele tiver sido salvo com ele
                val tokenLimpo = tokenRaw.replace("Bearer ", "", ignoreCase = true).trim()

                Log.d("ESPECTRA_JSON", "Enviando token limpo para o x-access-token: $tokenLimpo")

                // Chamada ao Retrofit passando o token puro no cabeçalho x-access-token
                val response = RetrofitInstance.espectraApiService.buscarPacientes(tokenLimpo, idUsuario)

                if (response.isSuccessful && response.body() != null) {
                    val apiResponse = response.body()!!

                    if (apiResponse.status && apiResponse.items != null) {
                        nomeUsuarioLogado = apiResponse.items.nome
                        tipoUsuarioLogado = apiResponse.items.tipoUsuario

                        // CORREÇÃO AQUI: Se 'familiares' vier nulo da API, ele assume uma lista vazia com segurança
                        listaPacientes = apiResponse.items.familiares ?: emptyList()

                        Log.d("ESPECTRA_JSON", "Sucesso! Foram carregados ${listaPacientes.size} pacientes.")
                    } else {
                        erroMensagem = apiResponse.message ?: "Nenhum dado encontrado."
                    }
                } else {
                    val erroServidor = response.errorBody()?.string()
                    erroMensagem = "Erro ${response.code()}: Não autorizado pelo servidor."
                    Log.e("ESPECTRA_JSON", "O servidor recusou o token. Corpo: $erroServidor")
                }
            } catch (e: Exception) {
                erroMensagem = "Erro de rede: ${e.localizedMessage}"
                Log.e("ESPECTRA_JSON", "Falha catastrófica de rede ou mapeamento", e)
            } finally {
                isLoading = false
            }
        }
    }
}