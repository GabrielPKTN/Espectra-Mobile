package com.example.espectra.ui.components
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.espectra.R

@Composable
fun HeaderPerfil(modifier: Modifier = Modifier, fotoPerfil) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(Color(0xFF2B78D6)) // Azul das telas
            .padding(top = 40.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logotipo_branco),
            contentDescription = "Logo  Espectra",
            modifier = Modifier
                .width(155.dp)
                .height(82.dp)
        )
    }
}


