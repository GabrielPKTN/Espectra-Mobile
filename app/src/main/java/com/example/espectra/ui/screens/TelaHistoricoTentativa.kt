package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import androidx.compose.foundation.lazy.LazyColumn
import com.example.espectra.ui.components.TelaHistoricoTentativa.CardTentativa

@Composable
fun TelaHistoricoTentativa(padding: PaddingValues) {

    val fontInclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))
    val fontInstrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    Column(

        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()

    ) {

        // Header (Componentizar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                tint = colorResource(R.color.primary_color),
                modifier = Modifier.size(32.dp)
            )

            // if(paciente tem foto?) { se sim mostra foto } else { se não mostra o ícone de usuário sem foto }

            Image(

                painter = painterResource(R.drawable.responsavel),
                contentDescription = "Foto do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = colorResource(R.color.primary_color),
                        shape = CircleShape
                    )
            )

        }
        // Fim header

        //Ínicio do conteúdo da tela
        Text(
            text = "9. Estende a mão em direção a um objeto oferecido",
            fontFamily = fontInclusiveSans,
            fontSize = 32.sp,
            color = Color(0, 0, 0, 255),
            modifier = Modifier
                .padding(vertical = 24.dp)
        )

        Column() {

            Text(
                text = "Tentativas:",
                fontFamily = fontInclusiveSans,
                fontSize = 20.sp,
                color = Color(0, 0, 0, 255),
                modifier = Modifier
                    .padding(vertical = 16.dp)
            )

            LazyColumn(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)

            ) {

                items(5) { index ->

                    CardTentativa()

                }


            }


        }

    }

}