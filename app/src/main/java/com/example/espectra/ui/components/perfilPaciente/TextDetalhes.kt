package com.example.espectra.ui.components.perfilPaciente

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.model.perfilFamiliar.Diagnostico

@Composable
fun TextDetalhes(
    nome: String,
    dataNascimento: String,
    idade: Int,
    serieEscolar: String,
    diagnosticos: List<Diagnostico>,
    grauSuporte: String
) {
    val inclusiveSans  = FontFamily(Font(R.font.inclusivesans_variablefont_wght))

    val textoDiagnosticos = when (diagnosticos.size) {
        0 -> ""
        1 -> diagnosticos[0].sigla
        else -> {
            val ultimo = diagnosticos.last().sigla
            diagnosticos.dropLast(1)
                .joinToString(", ") { it.sigla } + " e $ultimo"
        }
    }

    val trechoEscolar = if (serieEscolar == "CONCLUÍDO") {
        ""
    } else {
        " está no $serieEscolar e "
    }

    Text(
        text = "$nome nasceu em $dataNascimento," +
                " tem $idade anos,$trechoEscolar" +
                "possui diagnóstico de $textoDiagnosticos com " +
                "grau de suporte $grauSuporte.",

        fontFamily = inclusiveSans,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        color = Color(0xFF2B78D6),
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}