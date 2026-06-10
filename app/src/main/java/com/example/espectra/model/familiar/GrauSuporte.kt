package com.example.espectra.model.familiar

data class GrauSuporte(
    val id: Int,
    val grau: String
)

val grauSuporte = listOf(
    GrauSuporte(
        id = 1,
        grau = "GRAU 1"
    ),

    GrauSuporte(
        id = 2,
        grau = "GRAU 2"
    ),

    GrauSuporte(
        id = 1,
        grau = "GRAU 3"
    )
)