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

    // Estados para os campos de texto (coloquei valores mockados baseados no seu design)
    var email by remember { mutableStateOf("antonio.almeida@email.com") }
    var telefone by remember { mutableStateOf("(11) 99999-9999") }
    var dataNascimento by remember { mutableStateOf("01/01/1980") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(colorResource(R.color.primary_color))
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
        ) {
            Header(padding = padding, colorResource = Color.White)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(R.drawable.responsavel),
                contentDescription = "Foto do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(172.dp)
                    .clip(CircleShape)
            )

            Spacer(Modifier.height(48.dp))

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

            Spacer(Modifier.height(48.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // Campo Email
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
                    modifier = Modifier.border(
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

                // Campo Telefone
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
                    modifier = Modifier.border(
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

                // Campo Data de Nascimento
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
                    modifier = Modifier.border(
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

            Spacer(Modifier.height(48.dp))

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