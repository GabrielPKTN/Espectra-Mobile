package com.example.espectra.ui.screens

import android.widget.MediaController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.espectra.R

@Composable
fun TelaEditarFamiliar(
    //navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 18.dp),
            horizontalArrangement = Arrangement.Start
        ) {

            IconButton(
                onClick = {
                //navController.popBackStack()
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.arrow_back),
                    contentDescription = "Voltar",
                    colorFilter = ColorFilter.tint(Color(0xFF2B78D6))
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.default_photo),
                contentDescription = "Foto padrão",

                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)

            )


            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFD9D9D9)
                )
            ) {
                Text("Edite as informações do familiar", modifier = Modifier.padding(12.dp))
            }

        }
    }
}