package com.example.espectra.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.perfilFamiliar.Diagnostico
import com.example.espectra.service.RetrofitFactory
import com.example.espectra.service.editarFamiliar.DiagnosticoService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiagnosticoViewModel(

) : ViewModel() {

    private val service =
        RetrofitFactory()
            .getEspectraService()

    private val _diagnosticos = MutableStateFlow<List<Diagnostico>>(emptyList())
    val diagnosticos = _diagnosticos.asStateFlow()

    fun carregarDiagnosticos(token: String) {
        viewModelScope.launch {
            try {
                val response = service.getDiagnosticos(token)
                Log.d("DIAGNOSTICO_API", "Resposta: $response")
                Log.d("DIAGNOSTICO_API", "Items: ${response.items}")

                _diagnosticos.value = response.items


            } catch (e: Exception) {
                Log.e(
                    "DIAGNOSTICO_API",
                    "Erro ao carregar diagnósticos",
                    e
                )
                e.printStackTrace()
            }
        }
    }
}