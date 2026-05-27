package com.example.espectra.ui.components.perfilPaciente
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.espectra.R

@Composable
fun HeaderPerfil(
    //fotoPerfil: String?
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {

        // Header azul
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF2B78D6)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 8.dp),
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "Voltar",
                        colorFilter = ColorFilter.tint(Color.White)
                    )
                }
            }
        }

//            fotoPerfil?.let { url ->
//
//            AsyncImage(
//                model = url,
//                contentDescription = "Foto de perfil",
//
//                modifier = Modifier
//                    .size(100.dp)
//                    .clip(CircleShape)
//                    .align(Alignment.BottomCenter)
//
//            )
//        }
    }
}








