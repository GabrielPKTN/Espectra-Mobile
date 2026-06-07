package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.espectra.ui.screens.TelaCadastro
import com.example.espectra.ui.screens.TelaEditarFamiliar
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
//                    TelaPerfilFamiliar(
//                       token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjEsImlhdCI6MTc4MDMyMzA3OSwiZXhwIjoxMDAwMDE3ODAzMjMwNzl9.Ybxh8Ri_b6CXNcTwdQOHuRJJzLd76MDohbmilx9E5io",
//                        idPaciente = 6)

                   TelaEditarFamiliar(token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjEsImlhdCI6MTc4MDMyMzA3OSwiZXhwIjoxMDAwMDE3ODAzMjMwNzl9.Ybxh8Ri_b6CXNcTwdQOHuRJJzLd76MDohbmilx9E5io")

                }
            }
        }
    }
}

