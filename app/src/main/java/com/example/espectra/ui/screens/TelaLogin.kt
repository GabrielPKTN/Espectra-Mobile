package com.example.espectra.ui.screens

import androidx.lifecycle.ViewModel
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
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
import com.example.espectra.ui.components.componentsGerais.EspectraButton
import com.example.espectra.ui.components.TelaCadastroLogin.EspectraHeaderAzul
import com.example.espectra.ui.components.componentsGerais.EspectraTextField

//import com.example.espectra.ui.navigation.Screen

class LoginViewModel : ViewModel() {
    var email by mutableStateOf("")
    var senha by mutableStateOf("")
}

@Composable
fun TelaLogin(
    //navController: NavHostController, viewModel: LoginViewModel = viewModel()
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
                value ="",
                    //viewModel.email,
                    onValueChange = {
                        //viewModel.email = it
                                    }, placeholder = "E-mail")
            Spacer(modifier = Modifier.height(16.dp))
            EspectraTextField(
                value = "",
                    //viewModel.senha,
                onValueChange = {
                   // viewModel.senha = it
                                },
                placeholder = "Senha",
                visualTransformation = PasswordVisualTransformation()
            )

            // Botão Esqueci a senha implícito na imagem (Redefinir a senha)
            Box(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), contentAlignment = Alignment.CenterEnd) {
                Text(
                    text = "Esqueceu a senha?",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    modifier = Modifier.clickable {
                        //navController.navigate(Screen.RedefinirSenha.route )
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            EspectraButton(text = "Entrar", onClick = { /* Lógica de Login */ })

            Spacer(modifier = Modifier.weight(1f))

            // Footer direcionando para o cadastro
            Row {
                Text(text = "Não possui uma conta? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Cadastre-se",
                    color = Color(0xFF2B78D6),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable {
                        //navController.navigate(Screen.Cadastro.route)
                        }
                )
            }
        }
    }
}