package com.example.espectra.storage

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class GerenciarSessao(contexto: Context) {

    protected val chaveMestra =MasterKey.Builder(contexto)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    private val preferenciasCompartilhadas = EncryptedSharedPreferences.create(
        contexto,
        "espectra_preferencias_seguras",
        chaveMestra,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun salvarSessao(token: String, idUsuario: Int) {
        preferenciasCompartilhadas.edit().apply {
            putString("TOKEN_AUTENTICACAO", token)
            putInt("ID_USUARIO", idUsuario)
            apply()
        }
    }

    fun buscarToken(): String? = preferenciasCompartilhadas.getString("TOKEN_AUTENTICACAO", null)

    fun buscarIdUsuario(): Int = preferenciasCompartilhadas.getInt("ID_USUARIO", 0)


    fun limparSessao() {
        preferenciasCompartilhadas.edit().clear().apply()
    }
}