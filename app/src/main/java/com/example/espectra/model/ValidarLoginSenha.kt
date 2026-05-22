package com.example.espectra.model



object ValidarLoginSenha {

    //valida se o email nao está vazio e segue o padrao correto
    fun validarEmail(email: String): Boolean{
        if(email.isBlank()) return false
        val emailRegex = "ˆ[ A-Za-z0-9+_.-] +@ [A-za-z]{2,} [A-za-z]{2,}\\$ ".toRegex()
        return email.matches(emailRegex)
    }


    fun validarSenha(senha: String ): Boolean {
        return senha.isBlank() && senha.length >= 8
    }

}




