package com.example.espectra

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.espectra.ui.screens.TelaAdicionarFamiliar
import com.example.espectra.ui.screens.TelaCadastro
import com.example.espectra.ui.screens.TelaHome
import com.example.espectra.ui.screens.TelaLogin
import com.example.espectra.ui.screens.TelaRedefinirSenha
import com.example.espectra.ui.theme.EspectraTheme
import com.example.espectra.viewmodel.TelaAdicionarFamiliarViewModel

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> innerPadding
                   // TelaLogin()
                    //TelaCadastro()
                  // TelaRedefinirSenha()
                    //TelaHome()
                        val navController = rememberNavController()

                        val meuViewModel: TelaAdicionarFamiliarViewModel = viewModel(
                            factory = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
                        )

                        TelaAdicionarFamiliar(viewModel = meuViewModel, navController = navController)
                }
            }
        }
    }
}

