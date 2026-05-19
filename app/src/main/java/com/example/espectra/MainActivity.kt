package com.example.espectra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.espectra.components.EspectraHeader
import com.example.espectra.pages.TelaCadastro
import com.example.espectra.pages.TelaLogin
import com.example.espectra.pages.TelaRedefinir
import com.example.espectra.ui.theme.EspectraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EspectraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding -> innerPadding
                    //TelaLogin()
                    //TelaCadastro()
                    TelaRedefinir()

                }
            }
        }
    }
}

