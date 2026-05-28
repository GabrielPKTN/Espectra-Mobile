package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.example.espectra.ui.components.TelaHistoricoTentativa.CardTentativa
import com.example.espectra.ui.components.TelaHistoricoTentativa.GraficoTentativa
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.lineSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.common.LegendItem
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.rememberHorizontalLegend

@Composable
fun TelaHistoricoTentativa(padding: PaddingValues) {

    val fontInclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))
    val fontInstrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    val scrollState = rememberScrollState()

    Column(

        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {

        // Header (Componentizar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(top = 16.dp),
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
            fontSize = 24.sp,
            modifier = Modifier
                .padding(vertical = 24.dp),
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Column() {

            Text(
                text = "Tentativas:",
                fontFamily = fontInstrumentSans,
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(vertical = 24.dp)
            )

            LazyColumn(

                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                contentPadding = PaddingValues(top = 32.dp, bottom = 32.dp),

            ) {

                items(5) { index ->

                    CardTentativa()

                }


            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Representação gráfica",
                color = Color.Black,
                fontFamily = fontInstrumentSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(25.dp))

            GraficoTentativa()

            Spacer(modifier = Modifier.height(100.dp))

        }

    }

}