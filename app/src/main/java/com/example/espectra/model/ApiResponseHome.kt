package com.example.espectra.model
import com.example.espectra.model.perfilFamiliar.Diagnostico

import com.google.gson.annotations.SerializedName


data class ApiResponseHome(
    @SerializedName("development") val development: String,
    @SerializedName("api_description") val apiDescription: String,
    @SerializedName("request_date") val requestDate: String,
    @SerializedName("items") val items: ItemsHomeData?,
    @SerializedName("status") val status: Boolean,
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("message") val message: String?
)


data class ItemsHomeData(
    @SerializedName("id") val id: Int,
    @SerializedName("foto") val foto: String?,
    @SerializedName("nome") val nome: String,
    @SerializedName("tipo_usuario") val tipoUsuario: String,

    // Mapeia os dois possíveis retornos da API usando tipos anuláveis (?)
    @SerializedName("familiares") val familiares: List<DataTelaHome>?,
    @SerializedName("pacientes") val pacientes: List<DataTelaHome>?
)


data class DataTelaHome(
    @SerializedName("id") val id: Int,
    @SerializedName("cpf") val cpf: String,
    @SerializedName("foto") val foto: String?,
    @SerializedName("nome") val nome: String,
    @SerializedName("idade") val idade: Int,
    @SerializedName("grau_suporte") val grauSuporte: String,
    @SerializedName("serie_escolar") val serieEscolar: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("diagnostico_breve") val diagnosticoBreve: List<Diagnostico>
)


