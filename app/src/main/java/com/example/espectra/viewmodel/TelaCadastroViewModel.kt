package com.example.espectra.viewmodel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.service.RetrofitInstance
import com.example.espectra.model.DataTelaCadastro
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {
    var isPsicopedagogo by mutableStateOf(false)
    var isFamilia by mutableStateOf(true)
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var telefone by mutableStateOf("")
    var senha by mutableStateOf("")
    var confirmarSenha by mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var cadastroSucesso by mutableStateOf(false)

    fun executarCadastro(onSucesso: () -> Unit) {
        if (senha != confirmarSenha) {
            errorMessage = "As senhas não coincidem."
            return
        }
        if (nome.isBlank() || email.isBlank() || senha.isBlank()) {
            errorMessage = "Preencha os campos obrigatórios."
            return
        }

        val tipoConta = if (isPsicopedagogo) "PSICOPEDAGOGO" else "FAMILIA"

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val request = CadastroRequest(
                    tipoConta = tipoConta,
                    nome = nome,
                    email = email,
                    dataNascimento = dataNascimento,
                    telefone = telefone,
                    senha = senha
                )
                val response = RetrofitClient.apiService.cadastrar(request)

                if (response.isSuccessful) {
                    cadastroSucesso = true
                    onSucesso()
                } else {
                    errorMessage = response.body()?.mensagem ?: "Erro ao realizar cadastro."
                }
            } catch (e: Exception) {
                errorMessage = "Erro de rede: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}