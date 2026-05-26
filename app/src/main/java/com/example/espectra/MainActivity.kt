package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.espectra.ui.screens.TelaCadastro
import com.example.espectra.ui.screens.TelaHome
import com.example.espectra.ui.screens.TelaLogin
import com.example.espectra.ui.screens.TelaPerfilFamiliar
import com.example.espectra.ui.screens.TelaRedefinirSenha
import com.example.espectra.ui.theme.EspectraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> innerPadding
                   // TelaLogin()
                    // TelaCadastro()
                  // TelaRedefinirSenha()
                   // TelaHome()
                    TelaPerfilFamiliar(innerPadding)

                }
            }
        }
    }
}

