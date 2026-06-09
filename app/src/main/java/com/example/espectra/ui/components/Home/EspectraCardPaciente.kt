package com.example.espectra.ui.components.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.model.DataTelaHome

@Composable
fun EspectraCardPaciente(
    paciente: DataTelaHome,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    // CORREÇÃO: Ajustado para usar 'diagnosticoBreve' que veio mapeado do seu Model
    val diagnosticosFormatados = if (paciente.diagnosticoBreve.isNotEmpty()) {
        paciente.diagnosticoBreve.joinToString(", ") { it.sigla }
    } else {
        "Sem diagnóstico"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F3F7)
        ),
        border = BorderStroke(
            1.dp,
            if (selecionado) Color(0xFF2B78D6) else Color(0xFFE0E0E0)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.foto_paciente),
                contentDescription = "Foto de ${paciente.nome}",
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = paciente.nome,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${paciente.idade} Anos",
                    fontSize = 16.sp,
                    color = Color(0xFF666666)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Exibe os diagnósticos formatados (Ex: "TDAH, TEA" ou "Sem diagnóstico")
                Text(
                    text = diagnosticosFormatados,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF2B78D6)
                )
            }
        }
    }
}