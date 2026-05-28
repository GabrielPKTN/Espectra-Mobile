package com.example.espectra.ui.components.perfilPaciente

import android.widget.Button
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Composable
fun ButtonHabilidade(cor: Color, nomeHabilidade: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(cor),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = nomeHabilidade,
                color = Color.Black,
                fontFamily = FontFamily(
                    Font(R.font.instrumentsans_variablefont_wdth_wght)
                ),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 2.dp)

            )
        }
    }
}