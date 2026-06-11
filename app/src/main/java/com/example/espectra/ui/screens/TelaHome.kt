package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.espectra.R
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.components.Home.EspectraCardPaciente
import com.example.espectra.ui.components.componentsGerais.EspectraButton
import com.example.espectra.ui.components.componentsGerais.EspectraTextField
import com.example.espectra.viewmodel.TelaHomeViewModel

@Composable
fun TelaHome(
    gerenciarSessao: GerenciarSessao,
    onLogout: () -> Unit,
    onTelaAdicionarFamiliar: () -> Unit,
    onPacienteClicado: (Int) -> Unit,
    viewModel: TelaHomeViewModel = viewModel(),
    navController: NavController
) {
    var cardIdSelecionado by remember { mutableStateOf<Int?>(null) }
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                // Chama a sua requisição da API aqui
                viewModel.carregarDadosDoPaciente(gerenciarSessao = gerenciarSessao)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Container Principal: Todo branco conforme o protótipo final
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        // --- HEADER GIGANTE (238dp de altura) ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(238.dp)
                .background(Color.White)
                .padding(horizontal = 24.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo Espectra -> Agora limpa a sessão e volta para a tela de Login
                Image(
                    painter = painterResource(id = R.drawable.logotipo_azul),
                    contentDescription = "Logo Espectra - Voltar",
                    modifier = Modifier
                        .size(width = 70.dp, height = 35.dp)
                        .clickable {
                            gerenciarSessao.limparSessao()
                            onLogout()
                        }
                )


                Image(
                    painter = painterResource(id = R.drawable.foto_paciente),
                    contentDescription = "Foto de perfil do paciente",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(CircleShape) // Mantém o corte redondo, mas sem o .clickable
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Linha inferior do Header: Barra de Pesquisa e Filtro
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                EspectraTextField(
                    value = viewModel.textoPesquisa,
                    onValueChange = { viewModel.atualizarPesquisa(it) },
                    placeholder = "Procure por pacientes",
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Image(
                    painter = painterResource(id = R.drawable.filter),
                    contentDescription = "Filtro",
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        // --- CONTEÚDO DA TELA ---
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 20.dp)
            ) {
                if (viewModel.isLoading) {
                    CircularProgressIndicator(
                        color = Color(0xFF2B78D6),
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else if (viewModel.erroMensagem != null) {
                    Text(
                        text = viewModel.erroMensagem!!,
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else if (viewModel.listaPacientes.isEmpty()) {
                    Text(
                        text = "Selecione um paciente,\nou adicione um.",
                        color = Color(0xFF2B78D6),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    val pacientesFiltrados = viewModel.listaPacientes.filter {
                        it.nome.contains(viewModel.textoPesquisa, ignoreCase = true)
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(bottom = 16.dp)
                    ) {
                        items(pacientesFiltrados) { paciente ->
                            val estaSelecionado = cardIdSelecionado == paciente.id
                            val formatoCard = RoundedCornerShape(16.dp)


                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(formatoCard)
                                    .background(Color.White)
                                    .border(
                                        width = 2.dp,
                                        color = if (estaSelecionado) Color(0xFF2B78D6) else Color.Transparent,
                                        shape = formatoCard
                                    )
                                    .clickable { cardIdSelecionado = paciente.id }
                            ) {
                                EspectraCardPaciente(
                                    paciente = paciente,
                                    selecionado = false,
                                    onClick = {
                                        onPacienteClicado(paciente.id)
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // --- BASE FIXA: Botão Adicionar Paciente ---
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp, top = 8.dp)
            ) {
                EspectraButton(
                    text = "Adicionar paciente",
                    onClick = {
                        onTelaAdicionarFamiliar()
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}