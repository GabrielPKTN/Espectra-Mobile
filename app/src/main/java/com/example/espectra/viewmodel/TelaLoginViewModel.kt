package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.service.RetrofitInstance
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.model.DataTelaLogin
import com.example.espectra.model.ValidarLoginSenha
import kotlinx.coroutines.launch

class TelaLoginViewModel : ViewModel() {

    var email by mutableStateOf("")
        private set

    var senha by mutableStateOf("")
        private set

    var emailErro by mutableStateOf<String?>(null)
        private set

    var senhaErro by mutableStateOf<String?>(null)
        private set

    var carregarDados by mutableStateOf(false)
        private set


    fun novoEmail(novoEmail: String) {
        email = novoEmail
        if (emailErro != null) emailErro = null
    }

    fun novaSenha(novaSenha: String) {
        senha = novaSenha
        if (senhaErro != null) senhaErro = null
    }


    fun realizarLogin(gerenciarSessao: GerenciarSessao, onSuccess: () -> Unit) {
        // Remove espaços em branco das pontas
        val emailLimpo = email.trim()
        val senhaLimpa = senha.trim()

        val validarEmail = ValidarLoginSenha.validarEmail(emailLimpo)
        val validarSenha = ValidarLoginSenha.validarSenha(senhaLimpa)

        if (!validarEmail) emailErro = "E-mail Inválido"
        if (!validarSenha) senhaErro = "A senha deve ter pelo menos 8 caracteres"

        if (!validarEmail || !validarSenha) return

        viewModelScope.launch {
            carregarDados = true
            try {

                val response = RetrofitInstance.espectraApiService.login(
                    DataTelaLogin(emailLimpo, senhaLimpa)
                )
                if (response.isSuccessful && response.body()?.sucesso == true) {
                    val corpoResposta = response.body()
                    val token = corpoResposta?.token


                    val idUsuario = corpoResposta?.idUsuario ?: 0


                    if (token != null && idUsuario != 0) {
                        gerenciarSessao.salvarSessao(token, idUsuario)
                        onSuccess()
                    } else {
                        emailErro = "Erro: Dados de sessão inválidos (Token ou ID nulos)."
                    }
                } else {
                    emailErro = response.body()?.mensagem ?: "E-mail ou senha incorretos."
                }
            } catch (e: Exception) {
                emailErro = "Falha ao conectar com o servidor: ${e.localizedMessage}"
            } finally {
                carregarDados = false
            }
        }
    }
}