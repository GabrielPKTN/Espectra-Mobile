package com.example.espectra.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.espectra.ui.components.telasPerfil.HeaderPerfil

@Composable
fun TelaPerfilFamiliar(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderPerfil(null)

        Column(
            modifier = Modifier
        ) {
            Text("Nome Paciente")
        }
    }
}