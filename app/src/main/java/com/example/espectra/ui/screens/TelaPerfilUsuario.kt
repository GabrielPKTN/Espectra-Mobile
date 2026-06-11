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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.ui.components.componentsGerais.EspectraButton
import com.example.espectra.ui.components.componentsGerais.Header

@Composable
fun TelaPerfilUsuario(padding: PaddingValues) {

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    // Estados para os campos de texto
    var email by remember { mutableStateOf("antonio.almeida@email.com") }
    var telefone by remember { mutableStateOf("(11) 99999-9999") }
    var dataNascimento by remember { mutableStateOf("01/01/1980") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // 1. Header Azul
        Column(
            modifier = Modifier
                .background(colorResource(R.color.primary_color))
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Header(padding = padding, colorResource = Color.White)
        }

        // 2. Coluna Principal de Conteúdo (Área Branca)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Foto subindo metade para dentro do Header Azul
            Image(
                painter = painterResource(R.drawable.responsavel),
                contentDescription = "Foto do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .offset(y = (-70).dp)
                    .clip(CircleShape)
                    .border(4.dp, Color.White, CircleShape)
            )


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-40).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    text = "ANTÔNIO ALMEIDA DA SILVA",
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontFamily = instrumentSans,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(R.color.primary_color)
                    )
                )

                // Espaço controlado entre o Nome e o primeiro Input
                Spacer(Modifier.height(40.dp))

                // Formulário com os Inputs
                Column(
                    //verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()

                ) {

                    Text(
                        text = "E-mail",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = instrumentSans,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.primary_color)
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    TextField(
                        value = email,
                        onValueChange = { email = it },
                        readOnly = true,
                        shape = RoundedCornerShape(16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = colorResource(R.color.primary_color)
                            )
                        },
                        modifier = Modifier.fillMaxWidth().border(
                            width = 1.dp,
                            color = colorResource(R.color.primary_color),
                            shape = RoundedCornerShape(16.dp)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(233, 233, 233, 255),
                            unfocusedContainerColor = Color(255, 255, 255, 255),
                            disabledContainerColor = Color(255, 255, 255, 255),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red
                        )
                    )
                    Spacer(Modifier.height(12.dp))
                    // Campo Telefone
                    Text(
                        text = "Telefone",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = instrumentSans,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.primary_color)
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    TextField(
                        value = telefone,
                        onValueChange = { telefone = it },
                        readOnly = true,
                        shape = RoundedCornerShape(16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Telefone",
                                tint = colorResource(R.color.primary_color)
                            )
                        },
                        modifier = Modifier.fillMaxWidth().border(
                            width = 1.dp,
                            color = colorResource(R.color.primary_color),
                            shape = RoundedCornerShape(16.dp)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(255, 255, 255, 255),
                            unfocusedContainerColor = Color(255, 255, 255, 255),
                            disabledContainerColor = Color(255, 255, 255, 255),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red
                        )
                    )

                    Spacer(Modifier.height(12.dp))
                    // Campo Data de Nascimento
                    Text(
                        text = "Data de Nascimento",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = instrumentSans,
                            textAlign = TextAlign.Start,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.primary_color)
                        ),
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    TextField(
                        value = dataNascimento,
                        onValueChange = { dataNascimento = it },
                        readOnly = true,
                        shape = RoundedCornerShape(16.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Data de Nascimento",
                                tint = colorResource(R.color.primary_color)
                            )
                        },
                        modifier = Modifier.fillMaxWidth().border(
                            width = 1.dp,
                            color = colorResource(R.color.primary_color),
                            shape = RoundedCornerShape(16.dp)
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color(255, 255, 255, 255),
                            unfocusedContainerColor = Color(255, 255, 255, 255),
                            disabledContainerColor = Color(255, 255, 255, 255),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            errorIndicatorColor = Color.Red
                        )
                    )
                }

                // Espaço entre o formulário e os botões
                Spacer(Modifier.height(40.dp))

                // Botões de Ação
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    EspectraButton(
                        text = "Editar informações pessoais",
                        onClick = {},
                        modifier = Modifier.width(300.dp),
                        buttonColor = ButtonDefaults.buttonColors(containerColor = Color.White),
                        corLetra = colorResource(R.color.primary_color)
                    )

                    EspectraButton(
                        text = "Excluir perfil",
                        onClick = {},
                        modifier = Modifier.width(300.dp)
                    )
                }
            }
        }
    }
}