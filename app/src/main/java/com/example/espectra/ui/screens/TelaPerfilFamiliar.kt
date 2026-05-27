package com.example.espectra.ui.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.ui.components.perfilPaciente.ButtonHabilidade
import com.example.espectra.ui.components.perfilPaciente.HeaderPerfil

@Composable
fun TelaPerfilFamiliar(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(40.dp),


    ) {

        HeaderPerfil(
            // faltando implementar a foto
        )

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
                fontFamily = FontFamily(
                    Font(R.font.instrumentsans_variablefont_wdth_wght)
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2B78D6),
            )

            Text(
               text = "Detalhes do paciente",
                fontFamily = FontFamily(
                    Font(R.font.inclusivesans_variablefont_wght)
                ),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .shadow(elevation = 10.dp, shape = CircleShape),
                colors = ButtonDefaults.buttonColors(Color.White),
                shape = RoundedCornerShape(2.dp)
            ) {
                Text(
                    text = "Editar informações pessoais",
                    color = Color(0xFF2B78D6),
                    fontFamily = FontFamily(
                        Font(R.font.instrumentsans_variablefont_wdth_wght)
                    ),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                )
            }

        }

        // GRÁFICO

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Grafico de Desempenho",
                fontSize = 24.sp,
                fontFamily = FontFamily(
                    Font(R.font.instrumentsans_variablefont_wdth_wght)
                ),
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF2B78D6),
            )

            Text(
                text = "Essa criança ainda não tem um gráfico de desempenho, ou não está visível para os responsáveis",
                fontFamily = FontFamily(
                    Font(R.font.inclusivesans_variablefont_wght)
                ),
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

        }


        // BOTÕES DE HABILIDADE

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ButtonHabilidade(cor = 0xFFA2E289, nomeHabilidade = "Socialização")
            ButtonHabilidade(cor = 0xFFA2E289, nomeHabilidade = "Linguagem")
            ButtonHabilidade(cor = 0xFFA2E289, nomeHabilidade = "Cognição")
            ButtonHabilidade(cor = 0xFFA2E289, nomeHabilidade = "Auto-Cuidados")
            ButtonHabilidade(cor = 0xFFA2E289, nomeHabilidade = "Desenvolvimento motor")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .padding(horizontal = 40.dp)
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFEA1212)),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text(
                    text = "Excluir familiar",
                    color = Color.White,
                    fontFamily = FontFamily(
                        Font(R.font.instrumentsans_variablefont_wdth_wght)
                    ),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 6.dp)

                )
            }
        }

    }
}