package com.example.espectra.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.ui.components.TelaCadastroLogin.EspectraHeaderAzul
import com.example.espectra.ui.components.componentsGerais.EspectraButton
import com.example.espectra.ui.components.componentsGerais.EspectraTextField
import com.example.espectra.viewmodel.TelaCadastroViewModel

@Composable
fun TelaCadastro(
    viewModel: TelaCadastroViewModel,
    onNavegarParaLogin: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF2B78D6))) {
        EspectraHeaderAzul(modifier = Modifier.weight(0.25f))

        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(topStart = 32.dp))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Cadastro", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004481))
            Spacer(modifier = Modifier.height(16.dp))

            viewModel.errorMessage?.let { erro ->
                Text(text = erro, color = Color.Red, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = viewModel.isPsicopedagogo,
                        onCheckedChange = { viewModel.mudarTipoConta(true) }
                    )
                    Text("Psicopedagogo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = viewModel.isFamilia,
                        onCheckedChange = { viewModel.mudarTipoConta(false) }
                    )
                    Text("Família", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            EspectraTextField(value = viewModel.nome, onValueChange = { viewModel.nome = it }, placeholder = "Nome")
            Spacer(modifier = Modifier.height(12.dp))

            EspectraTextField(value = viewModel.email, onValueChange = { viewModel.email = it }, placeholder = "E-mail")
            Spacer(modifier = Modifier.height(12.dp))

            EspectraTextField(value = viewModel.dataNascimento, onValueChange = { viewModel.dataNascimento = it }, placeholder = "Data de nascimento (DDMMYYYY)")
            Spacer(modifier = Modifier.height(12.dp))

            EspectraTextField(value = viewModel.telefone, onValueChange = { viewModel.telefone = it }, placeholder = "Telefone")
            Spacer(modifier = Modifier.height(12.dp))

            EspectraTextField(value = viewModel.senha, onValueChange = { viewModel.senha = it }, placeholder = "Senha")
            Spacer(modifier = Modifier.height(12.dp))

            EspectraTextField(value = viewModel.confirmarSenha, onValueChange = { viewModel.confirmarSenha = it }, placeholder = "Confirme sua senha")

            Spacer(modifier = Modifier.height(24.dp))

            if (viewModel.isLoading) {
                CircularProgressIndicator(color = Color(0xFF2B78D6))
            } else {
                EspectraButton(
                    text = "Cadastrar",
                    onClick = { viewModel.executarCadastro(onSucesso = onNavegarParaLogin) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.clickable { onNavegarParaLogin() }) {
                Text(text = "Já possui uma conta? ", color = Color.Gray, fontSize = 14.sp)
                Text(text = "Faça login", color = Color(0xFF2B78D6), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}