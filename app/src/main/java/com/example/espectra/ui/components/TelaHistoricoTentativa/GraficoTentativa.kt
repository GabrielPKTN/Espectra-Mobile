package com.example.espectra.ui.components.TelaHistoricoTentativa

import android.R
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import java.util.Locale
import androidx.compose.ui.unit.dp
import com.example.espectra.model.tentativa.Tentativa
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.CartesianDrawingContext
import com.patrykandpatrick.vico.compose.cartesian.CartesianMeasuringContext
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.HorizontalAxis.Companion.rememberBottom
import com.patrykandpatrick.vico.compose.cartesian.axis.VerticalAxis
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.compose.cartesian.data.CartesianValueFormatter
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

@Composable
fun GraficoTentativa(

    datas: List<String>,
    listIndependente: List<Int>,
    listParcial: List<Int>,
    listTotal: List<Int>,
    listErro: List<Int>

) {



    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(254, 254, 254, 255)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        val datas = datas

        val corIndenpendente    = Color(53, 189, 0, 255)
        val corAjudaMinima      = Color(225, 208, 0, 255)
        val corAjudaTotal       = Color(255, 87, 34, 255)
        val corError            = Color(238, 6, 6, 255)

        val componentTexto = rememberTextComponent(style = TextStyle(Color.Black))

        val itemIndependente = LegendItem(
            icon = rememberShapeComponent(fill = Fill(color = corIndenpendente)),
            labelComponent = componentTexto,
            label = "Independente"
        )

        val itemAjudaMinima = LegendItem(
            icon = rememberShapeComponent(fill = Fill(color = corAjudaMinima)),
            labelComponent = componentTexto,
            label = "Ajuda Parcial"
        )

        val itemAjudaTotal = LegendItem(
            icon = rememberShapeComponent(fill = Fill(color = corAjudaTotal)),
            labelComponent = componentTexto,
            label = "Ajuda Total"
        )

        val itemErro = LegendItem(
            icon = rememberShapeComponent(fill = Fill(color = corError)),
            labelComponent = componentTexto,
            label = "Falha"
        )

        val legenda = rememberHorizontalLegend<CartesianMeasuringContext, CartesianDrawingContext>(

            items = {
                add(itemIndependente)
                add(itemAjudaMinima)
                add(itemAjudaTotal)
                add(itemErro)
            }

        )

        val xValues = List(datas.size) { it.toFloat() }

        val modelProducer = remember { CartesianChartModelProducer() }

        Spacer(modifier = Modifier.height(25.dp))

        LaunchedEffect(Unit) {
            modelProducer.runTransaction { lineSeries {

                series(
                    x = xValues,
                    y = listIndependente
                )

                series(
                    x = xValues,
                    y = listParcial
                )

                series(
                    x = xValues,
                    y = listTotal
                )

                series(
                    x = xValues,
                    y = listErro
                )

            } }
        }

        CartesianChartHost(
            chart = rememberCartesianChart(

                rememberLineCartesianLayer(

                    lineProvider = LineCartesianLayer.LineProvider.series(

                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(Fill(corIndenpendente)),
                            pointProvider = LineCartesianLayer.PointProvider.single(
                                LineCartesianLayer.Point(
                                    component = rememberShapeComponent(shape = CircleShape, fill = Fill(corIndenpendente)),
                                    size = 8.dp
                                )
                            )
                        ),

                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(Fill(corAjudaMinima)),
                            pointProvider = LineCartesianLayer.PointProvider.single(
                                LineCartesianLayer.Point(
                                    component = rememberShapeComponent(shape = CircleShape, fill = Fill(corAjudaMinima)),
                                    size = 8.dp
                                )
                            )
                        ),

                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(Fill(corAjudaTotal)),
                            pointProvider = LineCartesianLayer.PointProvider.single(
                                LineCartesianLayer.Point(
                                    component = rememberShapeComponent(shape = CircleShape, fill = Fill(corAjudaTotal)),
                                    size = 8.dp
                                )
                            )
                        ),

                        LineCartesianLayer.rememberLine(
                            fill = LineCartesianLayer.LineFill.single(Fill(corError)),
                            pointProvider = LineCartesianLayer.PointProvider.single(
                                LineCartesianLayer.Point(
                                    component = rememberShapeComponent(shape = CircleShape, fill = Fill(corError)),
                                    size = 8.dp
                                )
                            )
                        ),

                        )
                ),

                legend = legenda,
                bottomAxis = rememberBottom(
                    valueFormatter = { _, value, _ ->
                        val index = value.toInt()
                        if (index in datas.indices) datas[index].substring(0, 5) else ""
                    }
                ),

                startAxis = VerticalAxis.rememberStart(),

                getXStep = {1.0}

            ),

            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(vertical = 12.dp)
                .height(270.dp),

            modelProducer = modelProducer

        )

    }

}