package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.Paciente
import com.example.espectra.service.RetrofitInstance
import com.example.espectra.storage.GerenciarSessao
import kotlinx.coroutines.launch

class TelaHomeViewModel : ViewModel() {

    // Lista de pacientes que a tela vai observar
    var listaPacientes by mutableStateOf<List<Paciente>>(emptyList())
        private set

    // Controla o esqueleto/loading na tela
    var carregando by mutableStateOf(false)
        private set

    // Mensagem de erro caso a API falhe
    var mensagemErro by mutableStateOf<String?>(null)
        private set

    fun carregarPacientesDoUsuario(gerenciarSessao: GerenciarSessao) {
        val token = gerenciarSessao.buscarToken()
        val idUsuario = gerenciarSessao.buscarIdUsuario()

        // Segurança: Se não houver dados válidos salvos, nem tenta chamar a API
        if (token == null || idUsuario == 0) {
            mensagemErro = "Sessão inválida ou expirada."
            return
        }

        viewModelScope.launch {
            carregando = true
            mensagemErro = null
            try {
                // Executa a chamada passando o token formatado e o ID
                val respostaApi = RetrofitInstance.espectraApiService.obterPacientes(
                    token = token, // Ex: "Bearer seu_jwt_token" (adicione "Bearer " se seu backend exigir)
                    idUsuario = idUsuario
                )
                listaPacientes = respostaApi
            } catch (e: Exception) {
                mensagemErro = "Não foi possível carregar os pacientes: ${e.localizedMessage}"
            } finally {
                carregando = false
            }
        }
    }
}