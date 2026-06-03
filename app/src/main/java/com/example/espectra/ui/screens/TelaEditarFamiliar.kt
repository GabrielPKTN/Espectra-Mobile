package com.example.espectra.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.ui.components.perfilPaciente.editarFamiliar.SelectExample
import com.example.espectra.ui.components.perfilPaciente.editarFamiliar.TextFieldEditar

@Composable
fun TelaEditarFamiliar(
    //navController: NavController
) {

    var nome by remember { mutableStateOf("") }
    var dataNascimento by remember { mutableStateOf("") }
    var serieEscolar by remember { mutableStateOf("") }
    var diagnostico by remember { mutableStateOf("") }
    var grauSuporte by remember { mutableStateOf("") }

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
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
                        .padding(20.dp),
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
                            TextFieldEditar(
                                value = dataNascimento,
                                onValueChange = {dataNascimento = it},
                                placeholder = "DD/MM/AAAA",
                                modifier = Modifier
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                            )

                            SelectExample(
                                fieldModifier = Modifier
                                    .weight(1f)
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    ),
                                modifier = Modifier.weight(1f),
                                placeholder = "série escolar"
                            )

                        }



                        SelectExample(
                            fieldModifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                .fillMaxWidth(),
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "Grau de suporte"
                        )


                        SelectExample(
                                fieldModifier = Modifier
                                    .border(
                                        width = 2.dp,
                                        color = Color(0xFF3277CF),
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .fillMaxWidth(),
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = "Diagnóstico"
                        )


                    }

                }

            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Button(
                    onClick = {
                        //navController.navigate("editar_familiar")
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