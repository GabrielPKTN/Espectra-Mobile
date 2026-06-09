//package com.example.espectra.model
//import com.google.gson.annotations.SerializedName
//data class DataTelaHome(
//    val id: Int,
//    val cpf: String,
//    val foto: String?,
//    val nome: String,
//    val idade: Int,
//    @SerializedName("grau_suporte") val grauSuporte: String,
//    @SerializedName("serie_escolar") val serieEscolar: String,
//    @SerializedName("data_nascimento") val dataNascimento: String,
//    @SerializedName("diagnostico_breve") val diagnostico: List<DiagnosticoHomeData>
//)
//
//
//data class PacientesResponse(
//    val sucesso: Boolean,
//    val mensagem: String?,
//    val dados: List<DataTelaHome>?
//)
//
//data class ResponsavelHome(
//    val id: Int,
//    val cpf: String,
//    val nome: String,
//
//    @SerializedName(value = "familiares", alternate = ["dados", "pacientes", "listaPacientes"])
//    val familiares: List<Paciente>
//)
//
//data class ApiResponseResponsavel(
//    val development: String,
//    @SerializedName("api_description") val apiDescription: String,
//    @SerializedName("request_date") val requestDate: String,
//    val items: UsuarioHomeData?,
//    val status: Boolean,
//    @SerializedName("status_code") val statusCode: Int,
//    val message: String
//)
//
//data class UsuarioHomeData(
//    val id: Int,
//    val foto: String?,
//    val nome: String,
//    val familiares: List<FamiliarHomeData>,
//    @SerializedName("tipo_usuario") val tipoUsuario: String
//)
//
//
//
//data class DiagnosticoHomeData(
//    val sigla: String,
//    @SerializedName("id_transtorno") val idTranstorno: Int,
//    @SerializedName("nome_completo") val nomeCompleto: String
//)