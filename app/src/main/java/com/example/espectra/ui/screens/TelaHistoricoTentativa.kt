package com.example.espectra.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.espectra.R

@Composable
fun TelaHistoricoTentativa(padding: PaddingValues) {

    Column(

        modifier = Modifier
            .background(Color(253, 0, 0, 255))
            .fillMaxSize()

    ) {

        // Header (Componentizar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(horizontal = 10.dp)
                .background(Color.Green),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                painter = painterResource(R.drawable.baseline_arrow_back_24),
                contentDescription = null,
                tint = colorResource(R.color.primary_color),
                modifier = Modifier.size(32.dp)
            )

            // if(paciente tem foto?) { se sim mostra foto } else { se não mostra o ícone de usuário sem foto }

            Image(

                painter = painterResource(R.drawable.responsavel),
                contentDescription = "Foto do usuário",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = colorResource(R.color.primary_color),
                        shape = CircleShape
                    )
            )

        }

    }

}