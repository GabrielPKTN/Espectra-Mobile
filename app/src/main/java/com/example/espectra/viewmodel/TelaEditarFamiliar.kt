package com.example.espectra.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.editarFamiliar.AtualizarFamiliarRequest
import com.example.espectra.service.RetrofitFactory
import com.example.espectra.viewmodel.UploadUtils.createImagePart
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class EditarFamiliarViewModel : ViewModel() {

    private val service =
        RetrofitFactory()
            .getEspectraService()

    fun String.toTextRequestBody(): RequestBody {
        return toRequestBody("text/plain".toMediaType())
    }



    fun atualizarPaciente(
        context: Context,
        token: String,
        idUsuario: Int,
        request: AtualizarFamiliarRequest,
        fotoUri: Uri?
    ) {

        viewModelScope.launch {
            try {
                val diagnosticoJson = Gson().toJson(
                    request.diagnostico.map {
                        mapOf("id" to it)
                    }
                )

                val response = service.atualizarFamiliar(
                    token = token,
                    idUsuario = idUsuario,


                    idFamiliar = request.id
                        .toString()
                        .toTextRequestBody(),
                    nome = request.nome.toTextRequestBody(),

                    dataNascimento = request.dataNascimento.toTextRequestBody(),

                    diagnostico = diagnosticoJson.toTextRequestBody(),

                    serieEscolar = request.serieEscolar
                        .toString()
                        .toTextRequestBody(),

                    grauSuporte = request.grauSuporte
                        .toString()
                        .toTextRequestBody(),

                    foto = fotoUri?.let {
                        createImagePart(
                            context,
                            it
                        )
                    }
                )

                Log.d("respostaEditar", response.toString())

            }catch (e: Exception){
                Log.e(
                    "EditarFamiliarViewModel",
                    "Erro ao atualizar paciente",
                    e
                )
            }

        }


    }
}