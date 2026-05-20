package com.example.espectra.ui.screens

import com.example.espectra.ui.components.EspectraButton
import com.example.espectra.ui.components.EspectraTextField



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.ui.components.EspectraHeaderAzul

//import androidx.lifecycle.viewmodel.compose.viewModel
//import androidx.navigation.NavHostController
//import com.exemplo.espectra.ui.components.*
//import com.example.espectra.ui.navigation.Screen

/*
class CadastroViewModel : ViewModel() {
    var isPsicopedagogo by mutableStateOf(false)
    var isFamilia by mutableStateOf(false)
    var nome by mutableStateOf("")
    var email by mutableStateOf("")
    var dataNascimento by mutableStateOf("")
    var telefone by mutableStateOf("")
    var senha by mutableStateOf("")
    var confirmarSenha by mutableStateOf("")
}
*/

@Composable
fun TelaCadastro(
    //navController: NavHostController, viewModel: CadastroViewModel = viewModel()
    ) {
    Column(modifier = Modifier.fillMaxSize().background(Color(0xFF2B78D6))) {
        EspectraHeaderAzul(modifier = Modifier.weight(0.25f))

        Column(
            modifier = Modifier
                .weight(0.75f)
                .fillMaxWidth()
                .background(Color(0xFFF9F9F9), shape = RoundedCornerShape(topStart = 32.dp))
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Garante que caiba todos os inputs em telas menores
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Cadastro", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF004481))
            Spacer(modifier = Modifier.height(16.dp))

            // Checkboxes de Tipo de Conta
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = false,
                        //viewModel.isPsicopedagogo,
                        onCheckedChange = {
                           // viewModel.isPsicopedagogo = it
                        })
                    Text("Psicopedagogo", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(checked = true,
                       // viewModel.isFamilia,
                        onCheckedChange = {
                            //viewModel.isFamilia = it
                            })
                    Text("Família", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            EspectraTextField(value = "",
               // viewModel.nome,
                onValueChange = {
                 //   viewModel.nome = it
                                }, placeholder = "Nome")
            Spacer(modifier = Modifier.height(12.dp))
            EspectraTextField(value = "",
               // viewModel.email,
                onValueChange = {
                  //  viewModel.email = it
                                }, placeholder = "E-mail")
            Spacer(modifier = Modifier.height(12.dp))
            EspectraTextField(value = "",
               // viewModel.dataNascimento,
                onValueChange = {
                   // viewModel.dataNascimento = it
                                }, placeholder = "Data de nascimento")
            Spacer(modifier = Modifier.height(12.dp))
            EspectraTextField(value = "",
              //  viewModel.telefone,
                onValueChange = {
               // viewModel.telefone = it
                                                                          }, placeholder = "Telefone")
            Spacer(modifier = Modifier.height(12.dp))
            EspectraTextField(value = "",
                //viewModel.senha,
                onValueChange = {
               // viewModel.senha = it
                                                                       }, placeholder = "Senha")
            Spacer(modifier = Modifier.height(12.dp))


            // Corrigido o placeholder da imagem de "redefinir a senha" para a lógica real de confirmação
            EspectraTextField(value = "",
                //viewModel.confirmarSenha,
                onValueChange = {
                    //viewModel.confirmarSenha = it
                    }, placeholder = "Confirme sua senha")

            Spacer(modifier = Modifier.height(24.dp))
            EspectraButton(text = "Cadastrar", onClick = { /* Enviar dados */ })
            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.clickable {
                //navController.navigate(Screen.Login.route)
            }) {
                Text(text = "Já possui uma conta? ", color = Color.Gray, fontSize = 14.sp)
                Text(text = "Faça login", color = Color(0xFF2B78D6), fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}