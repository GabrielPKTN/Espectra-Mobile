package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
    var idPacienteSelecionado by remember { mutableStateOf<Int?>(null) }

    // Raciocínio: Disparar a requisição à API apenas uma vez quando a tela inicializar
    LaunchedEffect(Unit) {
        homeViewModel.carregarPacientesDoUsuario(gerenciarSessao)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B78D6))
    ) {
        // Cabeçalho Branco (25% da tela)
        EspectraHeaderBranco(
            modifier = Modifier.weight(0.25f)
        )

        // Corpo da Tela (75% da tela)
        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Garante rolagem para telas muito pequenas
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Barra de busca e Filtro
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

                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filtro",
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // --- SEÇÃO DINÂMICA DE CARREGAMENTO E DADOS ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Faz a área central ocupar o espaço flexível
                contentAlignment = Alignment.Center
            ) {
                if (homeViewModel.carregando) {
                    CircularProgressIndicator(color = Color(0xFF2B78D6))
                } else if (homeViewModel.mensagemErro != null) {
                    Text(
                        text = homeViewModel.mensagemErro ?: "",
                        color = Color.Red,
                        fontSize = 14.sp
                    )
                } else if (homeViewModel.listaPacientes.isEmpty()) {
                    Text(
                        text = "Nenhum paciente cadastrado para este usuário.",
                        color = Color.Gray
                    )
                } else {
                    // Raciocínio: Exibir a lista de forma inteligente e performática
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(homeViewModel.listaPacientes) { paciente ->
                            EspectraCardPaciente(
                                paciente = paciente,
                                selecionado = idPacienteSelecionado == paciente.id,
                                onClick = {
                                    idPacienteSelecionado = paciente.id
                                }
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão Adicionar Paciente
            EspectraButton(
                text = "Adicionar Paciente",
                onClick = { /* Lógica futura de clique */ }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Botão Sair da Conta
            EspectraButton(
                text = "Sair da Conta",
                onClick = { onLogout() }
            )
        }
    }
}