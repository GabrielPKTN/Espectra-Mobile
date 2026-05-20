package com.example.espectra.ui.screens


import androidx.lifecycle.ViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
import com.example.espectra.ui.components.EspectraButton
import com.example.espectra.ui.components.EspectraHeaderAzul
import com.example.espectra.ui.components.EspectraTextField

class RedefinirViewModel : ViewModel() {
    var email by mutableStateOf("")
}

@Composable
fun TelaRedefinirSenha(
    //navController: NavHostController, viewModel: RedefinirViewModel = viewModel()
    ) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF2B78D6))) {
        EspectraHeaderAzul(modifier = Modifier.weight(0.3f))

        Column(
            modifier = Modifier
                .weight(0.7f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(topStart = 32.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Redefinir a senha", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004481))
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Digite o seu e-mail cadastrado:",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier.align(Alignment.Start).padding(bottom = 8.dp)
            )
            EspectraTextField(value = "",
                //viewModel.email,
                onValueChange = {
                   // viewModel.email = it
                                }, placeholder = "E-mail")

            Spacer(modifier = Modifier.height(32.dp))
            EspectraButton(text = "Enviar ", onClick = { /* Lógica de disparo */ })
        }
    }
}