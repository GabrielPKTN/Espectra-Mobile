package com.example.espectra

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.util.Logger
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.screens.TelaAdicionarFamiliar
import com.example.espectra.ui.screens.TelaCadastro
import com.example.espectra.ui.screens.TelaHome
import com.example.espectra.ui.screens.TelaLogin
import com.example.espectra.ui.screens.TelaPerfilFamiliar
import com.example.espectra.ui.theme.EspectraTheme
import com.example.espectra.viewmodel.PerfilViewModel
import com.example.espectra.viewmodel.TelaAdicionarFamiliarViewModel
import com.example.espectra.viewmodel.TelaCadastroViewModel
import com.example.espectra.viewmodel.TelaHomeViewModel
import com.example.espectra.viewmodel.TelaLoginViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel: TelaLoginViewModel by viewModels()
    private val cadastroViewModel: TelaCadastroViewModel by viewModels()
    private val homeViewModel: TelaHomeViewModel by viewModels()
    private val TelaAdicionarFamiliarViewModel: TelaAdicionarFamiliarViewModel by viewModels()
    private val TelaPerfilFamiliarViewModel: PerfilViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gerenciarSessao = GerenciarSessao(applicationContext)

        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // Sistema de navegação baseado no estado da develop
                    var telaAtual by remember { mutableStateOf("login") }
                    val navController = rememberNavController()

                    var idPacienteSelecionado by remember {mutableStateOf<Int?>(null)}

                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (telaAtual) {
                            "login" -> {
                                TelaLogin(
                                    viewModel = loginViewModel,
                                    gerenciarSessao = gerenciarSessao,
                                    onNavegarParaHome = { telaAtual = "home" },
                                    onNavegarParaCadastro = { telaAtual = "cadastro" }
                                )
                            }

                            "cadastro" -> {
                                TelaCadastro(
                                    viewModel = cadastroViewModel,
                                    onNavegarParaLogin = { telaAtual = "login" }
                                )
                            }

                            "home" -> {
                                TelaHome(
                                    gerenciarSessao = gerenciarSessao,
                                    onLogout = {
                                        gerenciarSessao.limparSessao()
                                        telaAtual = "login"
                                    },
                                    onTelaAdicionarFamiliar = {
                                        telaAtual = "adicionar_familiar"
                                    },
                                    viewModel = homeViewModel,
                                    navController = navController,
                                    onPacienteClicado = { id ->
                                        idPacienteSelecionado = id
                                        telaAtual = "perfil_familiar"
                                    }
                                )
                            }

                            "adicionar_familiar" -> {
                                TelaAdicionarFamiliar(
                                    onNavegarHome = {
                                        telaAtual = "home"
                                    },
                                    viewModel = TelaAdicionarFamiliarViewModel
                                )
                            }

                            "perfil_familiar" -> {

                                idPacienteSelecionado?.let { id ->

                                    TelaPerfilFamiliar(
                                        navController = navController,
                                        viewModel = TelaPerfilFamiliarViewModel,
                                        gerenciarSessao = gerenciarSessao,
                                        idPaciente = id
                                    )

                                }
                            }
                        }
                    }
                }
            }
        }
    }
}