package com.example.espectra.model

object ValidarLoginSenha {


    fun validarEmail(email: String): Boolean {
        if (email.isBlank()) return false

        // Regex limpa, corrigida e sem espaços ocultos
        val emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
        val emailRegex = emailPattern.toRegex()

        return email.matches(emailRegex)
    }

    fun validarSenha(senha: String): Boolean {

        return senha.isNotBlank() && senha.length  >= 8
    }
}


