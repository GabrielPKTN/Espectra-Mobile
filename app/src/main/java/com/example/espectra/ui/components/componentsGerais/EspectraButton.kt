package com.example.espectra.ui.components.componentsGerais

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import java.time.format.TextStyle


@Composable
fun EspectraButton(
    text: String,
    onClick: () -> Unit,
    corLetra: Color = Color.White,
    buttonColor: ButtonColors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2B78D6)),
    modifier: Modifier = Modifier
) {

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    Button(
        onClick = onClick,
        colors = buttonColor,
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 12.dp
        ),
        shape = RoundedCornerShape(24.dp),
        modifier = modifier
            .fillMaxWidth(0.6f) // Controla a largura para ficar igual ao layout
            .height(48.dp)
    ) {
        Text(text = text, color = corLetra, fontSize = 16.sp, fontFamily = instrumentSans, fontWeight = FontWeight.Bold)
    }
}