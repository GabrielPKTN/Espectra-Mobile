package com.example.espectra.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.ui.components.EspectraButton
import com.example.espectra.ui.components.EspectraHeaderAzul
import com.example.espectra.ui.components.EspectraTextField
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.viewmodel.TelaLoginViewModel

@Composable
fun TelaLogin(
    viewModel: TelaLoginViewModel,
    gerenciarSessao: GerenciarSessao,
    onNavegarParaHome: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B78D6))
    ) {
        EspectraHeaderAzul(modifier = Modifier.weight(0.3f))

        // Card Branco com cantos arredondados superiores
        Column(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(topStart = 32.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Login", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004481))
            Spacer(modifier = Modifier.height(24.dp))


            EspectraTextField(
                value = viewModel.email,
                onValueChange = { viewModel.novoEmail(it) },
                placeholder = "E-mail"
            )
            // Mostra o erro de e-mail se houver
            if (viewModel.emailErro != null) {
                Text(text = viewModel.emailErro ?: "", color = Color.Red, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))


            EspectraTextField(
                value = viewModel.senha,
                onValueChange = { viewModel.novaSenha(it) },
                placeholder = "Senha",
                visualTransformation = PasswordVisualTransformation()
            )

            if (viewModel.senhaErro != null) {
                Text(text = viewModel.senhaErro ?: "", color = Color.Red, fontSize = 12.sp)
            }


            Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = "Esqueceu a senha?",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        // Lógica futura para redefinir senha
                    }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            EspectraButton(
                text = if (viewModel.carregarDados) "Carregando..." else "Entrar",
                onClick = {
                    viewModel.realizarLogin(gerenciarSessao) {
                        onNavegarParaHome() // Vai para a Home quando der sucesso
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))


            Row {
                Text(text = "Não possui uma conta? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Cadastre-se",
                    color = Color(0xFF2B78D6),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {

                    }
                )
            }
        }
    }
}