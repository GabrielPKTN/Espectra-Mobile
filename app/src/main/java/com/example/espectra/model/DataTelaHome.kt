package com.example.espectra.model
import com.google.gson.annotations.SerializedName
data class DataTelaHome(
    val id: Int,
    val nome: String,
    val idade: Int,
    val diagnostico: String?,
    val fotoUrl: String?
)


data class PacientesResponse(
    val sucesso: Boolean,
    val mensagem: String?,
    val dados: List<DataTelaHome>?
)

data class ResponsavelHome(
    val id: Int,
    val cpf: String,
    val nome: String,

    @SerializedName(value = "familiares", alternate = ["dados", "pacientes", "listaPacientes"])
    val familiares: List<Paciente>
)

