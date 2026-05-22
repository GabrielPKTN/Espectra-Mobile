package com.example.espectra.viewmodel

import android.telecom.Call
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TelaLoginViewModel : ViewModel() {

    //Estados que a tela Compose ira observar

    var email by mutableStateOf("")
        private set
    var senha by mutableStateOf("")
        private set
    var emailErro by mutableStateOf<String?>(null)
        private set

    var senhaErro by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun novoEmail(novoEmail: String){
        email = novoEmail
        if (emailErro != null) emailErro  = null // limpa erro ao digitar
    }
    fun novaSenha (newPassword: String){
        if(senhaErro != null ) senhaErro = null
    }

    fun

    //a funcao principal chamada pelo botao login
    fun realizarLogin(onSuccess: () -> Unit){
        //executa as validacoes locais
        val emalValido =
    }

}w



// A função principal chamada pelo botão de Login
fun realizarLOgin(onSuccess: () -> Unit) {
    // 1. Executa as validações locais
    val isEmailValid = Validator.isValidEmail(email)
    val isPasswordValid = Validator.isValidPassword(password)

    if (!isEmailValid) {
        emailError = "E-mail inválido"
    }
    if (!isPasswordValid) {
        passwordError = "A senha deve ter pelo menos 6 caracteres"
    }

    // Se algo estiver errado, interrompe o fluxo aqui
    if (!isEmailValid || !isPasswordValid) return

    // 2. Se passou na validação, inicia a chamada assíncrona (Coroutine)
    viewModelScope.launch {
        isLoading = true
        try {
            // Aqui chamaremos o Repository que faz o POST para o back-end
            // Exemplo simulado:
            // authRepository.login(email, password)

            // Simula um delay de rede de 2 segundos
            kotlinx.coroutines.delay(2000)

            // Se deu certo, executa o callback de sucesso (navegação)
            onSuccess()
        } catch (e: Exception) {
            // Trata erros de rede ou senha incorreta vindos do servidor
            emailError = "Falha ao realizar login: ${e.localizedMessage}"
        } finally {
            isLoading = false
        }
    }
}
}