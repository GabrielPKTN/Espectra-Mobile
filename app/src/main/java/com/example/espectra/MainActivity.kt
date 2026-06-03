package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.ui.screens.TelaAdicionarFamiliar
import com.example.espectra.ui.screens.TelaCadastro
import com.example.espectra.ui.screens.TelaHome
import com.example.espectra.ui.screens.TelaLogin
import com.example.espectra.ui.screens.TelaRedefinirSenha
import com.example.espectra.ui.theme.EspectraTheme
import com.example.espectra.viewmodel.TelaAdicionarFamiliarViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> innerPadding
                   // TelaLogin()
                    //TelaCadastro()
                  // TelaRedefinirSenha()
                    TelaHome()
                    val meuViewModel: TelaAdicionarFamiliarViewModel = viewModel(
                        factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                    )

                    // 2. Passamos a instância correta para o seu Composable da tela
                    TelaAdicionarFamiliar(
                        modifier = Modifier,
                        viewModel = meuViewModel
                    )
                }
            }
        }
    }
}

