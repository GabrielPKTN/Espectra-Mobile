package com.example.espectra.ui.components.componentsGerais

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EspectraButton(
    text: String,
    onClick: () -> Unit,
    corLetra: Color = Color.White,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B78D6)),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxWidth(0.6f) // Controla a largura para ficar igual ao layout
            .height(48.dp)
    ) {
        Text(text = text, color = corLetra, fontSize = 16.sp)
    }
}