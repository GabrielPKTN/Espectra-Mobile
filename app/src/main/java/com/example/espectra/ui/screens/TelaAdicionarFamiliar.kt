package com.example.espectra.ui.screens

import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.espectra.R
import com.example.espectra.ui.components.TelaAdicionarFamiliar.EspectraButtonAdicionarFamiliarBlue
import com.example.espectra.ui.components.TelaAdicionarFamiliar.EspectraButtonAdicionarFamiliarWhite
import com.example.espectra.ui.components.TelaAdicionarFamiliar.EspectraCard
import com.example.espectra.viewmodel.TelaAdicionarFamiliarViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TelaAdicionarFamiliar(modifier: Modifier = Modifier, viewModel: TelaAdicionarFamiliarViewModel
    //navController: NavHostController
) {

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
            uri: Uri? -> viewModel.onFotoChange(uri)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(top = 16.dp),
            contentAlignment = Alignment.Center
        ) {

            if (viewModel.fotoUri != null){
                AsyncImage(
                    model = viewModel.fotoUri,
                    contentDescription = "Foto de perfil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(115.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFF3277CF), CircleShape)
                        .clickable(enabled = !viewModel.estaCarregando){
                            launcher.launch("image/*")
                        }
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.outline_person_add_24),
                    contentDescription = "Adicionar foto",
                    colorFilter = ColorFilter.tint(Color(0xFF3277CF)),
                    modifier = Modifier
                        .size(115.dp)
                        .padding(16.dp)
                        .clickable(enabled = !viewModel.estaCarregando){
                            launcher.launch("image/*")
                        }
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        EspectraCard()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 22.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            EspectraButtonAdicionarFamiliarWhite(
                text = "Cancelar",
                modifier = Modifier
                    .weight(1f),
                onClick = {}
            )

            EspectraButtonAdicionarFamiliarBlue(
                text = if (viewModel.estaCarregando) "Salvando..." else "Salvar",
                modifier = Modifier.weight(1f),
                enabled = !viewModel.estaCarregando,
                onClick = {
                    //Guardar o token dentro de Storage
                    val tokenMock = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySUQiOjEsImlhdCI6MTc3OTI4MjM1NywiZXhwIjoxMDAwMDE3NzkyODIzNTd9.Gg83eaBKGXg2Xa9tNm5rjAxXn9_8mJxj4w2GBG756yk"
                    viewModel.salvarFamiliar(tokenAutenticacao = tokenMock)
                }
            )
        }
    }
}