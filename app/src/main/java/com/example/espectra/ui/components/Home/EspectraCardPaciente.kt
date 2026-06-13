package com.example.espectra.ui.components.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage // Certifique-se de ter a dependência do Coil para carregar imagens da URL do back-end
import com.example.espectra.R
import com.example.espectra.model.DataTelaHome

@Composable
fun EspectraCardPaciente(
    paciente: DataTelaHome,
    selecionado: Boolean,
    onClick: () -> Unit
) {
    // Definição dinâmica de cores de acordo com o estado de seleção (Layout do protótipo)
    val backgroundColor = if (selecionado) Color(0xFF2B78D6) else Color(0xFFF2F3F7)
    val borderColor = if (selecionado) Color(0xFF1A5BA8) else Color(0xFFE0E0E0)
    val textPrimaryColor = if (selecionado) Color.White else Color(0xFF2B78D6)

    val tagBackgroundColor = if (selecionado) Color.White.copy(alpha = 0.25f) else Color(0xFF2B78D6)
    val tagTextColor = Color.White

    // Une os diagnósticos vindos da API por " e " (Ex: "Autismo e TDAH")
    val diagnosticosFormatados = if (paciente.diagnosticoBreve.isNotEmpty()) {
        paciente.diagnosticoBreve.joinToString(" e ") { it.sigla }
    } else {
        "Sem diagnóstico"
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(1.dp, borderColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Renderização da Foto vinda do Back-end (com Fallback caso seja nula)
            AsyncImage(
                model = paciente.foto, // URL ou Base64 String tratada
                placeholder = painterResource(id = R.drawable.foto_paciente),
                error = painterResource(id = R.drawable.default_photo), // Sua imagem padrão
                contentDescription = "Foto de ${paciente.nome}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(65.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                // Nome em CAIXA ALTA (Uppercase) igual ao design fornecido
                Text(
                    text = paciente.nome.uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = textPrimaryColor
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Primeira Linha de Tags: Idade, CPF (como Registro) e Série Escolar
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TagInfo(text = "${paciente.idade} ANOS", bgColor = tagBackgroundColor, textColor = tagTextColor)

                    // Exibe o CPF ou ID como o número de registro do layout
                    TagInfo(text = paciente.cpf, bgColor = tagBackgroundColor, textColor = tagTextColor)

                    // Exibe a série vinda do back-end (Ex: "5ª Série")
                    TagInfo(text = paciente.serieEscolar, bgColor = tagBackgroundColor, textColor = tagTextColor)
                }

                Spacer(modifier = Modifier.height(6.dp))

                // Segunda Linha de Tags: Diagnóstico Combinado e Grau de Suporte
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TagInfo(text = diagnosticosFormatados, bgColor = tagBackgroundColor, textColor = tagTextColor)

                    // Exibe o Grau de Suporte dinâmico do back-end (Ex: "Grau 2")
                    TagInfo(text = paciente.grauSuporte, bgColor = tagBackgroundColor, textColor = tagTextColor)
                }
            }
        }
    }
}

@Composable
private fun TagInfo(text: String, bgColor: Color, textColor: Color) {
    Box(
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(4.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text.uppercase(),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )
    }
}