package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.espectra.ui.screens.HistoricoTentativa.TelaHistoricoTentativa
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
                     TelaHistoricoTentativa(
                         innerPadding,
                         5,
                         2,
                         "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjEsImlhdCI6MTc4MDI2MjE4NSwiZXhwIjoxMDAwMDE3ODAyNjIxODV9.IFFixs2vMO3uP7f9M_I2PIRH1krHcHBrcPJzqDOewf4"
                     )

                }
            }
        }
    }
}