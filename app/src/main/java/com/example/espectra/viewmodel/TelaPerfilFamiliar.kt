package com.example.espectra.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.perfilFamiliar.DataPerfilFamiliar
import com.example.espectra.service.EspectraApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PerfilViewModel(
    private val service: EspectraApiService
) : ViewModel() {

    private val _perfil = MutableStateFlow<DataPerfilFamiliar?>(null)
    val perfil: StateFlow<DataPerfilFamiliar?> = _perfil

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    private val _erro = MutableStateFlow<String?>(null)
    val erro: StateFlow<String?> = _erro

    fun carregarPerfil(id: Int) {

        viewModelScope.launch {

            _loading.value = true

            try {

                val response = service.buscarPaciente(id)

                _perfil.value = response.body()

            } catch (e: Exception) {

                _erro.value = e.message

            } finally {

                _loading.value = false
            }
        }
    }
}