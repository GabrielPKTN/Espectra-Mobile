package com.example.espectra.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.R
import com.example.espectra.storage.GerenciarSessao
import com.example.espectra.ui.components.TelaAtividade.CardAtividade
import com.example.espectra.viewmodel.TelaAtividadesViewModel
import com.google.gson.Gson
import kotlin.coroutines.EmptyCoroutineContext.get

@Composable
fun TelaAtividadesEmAndamento(
    viewModel: TelaAtividadesViewModel = viewModel(),
    gerenciarSessao: GerenciarSessao,
    idPaciente: Int,
    idHabiblidade: Int,
    onHistoricoClicado: (Int) -> Unit
) {

    val instrumentSans = FontFamily(Font(R.font.instrumentsans_variablefont_wdth_wght))
    val inclusiveSans = FontFamily(Font(R.font.inclusivesans_variablefont_wght))

    val nomeHabilidade by remember {mutableStateOf("Socialização")}

    val atividadesConcluidas = viewModel.atividadesConcluidas

    val atividadesAndamento = viewModel.atividadesEmAndamento

    val token = gerenciarSessao.buscarToken()

    Log.i("FLUXO", "TelaAtividades iniciou")
    Log.i("FLUXO", "Paciente: $idPaciente")
    Log.i("FLUXO", "Habilidade: $idHabiblidade")
    Log.i("FLUXO", "Token: $token")

    Log.d(
        "ATIVIDADES",
        "Concluídas: ${Gson().toJson(atividadesConcluidas)}"
    )

    Log.d(
        "ATIVIDADES",
        "Em andamento: ${Gson().toJson(atividadesAndamento)}"
    )

    LaunchedEffect(token, idPaciente, idHabiblidade) {
        viewModel.buscarAtividades(token!!, idPaciente, idHabiblidade)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF)),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Adicionar click para voltar a tela anterior
                IconButton(
                    onClick = {}
                ) {
                    Image(
                        painter = painterResource(R.drawable.arrow_back),
                        contentDescription = "voltar"
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = nomeHabilidade,
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = instrumentSans
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp),
            //verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Atividades em Andamento:",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = instrumentSans
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (atividadesAndamento.isEmpty()){
                    item {
                        Text(
                            text = "Nenhuma atividade em andamento",
                            fontSize = 20.sp,
                            color = Color.Red
                        )
                    }
                }else{
                    items(
                        items = atividadesAndamento,
                        key = { it.id_atividade }
                    ) { atividade ->

                        CardAtividade(
                            idAtividade = atividade.id_atividade,
                            descricao = atividade.comportamento,
                            onHistoricoClick = {idClicado -> onHistoricoClicado(idClicado) }
                        )
                    }
                }

            }


            Text(
                text = "Atividades Concluídas:",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = instrumentSans
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (atividadesConcluidas.isEmpty()){
                    item {
                        Text(
                            text = "Não existem atividades Concluídas",
                            fontSize = 20.sp,
                            color = Color.Red
                        )
                    }
                }else {
                    items(
                        items = atividadesConcluidas,
                        key = { it.id_atividade }
                    ) { atividade ->

                        CardAtividade(
                            idAtividade = atividade.id_atividade,
                            descricao = atividade.comportamento,
                            onHistoricoClick = {idClicado -> onHistoricoClicado(idClicado)}
                        )
                    }
                }
            }
        }

    }

}