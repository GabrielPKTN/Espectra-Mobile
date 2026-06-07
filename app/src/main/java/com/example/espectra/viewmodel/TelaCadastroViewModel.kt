package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.DataTelaCadastro
import com.example.espectra.service.RetrofitInstance
import kotlinx.coroutines.launch

class TelaCadastroViewModel : ViewModel() {


    var isPsicopedagogo by mutableStateOf(false)
    var isFamilia by mutableStateOf(true)
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var telefone by mutableStateOf("")
    var senha by mutableStateOf("")
    var confirmarSenha by mutableStateOf("")

    // Estados de controle de fluxo
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)
    var cadastroSucesso by mutableStateOf(false)

    fun mudarTipoConta(psicopedagogoSelecionado: Boolean) {
        isPsicopedagogo = psicopedagogoSelecionado
        isFamilia = !psicopedagogoSelecionado
    }


    private fun formatarDataParaBanco(dataOriginal: String): String {
        val limpa = dataOriginal.replace("/", "").replace("-", "").trim()
        return if (limpa.length == 8) {
            val dia = limpa.substring(0, 2)
            val mes = limpa.substring(2, 4)
            val ano = limpa.substring(4, 8)
            "$ano-$mes-$dia"
        } else {
            dataOriginal
        }
    }

    fun executarCadastro(onSucesso: () -> Unit) {
        val emailLimpo = email.trim()
        val senhaLimpa = senha.trim()
        val nomeLimpo = nome.trim()

        if (nomeLimpo.isBlank() || emailLimpo.isBlank() || senhaLimpa.isBlank() || dataNascimento.isBlank() || telefone.isBlank()) {
            errorMessage = "Preencha todos os campos obrigatórios."
            return
        }

        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        if (!emailLimpo.matches(emailPattern.toRegex())) {
            errorMessage = "Insira um e-mail válido."
            return
        }

        if (senhaLimpa.length < 8) {
            errorMessage = "A senha deve ter pelo menos 8 caracteres."
            return
        }

        if (senhaLimpa != confirmarSenha.trim()) {
            errorMessage = "As senhas não coincidem."
            return
        }

        val idTipoUsuarioCalculado = if (isPsicopedagogo) 1 else 2
        val dataFormatada = formatarDataParaBanco(dataNascimento)

        viewModelScope.launch {
            isLoading = true
            errorMessage = null
            try {
                val request = DataTelaCadastro(
                    nome = nomeLimpo,
                    email = emailLimpo,
                    senha = senhaLimpa,
                    dataNascimento = dataFormatada,
                    telefone = telefone.trim(),
                    idTipoUsuario = idTipoUsuarioCalculado
                )

                val response = RetrofitInstance.espectraApiService.cadastrar(request)

                if (response.isSuccessful) {
                    cadastroSucesso = true
                    onSucesso()
                } else {
                    val erroJson = response.errorBody()?.string()
                    errorMessage = erroJson ?: "Erro ao realizar cadastro."
                }
            } catch (e: Exception) {
                errorMessage = "Falha na conexão: ${e.localizedMessage}"
            } finally {
                isLoading = false
            }
        }
    }
}