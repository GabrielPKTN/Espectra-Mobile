package com.example.espectra.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.espectra.model.usuario.Usuario
import com.example.espectra.service.RetrofitInstance
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.components.componentsGerais.EspectraButton

@Composable
fun TelaPerfilUsuario() {
    val contexto = LocalContext.current

    val sessao = remember { GerenciarSessao(contexto) }
    val idUsuario = sessao.buscarIdUsuario()
    // 🚀 Garante que o token recupere o prefixo 'Bearer ' dinamicamente se o back-end exigir
    val tokenSalvo = sessao.buscarToken()
    val token = if (tokenSalvo != null && !tokenSalvo.startsWith("Bearer ")) "Bearer $tokenSalvo" else tokenSalvo ?: ""

    var usuarioState by remember { mutableStateOf<Usuario?>(null) }
    var carregandoState by remember { mutableStateOf(true) }
    var erroState by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(idUsuario) {
        try {
            // 🚀 Executa a requisição real de produção usando os dados do usuário logado
            if (idUsuario > 0 && token.isNotBlank()) {
                val resposta = RetrofitInstance.espectraApiService.buscarPerfilUsuario(
                    token = token,
                    idUsuario = idUsuario
                )

                if (resposta.isSuccessful && resposta.body() != null) {
                    usuarioState = resposta.body()?.items
                } else {
                    erroState = "Erro da API: Código ${resposta.code()} - ${resposta.message()}"
                }
                carregandoState = false
            } else {
                erroState = "Usuário não autenticado ou sessão expirada."
                carregandoState = false
            }
        } catch (e: Exception) {
            erroState = "Erro de rede: ${e.localizedMessage}"
            carregandoState = false
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFF9F9F9))) {
        if (carregandoState) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (erroState != null) {
            Text(text = erroState!!, color = Color.Red, modifier = Modifier.align(Alignment.Center))
        } else {
            usuarioState?.let { usuario ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {

                    // 1. Header Azul Superior com o botão Voltar igual ao mockup
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(160.dp)
                            .background(Color(0xFF2B78D6))
                    ) {
                        IconButton(
                            onClick = { /* Lógica para voltar de tela */ },
                            modifier = Modifier
                                .padding(top = 40.dp, start = 16.dp)
                                .size(32.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Voltar",
                                tint = Color.White
                            )
                        }
                    }

                    // 2. Card Branco sobreposto (Container central da foto e dados)
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 28.dp)
                            .offset(y = (-55).dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        // Foto de Perfil Redonda com borda branca grossa para destacar do fundo
                        Box(
                            modifier = Modifier
                                .size(114.dp)
                                .background(Color.White, CircleShape)
                                .padding(2.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                model = usuario.foto.ifBlank { "https://via.placeholder.com/150" },
                                contentDescription = "Foto de perfil",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Nome Centralizado em Caixa Alta e Azul
                        Text(
                            text = usuario.nome.uppercase(),
                            color = Color(0xFF2B78D6),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            lineHeight = 28.sp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Card grande externo com borda fina e sombra leve
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(28.dp),
                            border = BorderStroke(1.dp, Color(0xFFE5E5E5)),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFDFDFD)),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                // Mantido fixo temporariamente até que a classe Usuario inclua este campo
                                ExibirCampoPerfil(label = "E-mail", valor = "antonio@email.com")
                                ExibirCampoPerfil(label = "Telefone", valor = usuario.telefone)
                                ExibirCampoPerfil(label = "Data de nascimento", valor = usuario.data_nascimento)
                            }
                        }

                        Spacer(modifier = Modifier.height(30.dp))

                        // Botão Editar (Fundo Branco com Letras Azuis)
                        EspectraButton(
                            text = "Editar informações pessoais",
                            onClick = { /* Lógica de Edição */ },
                            corLetra = Color(0xFF2B78D6),
                            buttonColor = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.fillMaxWidth(0.9f)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botão Excluir Conta (Fundo Azul com Letras Brancas)
                        EspectraButton(
                            text = "Excluir conta",
                            onClick = { /* Lógica de Exclusão */ },
                            corLetra = Color.White,
                            buttonColor = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = Color(0xFF2B78D6)),
                            modifier = Modifier.fillMaxWidth(0.9f)
                        )
                    }
                }
            }
        }
    }
}

// Componente de input visual com bordas finas azuladas idêntico ao mockup
@Composable
fun ExibirCampoPerfil(label: String, valor: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = Color(0xFF2B78D6),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp)
                .border(BorderStroke(1.dp, Color(0xFFBDD4F5)), RoundedCornerShape(12.dp))
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = valor,
                color = Color(0xFF555555),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}