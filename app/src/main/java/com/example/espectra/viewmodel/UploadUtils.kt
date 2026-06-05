package com.example.espectra.viewmodel

import android.content.Context
import android.net.Uri
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

object UploadUtils {

    fun String.toTextRequestBody(): RequestBody {
        return this.toRequestBody(
            "text/plain".toMediaType()
        )
    }

    fun uriToFile(
        context: Context,
        uri: Uri
    ): File {

        val inputStream =
            context.contentResolver.openInputStream(uri)
                ?: throw Exception("Não foi possível abrir o arquivo")

        val file = File.createTempFile(
            "foto",
            ".jpg",
            context.cacheDir
        )

        inputStream.use { input ->
            file.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return file
    }

    fun createImagePart(
        context: Context,
        uri: Uri
    ): MultipartBody.Part {

        val file = uriToFile(
            context = context,
            uri = uri
        )

        val requestBody = file.asRequestBody(
            "image/*".toMediaType()
        )

        return MultipartBody.Part.createFormData(
            "foto",
            file.name,
            requestBody
        )
    }
}