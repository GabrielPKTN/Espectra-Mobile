package com.example.espectra.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.perfilFamiliar.PerfilFamiliar
import com.example.espectra.service.RetrofitFactory
import com.google.gson.Gson

class PerfilViewModel() : ViewModel() {

    private var _perfilFamiliar by mutableStateOf<PerfilFamiliar?>(null)
    val perfil get() = _perfilFamiliar

    suspend fun buscarPerfil(id: Int, token: String){
        try {

            val result = RetrofitFactory()
                .getEspectraService()
                .getPerfilById(token, id)

            Log.d("API_PERFIL", Gson().toJson(result))

            _perfilFamiliar = result

        }catch(error: Exception){
            Log.e("API_PERFIL", "Erro ao buscar perfil", error)

            _perfilFamiliar = null
        }
    }



}