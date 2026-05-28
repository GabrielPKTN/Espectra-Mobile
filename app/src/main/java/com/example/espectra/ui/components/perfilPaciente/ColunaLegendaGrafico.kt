package com.example.espectra.ui.components.perfilPaciente

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R

@Composable
fun ColunaLegenda(modifier: Modifier = Modifier) {

    val corSocializacao         = Color(162, 226, 137, 255)
    val corLinguagem            = Color(255, 200, 123, 255)
    val corCognicao             = Color(113, 175, 255, 255)
    val corAutoCuidados         = Color(210, 147, 240, 255)
    val corDesenvolvimentoMotor = Color(200, 200, 200, 255)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        Text(
            text = "Legenda:",
            fontFamily = FontFamily(
                Font(R.font.instrumentsans_variablefont_wdth_wght)
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp

        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            LegendaGrafico("Socialização", corSocializacao)
            LegendaGrafico("Cognição", corCognicao)
            LegendaGrafico("Auto-cuidados", corAutoCuidados)

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,

            ) {

            LegendaGrafico("Linguagem", corLinguagem)
            LegendaGrafico("Desenvovimento-motor", corDesenvolvimentoMotor)

        }

    }
}

@Composable
fun LegendaGrafico(habilidade: String, cor: Color) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(cor, RoundedCornerShape(4.dp))
            )

        Text(
            text = habilidade,
            fontFamily = FontFamily(
                Font(R.font.instrumentsans_variablefont_wdth_wght)
            ),
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}