package com.example.espectra.ui.screens.HistoricoTentativa

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.model.tentativa.Tentativa
import com.example.espectra.ui.components.TelaHistoricoTentativa.CardTentativa
import com.example.espectra.ui.components.TelaHistoricoTentativa.GraficoTentativa
import com.example.espectra.ui.viewmodel.AtividadeViewModel
import com.example.espectra.ui.viewmodel.TentativaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TelaHistoricoTentativa(

    padding: PaddingValues,
    idAtividade: Int,
    idUsuario: Int,
    token: String,
    viewModelTentativa: TentativaViewModel = viewModel(),
    viewModelAtividade: AtividadeViewModel = viewModel()
) {

    val fontInclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))
    val fontInstrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))

    val scrollState = rememberScrollState()

    LaunchedEffect(idAtividade, idUsuario, token) {
        viewModelAtividade.buscaAtividadeId(idAtividade, idUsuario, token)
        viewModelTentativa.buscarDadosTentativas(idAtividade, token)
    }

    val tentativas = viewModelTentativa.tentativas
    val atividade = viewModelAtividade.atividade

    val datas = viewModelTentativa.datas.reversed()
    val independente = viewModelTentativa.listIndependente
    val parcial = viewModelTentativa.listParcial
    val total = viewModelTentativa.listTotal
    val erro = viewModelTentativa.listErro

    val isLoading = tentativas.isEmpty() || atividade == null

    var tentativaSelecionada by remember { mutableStateOf<Tentativa?>(null) }

    Column(

        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)

    ) {

        // Header (Componentizar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(padding)
                .padding(top = 16.dp),
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
        // Fim header

        //Ínicio do conteúdo da tela

        if(isLoading) {

            Column (
                modifier = Modifier
                    .height(500.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = colorResource(R.color.primary_color)
                )
            }

        } else {

            if(atividade.numero_questao != null) {

                Text(
                    text = "${atividade.id_atividade}.${atividade.comportamento}",
                    fontFamily = fontInclusiveSans,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 24.dp),
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

            } else {

                Text(
                    text = "${atividade?.comportamento}",
                    fontFamily = fontInclusiveSans,
                    fontSize = 24.sp,
                    modifier = Modifier
                        .padding(vertical = 24.dp),
                    fontWeight = FontWeight.Medium,
                    color = Color.Black
                )

            }

            Column() {

                Text(
                    text = "Tentativas:",
                    fontFamily = fontInstrumentSans,
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(vertical = 24.dp)
                )

                LazyColumn(

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    contentPadding = PaddingValues(top = 32.dp, bottom = 32.dp),

                    ) {

                    items(tentativas) { tentativa ->

                        CardTentativa(
                            auxilio = tentativa.auxilio,
                            resultado = tentativa.resultado,
                            data = tentativa.data_tentativa,
                            onCliqueDetalhes = { tentativaSelecionada = tentativa }
                        )

                    }


                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Representação gráfica",
                    color = Color.Black,
                    fontFamily = fontInstrumentSans,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(25.dp))

                GraficoTentativa(

                    datas = datas,
                    listIndependente = independente,
                    listParcial = parcial,
                    listTotal = total,
                    listErro = erro

                )

                Spacer(modifier = Modifier.height(100.dp))

            }

        }

    }

    tentativaSelecionada?.let { tentativa ->

        ModalDetalhesTentativa(
            tentativa = tentativa,
            onDismissRequest = { tentativaSelecionada = null }
        )
    }

}