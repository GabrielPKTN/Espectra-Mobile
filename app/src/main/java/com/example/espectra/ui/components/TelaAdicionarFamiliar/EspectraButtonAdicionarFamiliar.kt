package com.example.espectra.ui.components.TelaAdicionarFamiliar

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
fun EspectraButtonAdicionarFamiliarWhite(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
        shape = RoundedCornerShape(18.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 12.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .fillMaxWidth(0.4f)
            .height(48.dp)
    ) {
        Text(text = text, color = Color(0xFF3277CF), fontSize = 16.sp)
    }
}

@Composable
fun EspectraButtonAdicionarFamiliarBlue(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3277CF)),
        shape = RoundedCornerShape(18.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 6.dp,
            pressedElevation = 12.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier
            .fillMaxWidth(0.4f)
            .height(48.dp)
    ) {
        Text(text = text, color = Color(0xFFF2F3F7), fontSize = 16.sp)
    }
}
