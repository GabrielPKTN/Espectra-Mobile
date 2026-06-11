package com.example.espectra.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.Atividades.Atividade
import com.example.espectra.model.Atividades.AtividadeResponse
import com.example.espectra.service.RetrofitFactory
import com.google.gson.Gson
import retrofit2.HttpException

class TelaAtividadesViewModel : ViewModel(
) {


    private var _atividades by mutableStateOf<List<Atividade>>(emptyList())
    val atividades: List<Atividade> get() = _atividades

    val atividadesConcluidas: List<Atividade> get() =
        _atividades.filter { atividade -> atividade.concluida == 1 }

    val atividadesEmAndamento: List<Atividade> get() =
        _atividades.filter { atividade -> atividade.concluida == 0 }

    suspend fun buscarAtividades(token: String, idPaciente: Int, idHabilidade: Int){
        try {

            val result = RetrofitFactory()
                .getEspectraService()
                .getAtividades(token, idPaciente, idHabilidade)


            Log.d("API_PERFIL", Gson().toJson(result.items))

            _atividades = result.items
        }catch (error: HttpException) {

            Log.e(
                "API_PERFIL",
                "HTTP ${error.code()} - ${error.message()}"
            )

            Log.e(
                "API_PERFIL",
                "Body: ${error.response()?.errorBody()?.string()}"
            )

            _atividades = emptyList()

        } catch (error: Exception) {

            Log.e(
                "API_PERFIL",
                "Erro genérico",
                error
            )

            _atividades = emptyList()
        }
    }
}