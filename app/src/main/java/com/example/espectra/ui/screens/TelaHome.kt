package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.ui.components.EspectraButton
import com.example.espectra.ui.components.EspectraCardPaciente
import com.example.espectra.ui.components.EspectraHeaderAzul
import com.example.espectra.ui.components.EspectraHeaderBranco
import com.example.espectra.ui.components.EspectraTextField


@Composable
fun TelaHome(

    //navController: NavHostController, viewModel: CadastroViewModel = viewModel()
) {
    var cardSelecionado by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF2B78D6))

    ) {
        EspectraHeaderBranco(modifier = Modifier.weight(0.25f))

        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Garante que caiba todos os inputs em telas menores
            horizontalAlignment = Alignment.CenterHorizontally

        )
        {
            Row( modifier = Modifier
                .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            )
                 {
                EspectraTextField(
                    value ="",
                    //viewModel.email,
                    onValueChange = {
                        //viewModel.email = it
                    }, placeholder = "Procure por pacientes",
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f))

                     Spacer(modifier = Modifier.width(3.dp))

                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Logo Espectra",
                    modifier = Modifier
                        .width(30.dp)
                        .height(32.dp)
                )

            }
            Spacer(modifier = Modifier.height(40.dp))

            EspectraCardPaciente(
                selecionado = cardSelecionado,
                onClick = {
                    cardSelecionado = true
                }
            )

            Spacer(modifier = Modifier.height(240.dp))

            EspectraButton(text = "Adicionar Paciente", onClick = { })


        }


    }
}


