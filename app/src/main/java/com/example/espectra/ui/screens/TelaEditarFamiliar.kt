package com.example.espectra.ui.screens

import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.i18n.DateTimeFormatter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.R
import com.example.espectra.model.editarFamiliar.AtualizarFamiliarRequest
import com.example.espectra.model.perfilFamiliar.Diagnostico
import com.example.espectra.ui.components.editarFamiliar.Select
import com.example.espectra.ui.components.editarFamiliar.TextFieldData
import com.example.espectra.ui.components.editarFamiliar.TextFieldEditar
import com.example.espectra.viewmodel.DiagnosticoViewModel
import com.example.espectra.viewmodel.EditarFamiliarViewModel
import com.example.espectra.viewmodel.PerfilViewModel
import java.time.LocalDate

@Composable
fun TelaEditarFamiliar(
    //navController: NavController
    viewModel: DiagnosticoViewModel = viewModel(),
    viewModelEditar: EditarFamiliarViewModel = viewModel(),
    token: String,
    idUsuario: Int,
    idFamiliar: Int
) {

    var nome by remember { mutableStateOf("") }
    var dataNascimentoTexto by remember {
        mutableStateOf(TextFieldValue())
    }

    val context  = LocalContext.current


    val diagnosticos by viewModel.diagnosticos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarDiagnosticos(token)
    }

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    data class SerieEscolar(
        val id: Int,
        val nome: String
    )

    data class GrauSuporte(
        val id: Int,
        val nome: String
    )

    val series = listOf(
        SerieEscolar(1, "MATERNAL"),
        SerieEscolar(2, "JARDIM I"),
        SerieEscolar(3, "JARDIM II"),
        SerieEscolar(4, "1º ANO"),
        SerieEscolar(5, "2º ANO"),
        SerieEscolar(6, "3º ANO"),
        SerieEscolar(7, "4º ANO"),
        SerieEscolar(8, "5º ANO"),
        SerieEscolar(9, "6º ANO"),
        SerieEscolar(10, "7º ANO"),
        SerieEscolar(11, "8º ANO"),
        SerieEscolar(12, "9º ANO"),
        SerieEscolar(13, "1º MÉDIO"),
        SerieEscolar(14, "2º MÉDIO"),
        SerieEscolar(15, "3º MÉDIO"),
        SerieEscolar(16, "3º CONCLUÍDO")

    )

    val graus = listOf(
        GrauSuporte(1, "GRAU I"),
        GrauSuporte(2, "GRAU II"),
        GrauSuporte(3, "GRAU III")
    )

    var serieSelecionada by remember {
        mutableStateOf<SerieEscolar?>(null)
    }

    var grauSelecionado by remember {
        mutableStateOf<GrauSuporte?>(null)
    }

    var diagnosticosSelecionados by remember {
        mutableStateOf<List<Diagnostico>>(emptyList())
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(
                onClick = {
                    //navController.popBackStack()
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Voltar",
                    colorFilter = ColorFilter.tint(Color(0xFF2B78D6))
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.default_photo),
                contentDescription = "Foto padrão",

                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)

            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(48.dp)
                    ),
                colors = CardDefaults.cardColors(Color(0xFFF3F3F3)),
                shape = RoundedCornerShape(48.dp),
                border = BorderStroke(
                    width = 2.dp,
                    color = Color(0xFFD9D9D9)
                ),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,


                ) {
                    Text("Edite as informações\n do familiar",
                        modifier = Modifier.padding(28.dp),
                        textAlign = TextAlign.Center,
                        color = Color(0xFF3277CF),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 48.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextFieldEditar(
                            value = nome,
                            onValueChange = {nome = it},
                            placeholder = "Nome Completo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF3277CF),
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)

                        ) {

                            TextFieldData(
                                value = dataNascimentoTexto,
                                onValueChange = { novoValor ->

                                    val numeros = novoValor.text
                                        .filter(Char::isDigit)
                                        .take(8)

                                    val textoFormatado = when {
                                        numeros.length <= 2 -> numeros

                                        numeros.length <= 4 ->
                                            "${numeros.substring(0, 2)}/${numeros.substring(2)}"

                                        else ->
                                            "${numeros.substring(0, 2)}/" +
                                                    "${numeros.substring(2, 4)}/" +
                                                    numeros.substring(4)
                                    }

                                    dataNascimentoTexto = TextFieldValue(
                                        text = textoFormatado,
                                        selection = TextRange(textoFormatado.length)
                                    )
                                },
                                placeholder = "DD/MM/AAAA",
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            )

                            Select(
                                fieldModifier = Modifier
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                modifier = Modifier.weight(1f),
                                placeholder = "série escolar",
                                options = series,
                                selectedOption = serieSelecionada,
                                onOptionSelected = {
                                    serieSelecionada = it
                                },
                                optionLabel = { serie ->
                                    serie.nome
                                }
                            )

                        }


                        Select(
                            fieldModifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                .fillMaxWidth(),
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = "Grau de suporte",
                            options = graus,
                            selectedOption = grauSelecionado,
                            onOptionSelected = { grauSelecionado = it },
                            optionLabel = { grau -> grau.nome }
                        )


                        Select(
                                fieldModifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .fillMaxWidth(),
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "Diagnóstico",
                                options = diagnosticos,
                                selectedOption = null,
                                onOptionSelected = { diagnostico ->

                                    if (diagnostico !in diagnosticosSelecionados) {
                                        diagnosticosSelecionados = diagnosticosSelecionados + diagnostico
                                    }
                                },
                                optionLabel = { diagnostico ->
                                    diagnostico.sigla
                                }
                        )

                        FlowRow (
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ){
                            diagnosticosSelecionados.forEach { diagnostico ->

                                InputChip(
                                    selected = true,
                                    onClick = {},
                                    label = {
                                        Text(
                                            diagnostico.sigla,
                                            color = Color.White
                                            )
                                    },
                                    colors = InputChipDefaults.inputChipColors(
                                        selectedContainerColor = Color(0xFF3277CF),
                                    ),
                                    trailingIcon = {
                                        Image(
                                            painter = painterResource(R.drawable.close),
                                            contentDescription = "Remover",
                                            modifier = Modifier
                                                .size(14.dp)
                                                .clickable {
                                                    diagnosticosSelecionados =
                                                        diagnosticosSelecionados.filter { it != diagnostico }
                                                }
                                        )
                                    }

                                )
                            }
                        }

                    }

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                Button(
                    onClick = {

                        val partes = dataNascimentoTexto.text.split("/")

                        val dataNascimento = "${partes[2]}-${partes[1]}-${partes[0]}"

                        if (
                            nome.isBlank() ||
                            serieSelecionada == null ||
                            grauSelecionado == null ||
                            diagnosticosSelecionados.isEmpty()
                        ) {
                            Toast.makeText(
                                context,
                                "Preencha todos os campos obrigatórios",
                                Toast.LENGTH_SHORT
                            ).show()
                            return@Button
                        }

                        val idSerieEscolar = serieSelecionada!!.id
                        val idGrauSuporte = grauSelecionado!!.id

                        val request = AtualizarFamiliarRequest(
                            id = idFamiliar,
                            nome = nome,
                            dataNascimento = dataNascimento,
                            diagnostico = diagnosticosSelecionados.map { it.id },
                            grauSuporte = idGrauSuporte,
                            serieEscolar = idSerieEscolar
                        )

                        Log.d("datamsg", request.toString())

                        viewModelEditar.atualizarPaciente(
                            context = context,
                            token = token,
                            idUsuario = idUsuario,
                            request = request,
                            fotoUri = null
                        )

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(24.dp))
                        .height(48.dp),
                    colors = ButtonDefaults.buttonColors(Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) {

                    Text(
                        text = "Salvar alterações",
                        color = Color(0xFF2B78D6),
                        fontFamily = instrumentSans,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
    }
}

