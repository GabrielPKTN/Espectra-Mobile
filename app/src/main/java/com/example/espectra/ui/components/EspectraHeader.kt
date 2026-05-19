package com.example.espectra.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R

@Composable
fun EspectraHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(Color(0xFF2B78D6)) // Azul das telas
            .padding(top = 40.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.height(12.dp))
        // Substitua pelo ID correto do seu ícone de quebra-cabeça
        Image(
            painter = painterResource(id = R.drawable.logotipo),
            contentDescription = "Logo Espectra",
            modifier = Modifier
                .width(155.dp)
                .height(82.dp)
        )
    }
}