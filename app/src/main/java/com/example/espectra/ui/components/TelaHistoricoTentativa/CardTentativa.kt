package com.example.espectra.ui.components.TelaHistoricoTentativa

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.model.tentativa.Tentativa

@Composable
fun CardTentativa(
    auxilio: String,
    resultado: Int,
    data: String,
    onCliqueDetalhes: () -> Unit
) {

    val fontInclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))
    val fontInstrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    var dataSplit = data.split("-").reversed()

    var data = "${dataSplit[0]}/${dataSplit[1]}/${dataSplit[2]}"


    Card (
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(254, 254, 254, 255)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )

    ) {

        Spacer(modifier = Modifier.height(15.dp))

        if(auxilio == "Auxílio total" || auxilio == "Auxílio parcial" ) {

            Text(
                text = "Atividade realizada com ${auxilio.lowercase()}",
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color(0,0,0),
                fontSize = 24.sp,
                fontFamily = fontInstrumentSans,
                fontWeight = FontWeight.Bold
            )

        } else {

            Text(
                text = "Atividade realizada de forma ${auxilio.lowercase()}",
                modifier = Modifier.padding(horizontal = 10.dp),
                color = Color(0,0,0),
                fontSize = 24.sp,
                fontFamily = fontInstrumentSans,
                fontWeight = FontWeight.Bold
            )

        }



        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            //Resultado
            Row() {

                Text(
                    text = "Resultado: ",
                    color = Color(0,0,0),
                    fontFamily = fontInclusiveSans
                )

                if(resultado == 0) {

                    Text(

                        text = "Falha",
                        color = Color(194, 0, 0, 255),
                        fontFamily = fontInstrumentSans,
                        fontWeight = FontWeight.Bold

                    )

                } else {

                    Text(

                        text = "Êxito",
                        color = Color(67, 194, 0, 255),
                        fontFamily = fontInstrumentSans,
                        fontWeight = FontWeight.Bold

                    )

                }

            }

            //Data
            Text(
                text = data,
                color = Color(0,0,0),
                fontFamily = fontInstrumentSans,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {

            Button(
                //Exibe detalhes a mais da tentativa
                onClick = onCliqueDetalhes,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.primary_color)
                )
            ) {
                Text(
                    text = "Ver detalhes",
                    fontFamily = fontInstrumentSans,
                    fontWeight = FontWeight.SemiBold
                    )
            }

        }

        Spacer(modifier = Modifier.height(15.dp))


    }

}
