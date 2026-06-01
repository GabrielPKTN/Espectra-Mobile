package com.example.espectra.ui.screens

import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.espectra.R
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.components.Home.EspectraCardPaciente
import com.example.espectra.ui.components.Home.EspectraHeaderBranco
import com.example.espectra.ui.components.componentsGerais.EspectraButton
import com.example.espectra.ui.components.componentsGerais.EspectraTextField
import com.example.espectra.viewmodel.TelaHomeViewModel

@Composable
fun TelaHomePaciente(
    gerenciarSessao: GerenciarSessao,
    onLogout: () -> Unit = {},
    homeViewModel: TelaHomeViewModel = viewModel()
) {
    var pesquisaTexto by remember { mutableStateOf("") }
    var cardSelecionado by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        homeViewModel.carregarPacientesDoUsuario(gerenciarSessao)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B78D6))
    ) {

        EspectraHeaderBranco(
            modifier = Modifier.weight(0.25f)
        )

        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Barra de busca
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                EspectraTextField(
                    value = pesquisaTexto,
                    onValueChange = { pesquisaTexto = it },
                    placeholder = "Procure por pacientes",
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(3.dp))

                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filtro",
                    modifier = Modifier.width(30.dp).height(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // --- SEÇÃO DINÂMICA DE CARREGAMENTO E DADOS ---

            if (homeViewModel.carregando) {

                CircularProgressIndicator(color = Color(0xFF2B78D6))
            } else if (homeViewModel.mensagemErro != null) {

                Text(text = homeViewModel.mensagemErro ?: "", color = Color.Red, fontSize = 14.sp)
            } else if (homeViewModel.listaPacientes.isEmpty()) {

                Text(text = "Nenhum paciente cadastrado para este usuário.", color = Color.Gray)
            } else {

                homeViewModel.listaPacientes.forEach { paciente ->
                    EspectraCardPaciente(
                        // Você pode passar propriedades do 'paciente' para customizar seu componente interno depois, ex: nome = paciente.nome
                        selecionado = cardSelecionado,
                        onClick = {
                            cardSelecionado = true
                        }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }



            Spacer(modifier = Modifier.height(40.dp))

            EspectraButton(
                text = "Adicionar Paciente",
                onClick = {}
            )

            Spacer(modifier = Modifier.height(16.dp))


            EspectraButton(
                text = "Sair da Conta",
                onClick = { onLogout() }
            )
        }
    }
}