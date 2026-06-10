package com.example.espectra.ui.screens.HistoricoTentativa

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.espectra.R
import com.example.espectra.model.tentativa.Tentativa
import androidx.compose.ui.text.PlatformTextStyle
import android.graphics.Paint.Style.STROKE
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ModalDetalhesTentativa(
    tentativa: Tentativa,
    onDismissRequest: () -> Unit
) {

    val fontInclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))
    val fontInstrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    var habilidade = tentativa.habilidade.nome_habilidade
    var corHabilidade = Color(0,0,0, 0)

    if (habilidade == "Socialização") {
        corHabilidade = Color(137, 199, 113, 255)
    } else if(habilidade == "Linguagem") {
        corHabilidade = Color(255, 200, 123, 255)
    } else if(habilidade == "Cognição") {
        corHabilidade = Color(113, 175, 255, 255)
    } else if(habilidade == "Auto-Cuidados") {
        corHabilidade = Color(204, 157, 255, 255)
    } else if(habilidade == "Desenvolvimento motor") {
        corHabilidade = Color(200, 200, 200, 255)
    }


    var dataNonFormatted = tentativa.data_tentativa.split("-").reversed()
    var data = "${dataNonFormatted[0]}/${dataNonFormatted[1]}/${dataNonFormatted[2]}"

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = habilidade,
                        color = corHabilidade.copy(alpha = 0.4f),
                        style = TextStyle(
                            fontSize = 33.sp,
                            fontWeight = FontWeight.Bold,
                            drawStyle = Stroke(
                                width = 3f
                            )
                        )
                    )

                    Text(
                        text = habilidade,
                        color = corHabilidade.copy(alpha = 0.8f),
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )

                }

                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Row(
                        modifier = Modifier
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(corHabilidade.copy(alpha = 0.4f)),
                    ) {

                        Text(
                            text = data,
                            style = TextStyle(
                                fontFamily = fontInstrumentSans,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(6.dp),
                            textAlign = TextAlign.Center
                        )

                    }

                }

                Spacer(Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth()) {

                    Text(
                        text = if(tentativa.numero_questao != null) "${tentativa.numero_questao}. " else "",
                        style = TextStyle(
                            fontFamily = fontInstrumentSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),
                    )

                    Text(
                        text = tentativa.comportamento,
                        style = TextStyle(
                            fontFamily = fontInstrumentSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp
                        ),

                    )

                }

                Spacer(Modifier.height(16.dp))

                Column(

                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {

                    Text(
                        text = "Resultado:",
                        style = TextStyle(
                            fontFamily = fontInstrumentSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )

                    Text(
                        text = if(tentativa.resultado == 0) "Falha" else "Êxito",
                        style = TextStyle(
                            fontFamily = fontInstrumentSans,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        ),
                        color = if (tentativa.resultado == 0) Color.Red else Color.Green
                    )

                }

                Spacer(modifier = Modifier.height(16.dp))

                if(tentativa.auxilio == "Auxílio total" || tentativa.auxilio == "Auxílio parcial" ) {

                    Text(
                        text = "Atividade realizada com ${tentativa.auxilio.lowercase()}",
                        fontSize = 20.sp,
                        fontFamily = fontInstrumentSans,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic
                    )

                } else {

                    Text(
                        text = "Atividade realizada de forma ${tentativa.auxilio.lowercase()}",
                        fontSize = 20.sp,
                        fontFamily = fontInstrumentSans,
                        fontWeight = FontWeight.Medium,
                        fontStyle = FontStyle.Italic
                    )

                }

                Spacer(Modifier.height(24.dp))

                Text(
                    text = "Observações:",
                    style = TextStyle(
                        fontFamily = fontInstrumentSans,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
                )

                Spacer(Modifier.height(24.dp))

                TextField(
                    value = if (tentativa.observacao != null) "${tentativa.observacao}" else "Nenhuma observação.",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .padding(8.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color(233, 233, 233, 255),
                        unfocusedContainerColor = Color(233, 233, 233, 255),
                        disabledContainerColor = Color(233, 233, 233, 255),
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent
                    )

                )

            }
        }
    }
}