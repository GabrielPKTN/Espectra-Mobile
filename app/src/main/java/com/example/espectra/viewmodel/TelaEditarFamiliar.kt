package com.example.espectra.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.espectra.model.editarFamiliar.AtualizarFamiliarRequest
import com.example.espectra.service.RetrofitFactory
import com.example.espectra.viewmodel.UploadUtils.createImagePart
import com.google.gson.Gson
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



    suspend fun atualizarPaciente(
        context: Context,
        token: String,
        idUsuario: Int,
        request: AtualizarFamiliarRequest,
        fotoUri: Uri?
    ) {

        val diagnosticoJson = Gson().toJson(
            request.diagnosticos.map {
                mapOf("id" to it)
            }
        )

        service.atualizarFamiliar(
            token = token,
            idUsuario = idUsuario,

            nome = request.nome.toTextRequestBody(),

            cpf = request.cpf.toTextRequestBody(),

            dataNascimento = request.dataNascimento.toTextRequestBody(),

            diagnostico = diagnosticoJson.toTextRequestBody(),

            foto = fotoUri?.let {
                createImagePart(
                    context,
                    it
                )
            }
        )
    }
}