package com.example.espectra.model


import com.google.gson.annotations.SerializedName

// 1. Objeto Principal da Resposta
data class ApiResponseHome(
    @SerializedName("development") val development: String,
    @SerializedName("api_description") val apiDescription: String,
    @SerializedName("request_date") val requestDate: String,
    @SerializedName("items") val items: ItemsHomeData?,
    @SerializedName("status") val status: Boolean,
    @SerializedName("status_code") val statusCode: Int,
    @SerializedName("message") val message: String?
)

// 2. Objeto "items"
data class ItemsHomeData(
    @SerializedName("id") val id: Int,
    @SerializedName("foto") val foto: String?,
    @SerializedName("nome") val nome: String,
    @SerializedName("tipo_usuario") val tipoUsuario: String,
    @SerializedName("familiares") val familiares: List<DataTelaHome>
)

// 3. Objeto de cada familiar/paciente da lista
data class DataTelaHome(
    @SerializedName("id") val id: Int,
    @SerializedName("cpf") val cpf: String,
    @SerializedName("foto") val foto: String?,
    @SerializedName("nome") val nome: String,
    @SerializedName("idade") val idade: Int,
    @SerializedName("grau_suporte") val grauSuporte: String,
    @SerializedName("serie_escolar") val serieEscolar: String,
    @SerializedName("data_nascimento") val dataNascimento: String,
    @SerializedName("diagnostico_breve") val diagnosticoBreve: List<DiagnosticoHomeData>
)

// 4. Objeto de cada diagnóstico do familiar
data class DiagnosticoHomeData(
    @SerializedName("sigla") val sigla: String,
    @SerializedName("id_transtorno") val idTranstorno: Int,
    @SerializedName("nome_completo") val nomeCompleto: String
)