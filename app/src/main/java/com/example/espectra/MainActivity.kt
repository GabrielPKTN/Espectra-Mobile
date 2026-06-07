package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.screens.TelaLogin
import com.example.espectra.ui.screens.TelaPerfilFamiliar
import com.example.espectra.ui.screens.TelaPerfilUsuario
import com.example.espectra.ui.theme.EspectraTheme
import com.example.espectra.viewmodel.TelaLoginViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel: TelaLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gerenciarSessao = GerenciarSessao(applicationContext)

        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    TelaPerfilUsuario(innerPadding)

//                    var telaAtual by remember {
//                        mutableStateOf(if (gerenciarSessao.buscarToken() != null) "home" else "login")
//                    }
//
//                    Box(modifier = Modifier.padding(innerPadding)) {
//                        when (telaAtual) {
//                            "login" -> {
//                                TelaLogin(
//                                    viewModel = loginViewModel,
//                                    gerenciarSessao = gerenciarSessao,
//                                    onNavegarParaHome = {
//                                        telaAtual = "home"
//                                    }
//                                )
//                            }
//                            "home" -> {
//                                //TelaHomePaciente(
//                                   // gerenciarSessao = gerenciarSessao,
//                                   // onLogout = {
//                                      //  gerenciarSessao.limparSessao()
//                                      //  telaAtual = "login"
//                                   // }
//                                //)
//                            }
//                        }
//                    }
                }
            }
        }
    }
}