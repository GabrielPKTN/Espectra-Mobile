package com.example.espectra.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Shape

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R


@Composable
fun EspectraCardPaciente( selecionado: Boolean,
                         onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick() },
        shape = RoundedCornerShape(16.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F3F7)
        ),
        border = BorderStroke(
            1.dp,
            if (selecionado)
                Color(0xFF2B78D6)
            else
                Color(0xFFE0E0E0)


        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.foto_paciente),
                contentDescription = "Foto Paciente",
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {

                Text(
                    text = "Joao Pedro Silva Pereira",
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(6.dp))
                Row {
                    Text(
                        text = " 12 Anos ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = " 00002513200 ",
                        fontSize = 16.sp
                    )
                    Text(
                        text = " 5 serie ",
                        fontSize = 16.sp
                    )
                }

                    Spacer(modifier = Modifier.height(6.dp))

                        Row {
                            Text(
                                text = " Autismo e TDAH ",
                                fontSize = 16.sp
                            )
                            Text(
                                text = "  Grau 2 ",
                                fontSize = 16.sp
                            )

                        }

                    }



                }

            }
        }










