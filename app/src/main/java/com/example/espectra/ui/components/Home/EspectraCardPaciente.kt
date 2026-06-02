package com.example.espectra.ui.components.Home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.espectra.R
import com.example.espectra.model.Paciente

@Composable
fun EspectraCardPaciente(
    paciente: Paciente,      // Recebe o objeto completo do banco de dados
    selecionado: Boolean,    // Estado de clique controlado pela tela home
    onClick: () -> Unit      // Evento de clique
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F3F7)
        ),
        // Se o card for o selecionado, ganha borda azul, senão fica cinza
        border = BorderStroke(
            1.dp,
            if (selecionado) Color(0xFF2B78D6) else Color(0xFFE0E0E0)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Nota: Usando o ícone de filtro temporariamente para a foto de perfil.
            // Garanta que o arquivo 'filter' exista em res/drawable para não dar erro.
            Image(
                painter = painterResource(id = R.drawable.filter),
                contentDescription = "Foto do Paciente",
                modifier = Modifier.size(60.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                // Nome vindo direto do MySQL (Node.js)
                Text(
                    text = paciente.nome,
                    fontSize = 20.sp,
                    color = Color(0xFF004481) // Azul escuro para o nome
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Idade vinda direto do MySQL (Node.js)
                Text(
                    text = "${paciente.idade} Anos",
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Diagnóstico vindo do MySQL (Node.js)
                // Como ele pode ser nulo (String?), usamos o operador ?: para colocar um aviso caso esteja em branco
                Text(
                    text = paciente.diagnostico ?: "Sem diagnóstico registrado",
                    fontSize = 16.sp,
                    color = if (paciente.diagnostico != null) Color(0xFF2B78D6) else Color.Gray
                )
            }
        }
    }
}