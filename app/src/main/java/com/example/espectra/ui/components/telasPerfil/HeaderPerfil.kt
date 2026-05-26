package com.example.espectra.ui.components.telasPerfil
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.espectra.R

@Composable
fun HeaderPerfil(
    fotoPerfil: String?
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF2B78D6))
        ) {


            IconButton(
                onClick = { }
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Voltar",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .clickable{
                            // navegar para a tela anterior
                        }
                )
            }
        }


        fotoPerfil?.let { url ->

            AsyncImage(
                model = url,
                contentDescription = "Foto de perfil",

                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-40).dp)
            )
        }
    }
}


