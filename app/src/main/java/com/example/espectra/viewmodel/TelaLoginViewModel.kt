package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.service.
import com.example.espectra.model.DataTelaLogin
import com.example.espectra.model.ValidarLoginSenha
import kotlinx.coroutines.launch

class TelaLoginViewModel : ViewModel() {

    // Estados que a tela Compose irá observar (Apenas leitura para a View)
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

    // Funções para a View atualizar os estados com segurança

    fun novoEmail(novoEmail: String) {
        email = novoEmail
        if (emailErro != null) emailErro = null // Limpa o erro assim que o usuário digita
    }

    fun novaSenha(novaSenha: String) {
        senha = novaSenha
        if (senhaErro != null) senhaErro = null // Limpa o erro assim que o usuário digita
    }

    // A função principal chamada pelo botão de login

    fun realizarLogin(onSuccess: () -> Unit) {
        // 1. Executa as validações locais
        val validarEmail = ValidarLoginSenha.validarEmail(email)
        val validarSenha = ValidarLoginSenha.validarSenha(senha)

        if (!validarEmail) {
            emailErro = "E-mail Inválido"
        }
        if (!validarSenha) {
            senhaErro = "A senha deve ter pelo menos 8 caracteres"
        }


        if (!validarEmail || !validarSenha) return

        // 2. Se passou na validação, inicia a chamada assíncrona para o Backend

        viewModelScope.launch {
            carregarDados = true
            try {
                // Faz o POST real enviando o JSON com email e senha para o banco
                val response = RetrofitInstance.apiService.login(DataTelaLogin(email, senha))

                if (response.isSuccessful && response.body()?.sucesso == true) {
                    // Login feito com sucesso no banco de dados!
                    onSuccess()
                } else {
                    // Mensagem de erro vinda do seu backend (ex: "Senha incorreta")
                    emailErro = response.body()?.mensagem ?: "E-mail ou senha incorretos."
                }
            } catch (e: Exception) {
                // Trata erros de rede (ex: servidor desligado ou sem internet)
                emailErro = "Falha ao conectar com o servidor: ${e.localizedMessage}"
            } finally {
                carregarDados = false
            }
        }
    }
}