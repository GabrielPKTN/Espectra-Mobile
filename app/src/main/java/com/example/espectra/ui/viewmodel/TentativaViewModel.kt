package com.example.espectra.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.espectra.model.tentativa.Tentativa
import com.example.espectra.service.RetrofitFactory
import java.text.SimpleDateFormat
import java.util.Locale

class TentativaViewModel : ViewModel() {

    private var _tentativas by mutableStateOf<List<Tentativa>>(emptyList())
    val tentativas get() = _tentativas

    private var _datas by mutableStateOf<List<String>>(emptyList())
    val datas get() = _datas

    private var _listIndependente by mutableStateOf<List<Int>>(emptyList())
    val listIndependente get() = _listIndependente

    private var _listParcial by mutableStateOf<List<Int>>(emptyList())
    val listParcial get() = _listParcial

    private var _listTotal by mutableStateOf<List<Int>>(emptyList())
    val listTotal get() = _listTotal

    private var _listErro by mutableStateOf<List<Int>>(emptyList())
    val listErro get() = _listErro

    suspend fun buscarDadosTentativas(idAtividade: Int, token: String) {

        try {

            val result = RetrofitFactory()
                .getEspectraService()
                .getTentativasByIdAtividade(

                    token = token,
                    idTentativa = idAtividade

                )

            _tentativas = result.items

            var datasNonFormatted = _tentativas.map {

                var dataSplit = it.data_tentativa.split("-").reversed()

                "${dataSplit[0]}/${dataSplit[1]}/${dataSplit[2]}"

            }

            _datas = datasNonFormatted.distinct()


            var qtdIndependente = 0
            var qtdParcial = 0
            var qtdTotal = 0
            var qtdErro = 0


            for(tentativa in _tentativas) {

                if(tentativa.resultado == 0) {

                    qtdErro++

                } else if(tentativa.auxilio == "Independente" && tentativa.resultado == 1) {

                    qtdIndependente++

                } else if(tentativa.auxilio == "Auxilio parcial" && tentativa.resultado == 1) {

                    qtdParcial++

                } else if(tentativa.auxilio == "Auxilio total" && tentativa.resultado == 1) {

                    qtdTotal++

                }

            }

            var independenteNonFormatted = (0..qtdIndependente).toMutableList()
            var parcialNonFormatted      = (0..qtdParcial).toMutableList()
            var totalNonFormatted        = (0..qtdTotal).toMutableList()
            var erroNonFormatted         = (0..qtdErro).toMutableList()

            if(independenteNonFormatted.size < datas.size && independenteNonFormatted.isNotEmpty()) {

                while (independenteNonFormatted.size < datas.size) {
                    independenteNonFormatted.add(independenteNonFormatted[independenteNonFormatted.lastIndex])
                }

            }

            if(parcialNonFormatted.size < datas.size && parcialNonFormatted.isNotEmpty()) {

                while (parcialNonFormatted.size < datas.size) {
                    parcialNonFormatted.add(parcialNonFormatted[parcialNonFormatted.lastIndex])
                }

            }

            if(totalNonFormatted.size < datas.size && totalNonFormatted.isNotEmpty()) {

                while (totalNonFormatted.size < datas.size) {
                    totalNonFormatted.add(totalNonFormatted[totalNonFormatted.lastIndex])
                }

            }

            if(erroNonFormatted.size < datas.size && erroNonFormatted[erroNonFormatted.lastIndex] != 0 && erroNonFormatted.isNotEmpty()) {

                while (erroNonFormatted.size < datas.size && erroNonFormatted[erroNonFormatted.lastIndex] != 0) {
                    erroNonFormatted.add(erroNonFormatted[erroNonFormatted.lastIndex] - 1)
                }

            }

            _listIndependente = independenteNonFormatted
            _listParcial = parcialNonFormatted
            _listTotal = totalNonFormatted
            _listErro = erroNonFormatted

        } catch (error: Exception) {

            _tentativas = emptyList()

        }

    }

}