package com.example.espectra.ui.components.TelaAdicionarFamiliar

import android.app.Application
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.espectra.R
import com.example.espectra.model.familiar.grauSuporte
import com.example.espectra.model.familiar.seriesEscolares
import com.example.espectra.model.familiar.transtornos
import com.example.espectra.utils.CpfTransformation
import com.example.espectra.utils.MascaraDataTransformation
import com.example.espectra.viewmodel.TelaAdicionarFamiliarViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EspectraCard(modifier: Modifier = Modifier) {
    //Import da viewModel de Adicionar Familiar
    val contexto = LocalContext.current
    val viewModel: TelaAdicionarFamiliarViewModel = viewModel(
        factory = androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.getInstance(
            contexto.applicationContext as Application
        )
    )

    var expandidoSerie by remember{
        mutableStateOf(false)
    }

    var expandidoDiagnostico by remember {
        mutableStateOf(false)
    }

    var expandidoGrauSuporte by remember {
        mutableStateOf(false)
    }


    //Estrutura do Card
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(470.dp)
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(44.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        ),
        border = BorderStroke(
            width = 1.dp,
            Color(0xFFD9D9D9)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Adicione um novo familiar",
                fontSize = 20.sp,
                color = Color(0xFF3277CF),
                fontWeight = FontWeight.Bold,
                style = TextStyle(
                    fontFamily = FontFamily(
                        Font(R.font.instrumentsans_variablefont_wdth_wght)
                    )
                )
            )
        }

        //OutlinedTextField -> Nome
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = viewModel.nome,
                onValueChange = {
                    viewModel.onNomeChange((it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                label = {
                    Text("Nome completo")
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF3277CF),
                    unfocusedBorderColor = Color(0xFF3277CF)
                ),
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = Color.Black,
                    fontFamily = FontFamily(
                        Font(R.font.instrumentsans_variablefont_wdth_wght)
                    ),
                    fontWeight = FontWeight.SemiBold
                ),
                singleLine = true,
                isError = viewModel.erroNome != null,
                supportingText = {
                    if (viewModel.erroNome != null) Text(text = viewModel.erroNome!!)
                }
            )

            //Row que armazena OutlinedTextField de Data de nascimento e de Série Escolar
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(150.dp),
                    value = viewModel.dataNascimento,
                    onValueChange = {
                        viewModel.onDataNascimentoChange((it))
                    },
                    visualTransformation = MascaraDataTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    label = {
                        Text("Nascimento")
                    },
                    placeholder = {
                        Text("DD/MM/AAAA")
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF3277CF),
                        unfocusedBorderColor = Color(0xFF3277CF)
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.instrumentsans_variablefont_wdth_wght)
                        ),
                        fontWeight = FontWeight.SemiBold
                    ),
                    singleLine = true,
                    isError = viewModel.erroDataNascimento != null,
                    supportingText = {
                        if (viewModel.erroDataNascimento != null) Text(text = viewModel.erroDataNascimento!!)
                    }
                )

                ExposedDropdownMenuBox(
                    expanded = expandidoSerie,
                    onExpandedChange = {
                        expandidoSerie = !expandidoSerie
                    }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .width(175.dp),
                        value = viewModel.serieEscolarSelecionada?.nome?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Série")
                        },
                        placeholder = {
                            Text("Selecione")
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF3277CF),
                            unfocusedBorderColor = Color(0xFF3277CF)
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandidoSerie
                            )
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandidoSerie,
                        onDismissRequest = {
                            expandidoSerie = false
                        },
                        modifier = Modifier
                            .height(300.dp)
                    ) {
                        seriesEscolares.forEach { seriesEscolares ->
                            DropdownMenuItem(
                                text = {
                                    Text(seriesEscolares.nome)
                                },
                                onClick = {
                                    viewModel.onSerieEscolarChange(
                                        seriesEscolares
                                    )
                                    expandidoSerie = false
                                }
                            )
                        }
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.cpf,
                    onValueChange = {
                        viewModel.onCpfChange((it))
                    },
                    visualTransformation = CpfTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    label = {
                        Text("CPF")
                    },
                    placeholder = {
                        Text("000.000.000-00")
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF3277CF),
                        unfocusedBorderColor = Color(0xFF3277CF)
                    ),
                    textStyle = TextStyle(
                        fontSize = 18.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(
                            Font(R.font.instrumentsans_variablefont_wdth_wght)
                        ),
                        fontWeight = FontWeight.SemiBold
                    ),
                    singleLine = true,
                    isError = viewModel.erroCpf != null,
                    supportingText = {
                        if (viewModel.erroCpf != null) Text(text = viewModel.erroCpf!!)
                    }
                )
            }

            //Dropdown -> Diagnóstico
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                ExposedDropdownMenuBox(
                    expanded = expandidoDiagnostico,
                    onExpandedChange = {
                        expandidoDiagnostico = !expandidoDiagnostico
                    }
                ) {

                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = viewModel.diagnosticoSelecionado.joinToString(", ") {it.sigla},
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Diagnóstico")
                        },
                        placeholder = {
                            Text("Selecione")
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF3277CF),
                            unfocusedBorderColor = Color(0xFF3277CF)
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandidoDiagnostico
                            )
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandidoDiagnostico,
                        onDismissRequest = {
                            expandidoDiagnostico = false
                        },
                        modifier = Modifier
                            .height(300.dp)
                    ) {
                        transtornos.forEach { transtornos ->
                            DropdownMenuItem(
                                text = {
                                    Text(transtornos.sigla)
                                },

                                trailingIcon = {
                                    if (viewModel
                                            .diagnosticoSelecionado
                                            .contains(transtornos)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = null,
                                            tint = Color(0xFF3277CF)
                                        )
                                    }
                                },
                                onClick = {
                                    viewModel.onDiagnosticoChange(
                                        transtornos
                                    )
                                    expandidoDiagnostico = false
                                }
                            )
                        }
                    }
                }

                ExposedDropdownMenuBox(
                    expanded = expandidoGrauSuporte,
                    onExpandedChange = {
                        expandidoGrauSuporte = !expandidoGrauSuporte
                    }
                ) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        value = viewModel.grauSuporteSelecionado?.grau?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = {
                            Text("Grau de Suporte")
                        },
                        placeholder = {
                            Text("Selecione")
                        },
                        shape = RoundedCornerShape(16.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF3277CF),
                            unfocusedBorderColor = Color(0xFF3277CF)
                        ),
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expandidoGrauSuporte
                            )
                        }
                    )

                    ExposedDropdownMenu(
                        expanded = expandidoGrauSuporte,
                        onDismissRequest = {
                            expandidoGrauSuporte = false
                        },
                        modifier = Modifier
                            .height(175.dp)
                    ) {
                        grauSuporte.forEach { grausSuporte ->
                            DropdownMenuItem(
                                text = {
                                    Text(grausSuporte.grau)
                                },
                                onClick = {
                                    viewModel.onGrauSuporteChange(
                                        grausSuporte
                                    )
                                    expandidoGrauSuporte = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}