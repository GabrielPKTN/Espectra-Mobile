package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
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
fun TelaHome(

    gerenciarSessao: GerenciarSessao,
    onLogout: () -> Unit,
    viewModel: TelaHomeViewModel = viewModel()
) {
    var cardIdSelecionado by remember { mutableStateOf<Int?>(null) }


    LaunchedEffect(Unit) {
        viewModel.carregarDadosDoPaciente(gerenciarSessao)
    }

    DisposableEffect(Unit) {
        onDispose { gerenciarSessao.limparSessao()
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B78D6))
    ) {

        EspectraHeaderBranco(
            modifier = Modifier
                .weight(0.25f)
                .clickable { onLogout() } // Exemplo: desloga se clicar no header ou ícone correspondente
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                EspectraTextField(
                    value = viewModel.textoPesquisa,
                    onValueChange = { viewModel.atualizarPesquisa(it) },
                    placeholder = "Procure por pacientes",
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filtro",
                    modifier = Modifier
                        .width(30.dp)
                        .height(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Gerenciamento de Estados de Rede na UI
            if (viewModel.isLoading) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = Color(0xFF2B78D6))
                }
            } else if (viewModel.erroMensagem != null) {
                Text(text = viewModel.erroMensagem!!, color = Color.Red, fontSize = 14.sp)
            } else if (viewModel.listaPacientes.isEmpty()) {
                Text(text = "Nenhum paciente cadastrado para este usuário.", color = Color.Gray, fontSize = 14.sp)
            } else {
                // Renderiza dinamicamente os cartões com os dados de cada paciente do id específico
                viewModel.listaPacientes.filter {
                    it.nome.contains(viewModel.textoPesquisa, ignoreCase = true)
                }.forEach { paciente ->
                    EspectraCardPaciente(
                        // Adapte seu componente customizado para receber o Objeto 'paciente' real se possível
                        selecionado = cardIdSelecionado == paciente.id,
                        onClick = { cardIdSelecionado = paciente.id }
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            EspectraButton(text = "Adicionar Paciente", onClick = { /* Rota para add */ })
        }
    }
}