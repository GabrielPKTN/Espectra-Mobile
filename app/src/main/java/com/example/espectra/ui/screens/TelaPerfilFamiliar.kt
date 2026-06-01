package com.example.espectra.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.R
import com.example.espectra.ui.components.perfilPaciente.ButtonHabilidade
import com.example.espectra.ui.components.perfilPaciente.ColunaLegenda
import com.example.espectra.ui.components.perfilPaciente.HeaderPerfil
import com.example.espectra.ui.components.perfilPaciente.LegendaGrafico
import com.example.espectra.viewmodel.PerfilViewModel
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianLayerRangeProvider
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.compose.cartesian.data.columnSeries
import com.patrykandpatrick.vico.compose.cartesian.layer.ColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.LineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberColumnCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.common.Fill
import com.patrykandpatrick.vico.compose.common.component.LineComponent
import com.patrykandpatrick.vico.compose.common.component.TextComponent
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent

@Composable
fun TelaPerfilFamiliar(
    viewModel: PerfilViewModel = viewModel(),
    token: String,
    idPaciente: Int
) {

    val corSocializacao         = Color(162, 226, 137, 255)
    val corLinguagem            = Color(255, 200, 123, 255)
    val corCognicao             = Color(113, 175, 255, 255)
    val corAutoCuidados         = Color(210, 147, 240, 255)
    val corDesenvolvimentoMotor = Color(200, 200, 200, 255)

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))
    val inclusiveSans  = FontFamily(Font(R.font.inclusivesans_variablefont_wght))



    LaunchedEffect(token, idPaciente) {
        viewModel.buscarPerfil(idPaciente, token)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(40.dp),


    ) {

        HeaderPerfil()

        // NOME E DETALHES DO PACIENTE

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "JOÃO PEDRO SILVA PEREIRA",
                fontSize = 24.sp,
                fontFamily = instrumentSans,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B78D6),
            )

            Text(
               text = "Detalhes do paciente",
                fontFamily = inclusiveSans,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 52.dp)
                    .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp))
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Editar informações pessoais",
                    color = Color(0xFF2B78D6),
                    fontFamily = instrumentSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

        }

        // GRÁFICO
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            Text(
                text = "Grafico de Desempenho",
                fontSize = 24.sp,
                fontFamily = instrumentSans,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B78D6),
            )

            ColunaLegenda()

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {


                val modelProducer = remember { CartesianChartModelProducer() }
                val columnProvider = ColumnCartesianLayer.ColumnProvider.series(
                    rememberLineComponent(fill = Fill(corSocializacao), thickness = 10.dp),
                    rememberLineComponent(fill = Fill(corLinguagem), thickness = 10.dp),
                    rememberLineComponent(fill = Fill(corCognicao), thickness = 10.dp),
                    rememberLineComponent(fill = Fill(corAutoCuidados), thickness = 10.dp),
                    rememberLineComponent(fill = Fill(corDesenvolvimentoMotor), thickness = 10.dp),
                )

                val dataLabelComponent = remember {
                    TextComponent(
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                LaunchedEffect(Unit) {
                    modelProducer.runTransaction {
                        columnSeries {
                            series(1)
                            series(2)
                            series(5)
                            series(3)
                            series(6)
                        }
                    }
                }

                CartesianChartHost(
                    chart = rememberCartesianChart(
                        rememberColumnCartesianLayer(
                            columnProvider,
                            dataLabel = dataLabelComponent,

                            dataLabelValueFormatter = CartesianValueFormatter{
                                _, value, _ ->
                                if (value.toInt() == 1){
                                    "${value.toInt()} ano"
                                }else{
                                    "${value.toInt()} anos"
                                }
                                                                             },
                            rangeProvider = CartesianLayerRangeProvider.fixed(
                                minY = 0.0,
                                maxY = 7.0
                            )
                        ),
                        bottomAxis = HorizontalAxis.rememberBottom(
                            line = rememberLineComponent(
                                thickness = 3.dp
                            ),
                            tick = null,
                            guideline = null,
                            itemPlacer = remember { HorizontalAxis.ItemPlacer.aligned() },
                            label = null,
                        )
                    ),
                    modelProducer = modelProducer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                )


//
//                Text(
//                    text = "Essa criança ainda não tem um gráfico de desempenho, ou não está visível para os responsáveis",
//                    fontFamily = FontFamily(
//                        Font(R.font.inclusivesans_variablefont_wght)
//                    ),
//                    fontSize = 18.sp,
//                    textAlign = TextAlign.Center
//                )

            }
        }




        // BOTÕES DE HABILIDADE

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonHabilidade(cor = corSocializacao, nomeHabilidade = "Socialização")
            ButtonHabilidade(cor = corLinguagem, nomeHabilidade = "Linguagem")
            ButtonHabilidade(cor = corCognicao, nomeHabilidade = "Cognição")
            ButtonHabilidade(cor = corAutoCuidados, nomeHabilidade = "Auto-Cuidados")
            ButtonHabilidade(cor = corDesenvolvimentoMotor, nomeHabilidade = "Desenvolvimento motor")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .padding(horizontal = 52.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(40.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1212)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Excluir familiar",
                    color = Color.White,
                    fontFamily = instrumentSans,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,

                )
            }
        }

    }
}