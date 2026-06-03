package com.example.espectra.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.espectra.model.familiar.Diagnostico
import com.example.espectra.model.familiar.GrauSuporte
import com.example.espectra.model.familiar.SerieEscolar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.familiar.Familiar
import com.example.espectra.service.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream

class TelaAdicionarFamiliarViewModel(application: Application): AndroidViewModel(application) {

    private val context = application.applicationContext
    private val espectraService = RetrofitFactory().getEspectraService()

    var estaCarregando by mutableStateOf(false)
        private set

    var mensagemStatus: String? by mutableStateOf(null)
        private set

    var nome by mutableStateOf("")
        private set

    var dataNascimento by mutableStateOf("")
        private set

    var serieEscolarSelecionada by mutableStateOf<SerieEscolar?>(null)
        private set

    var diagnosticoSelecionado by mutableStateOf<List<Diagnostico>>(emptyList())
        private set

    var grauSuporteSelecionado by mutableStateOf<GrauSuporte?>(null)
        private set
    var fotoUri by mutableStateOf<Uri?>(null)
        private set
    var erroNome by mutableStateOf<String?>(null)
        private set

    var erroDataNascimento by mutableStateOf<String?>(null)
        private set

    var erroSerieEscolar by mutableStateOf<String?>(null)
        private set

    var erroDiagnostico by mutableStateOf<String?>(null)
        private set

    var erroGrauSuporte by mutableStateOf<String?>(null)
        private set

    private fun validarNome() {
        erroNome = when {
            nome.isBlank() -> {
                "Nome é obrigatório!"
            }

            nome.length > 150 -> {
                "Nome deve ter no máximo 150 caracteres!"
            }

            !nome.matches(Regex("^[a-zA-ZÀ-ÿ\\s]+\$")) -> {
                "Nome não pode conter números ou caracteres especiais!"
            }

            else -> null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validarDataNascimento() {
        val dataComBarrasParaValidar = buildString {
            for (i in dataNascimento.indices) {
                append(dataNascimento[i])
                if ((i == 1 || i == 3) && i != dataNascimento.lastIndex) {
                    append("/")
                }
            }
        }

        erroDataNascimento = when {
            dataNascimento.isBlank() -> {
                "Data de Nascimento é obrigatória!"
            }

            !dataComBarrasParaValidar.matches(Regex("^\\d{2}/\\d{2}/\\d{4}$")) -> {
                "Use o formato Dia/Mês/Ano"
            }

            else -> {
                try {
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

                    val data = LocalDate.parse(dataComBarrasParaValidar, formatter)

                    if (data.isAfter(LocalDate.now())) {
                        "Data não pode ser futura!"
                    } else {
                        null
                    }
                } catch (e: Exception) {
                    "Data inválida!"
                }
            }
        }
    }

    private fun validarSerieEscolar() {
        erroSerieEscolar = when {
            serieEscolarSelecionada == null -> {
                "Série Escolar é obrigatória!"
            }

            else -> {
                null
            }
        }
    }

    private fun validarDiagnostico() {
        erroDiagnostico = when {
            diagnosticoSelecionado.isEmpty() -> {
                "Diagnóstico é obrigatório!"
            }

            else -> {
                null
            }
        }
    }

    private fun validarGrauSuporte() {
        erroGrauSuporte = when {
            grauSuporteSelecionado == null -> {
                "Grau Suporte é obrigatório!"
            }

            else -> {
                null
            }
        }
    }

    fun onNomeChange(valor: String) {
        nome = valor

        if (erroNome != null){
            erroNome = null
        }
    }

    fun onDataNascimentoChange(valor: String) {
        dataNascimento = valor.filter { it.isDigit() }.take(8)
    }

    fun onSerieEscolarChange(valor: SerieEscolar) {
        serieEscolarSelecionada = valor
    }

    fun onDiagnosticoChange(diagnostico: Diagnostico) {
        diagnosticoSelecionado =
            if (diagnosticoSelecionado.contains(diagnostico)) {
                diagnosticoSelecionado - diagnostico
            } else {
                diagnosticoSelecionado + diagnostico
            }
    }

    fun onGrauSuporteChange(valor: GrauSuporte) {
        grauSuporteSelecionado = valor
    }

    fun onFotoChange(uri: Uri?) {
        fotoUri = uri
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validarDados(): Boolean {
        validarNome()
        validarDataNascimento()
        validarSerieEscolar()
        validarDiagnostico()
        validarGrauSuporte()

        return erroNome == null &&
                erroDataNascimento == null &&
                erroSerieEscolar == null &&
                erroDiagnostico == null &&
                erroGrauSuporte == null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun salvarFamiliar(tokenAutenticacao: String) {
        if (!validarDados()) return

        viewModelScope.launch {
            estaCarregando = true
            mensagemStatus = null

            try {
                val diagnosticoIds = diagnosticoSelecionado.map { it.id }
                val apenasDigitosData = dataNascimento.filter { it.isDigit() }
                val dataInvertidaParaAPI = apenasDigitosData.replace(Regex("(\\d{2})(\\d{2})(\\d{4})"), "$3-$2-$1")

                val novoFamiliar = Familiar(
                    nome = nome,
                    data_nascimento = dataInvertidaParaAPI,
                    id_serie_escolar = serieEscolarSelecionada?.id ?: 0,
                    id_diagnostico = diagnosticoIds,
                    id_grau_suporte = grauSuporteSelecionado?.id ?: 0,
                    foto_url = null
                )

                val fotoPart = withContext(Dispatchers.IO) {
                    fotoUri?.let { uri ->
                        val file = uriToFile(uri)
                        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                        MultipartBody.Part.createFormData("foto", file.name, requestFile)
                    }
                }

                val response = withContext(Dispatchers.IO) {
                    espectraService.adicionarFamiliar(
                        token = tokenAutenticacao,
                        request = novoFamiliar,
                        foto = fotoPart
                    )
                }

                mensagemStatus = "Familiar cadastrado com sucesso!"

            } catch (e: Exception) {
                mensagemStatus = "Erro ao conectar: ${e.localizedMessage}"
            } finally {
                estaCarregando = false
            }
        }
    }

    //Converter Uri em Arquivo
    private fun uriToFile(uri: Uri): File {
        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_foto_familiar.jpg")
        val outputStream = FileOutputStream(file)
        inputStream?.use { input -> outputStream.use { output -> input.copyTo(output) } }
        return file
    }
}