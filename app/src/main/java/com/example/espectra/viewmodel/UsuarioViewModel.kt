package com.example.espectra.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.usuario.Usuario

class UsuarioViewModel: ViewModel() {

    private var _usuario by mutableStateOf<Usuario?>(null)
    val usuario get() = _usuario

    suspend fun BuscarDadosUsuario(idUsuario: Int) {

        try {



        } catch (error: Exception) {
            _usuario = null
        }

    }

}