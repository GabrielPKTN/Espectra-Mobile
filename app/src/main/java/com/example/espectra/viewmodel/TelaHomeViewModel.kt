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

        if (tokenRaw.isNullOrBlank()) {
            erroMensagem = "Sessão inválida. Faça login novamente."
            return
        }

        viewModelScope.launch {
            isLoading = true
            erroMensagem = null
            try {
                // Força a remoção de qualquer "Bearer" antigo e limpa quebras de linha ou espaços invisíveis
                val tokenLimpo = tokenRaw
                    .replace("Bearer ", "", ignoreCase = true)
                    .replace("Bearer", "", ignoreCase = true)
                    .trim()

                val tokenFormatado = "Bearer $tokenLimpo"

                Log.d("ESPECTRA_DEBUG", "Enviando para ID: $idUsuario")

                val response = RetrofitInstance.espectraApiService.buscarPacientes(tokenFormatado, idUsuario)

                if (response.isSuccessful && response.body() != null) {
                    val apiResponse = response.body()!!

                    if (apiResponse.status && apiResponse.items != null) {
                        listaPacientes = apiResponse.items.familiares.map { paciente ->
                            DataTelaHome(
                                id = paciente.id,
                                nome = paciente.nome,
                                idade = paciente.idade,
                                diagnostico = if (paciente.diagnostico_breve.isNotEmpty()) {
                                    paciente.diagnostico_breve.joinToString(", ") { it.sigla }
                                } else {
                                    "Sem diagnóstico"
                                },
                                fotoUrl = paciente.foto
                            )
                        }
                    } else {
                        erroMensagem = apiResponse.message ?: "Nenhum dado encontrado."
                    }
                } else {
                    // Se der 401 aqui, o problema está na chave secreta de validação do JWT no Spring Boot
                    erroMensagem = "Erro ${response.code()}: Não autorizado. Verifique o login."
                    Log.e("ESPECTRA_DEBUG", "Erro do servidor: ${response.code()}")
                }
            } catch (e: Exception) {
                erroMensagem = "Erro de rede: ${e.localizedMessage}"
                Log.e("ESPECTRA_DEBUG", "Falha de conexão", e)
            } finally {
                isLoading = false
            }
        }
    }
}