package com.example.espectra.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.espectra.ui.components.EspectraHeaderAzul
import com.example.espectra.ui.components.HeaderPerfil

@Composable
fun TelaPerfilFamiliar(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HeaderPerfil()
    }
}