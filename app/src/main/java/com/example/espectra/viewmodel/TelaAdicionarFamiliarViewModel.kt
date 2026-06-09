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
import android.util.Log.e
import androidx.lifecycle.viewModelScope
import com.example.espectra.model.familiar.Familiar
import com.example.espectra.service.RetrofitFactory
import com.example.espectra.storage.GerenciarSessao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream

class TelaAdicionarFamiliarViewModel(application: Application): AndroidViewModel(application) {

    var cadastroSucesso by mutableStateOf(false)
        private set

    private val context = application.applicationContext

    private val espectraService by lazy { RetrofitFactory().getEspectraService() }

    private val gerenciarSessao = GerenciarSessao(context)

    var estaCarregando by mutableStateOf(false)
        private set

    var mensagemStatus: String? by mutableStateOf(null)
        private set

    var nome by mutableStateOf("")
        private set

    var dataNascimento by mutableStateOf("")
        private set

    var cpf by mutableStateOf("")
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

    var erroCpf by mutableStateOf<String?>(null)
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

    private fun isCpfValido(cpf: String): Boolean {
        val numeros = cpf.filter { it.isDigit() }

        if (numeros.length != 11 || numeros.all { it == numeros[0] }) return false

        var soma = 0
        for (i in 0 until 9) {
            soma += (numeros[i].toString().toInt()) * (10 - i)
        }
        var resto = soma % 11
        val digito1 = if (resto < 2) 0 else 11 - resto
        if (numeros[9].toString().toInt() != digito1) return false

        soma = 0
        for (i in 0 until 10) {
            soma += (numeros[i].toString().toInt()) * (11 - i)
        }
        resto = soma % 11
        val digito2 = if (resto < 2) 0 else 11 - resto
        if (numeros[10].toString().toInt() != digito2) return false

        return true
    }

    private fun validarCpf(){
        erroCpf = when {
            cpf.isBlank() -> {
                "CPF é obrigatório!"
            }

            !isCpfValido(cpf) -> {
                "CPF inválido! Verifique os números"
            }

            else -> null
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

        if (erroDataNascimento != null) {
            erroDataNascimento = null
        }
    }

    fun onSerieEscolarChange(valor: SerieEscolar) {
        serieEscolarSelecionada = valor
        erroSerieEscolar = null
    }

    fun onDiagnosticoChange(diagnostico: Diagnostico) {
        diagnosticoSelecionado =
            if (diagnosticoSelecionado.contains(diagnostico)) {
                diagnosticoSelecionado - diagnostico
            } else {
                diagnosticoSelecionado + diagnostico
            }

        erroDiagnostico = null
    }

    fun onGrauSuporteChange(valor: GrauSuporte) {
        grauSuporteSelecionado = valor
        erroGrauSuporte = null
    }

    fun onFotoChange(uri: Uri?) {
        fotoUri = uri
    }

    fun onCpfChange(valor: String){
        cpf = valor.filter { it.isDigit() }.take(11)

        if (erroCpf != null) {
            erroCpf = null
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun validarDados(): Boolean {
        validarNome()
        validarDataNascimento()
        validarCpf()
        validarSerieEscolar()
        validarDiagnostico()
        validarGrauSuporte()

        return erroNome == null &&
                erroDataNascimento == null &&
                erroCpf == null &&
                erroSerieEscolar == null &&
                erroDiagnostico == null &&
                erroGrauSuporte == null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun salvarFamiliar() {
        viewModelScope.launch {
            estaCarregando = true
            mensagemStatus = null

            try {

                val tokenReal = gerenciarSessao.buscarToken()
                val idUsuarioReal = gerenciarSessao.buscarIdUsuario()

                if (tokenReal.isNullOrBlank() || idUsuarioReal == 0) {
                    mensagemStatus = "Sessão expirada. Por favor, faça login novamente."
                    estaCarregando = false
                    return@launch
                }

                val apenasDigitosData = dataNascimento.filter { it.isDigit() }
                val dataInvertidaParaAPI = apenasDigitosData.replace(Regex("(\\d{2})(\\d{2})(\\d{4})"), "$3-$2-$1")

                val tokenHeader = "Bearer $tokenReal"

                val nomeBody = nome.toRequestBody("text/plain".toMediaTypeOrNull())
                val dataBody = dataInvertidaParaAPI.toRequestBody("text/plain".toMediaTypeOrNull())
                val serieBody = (serieEscolarSelecionada?.id ?: 0).toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val grauBody = (grauSuporteSelecionado?.id ?: 0).toString().toRequestBody("text/plain".toMediaTypeOrNull())
                val diagnosticoBody = com.google.gson.Gson().toJson(diagnosticoSelecionado.map { it.id })
                    .toRequestBody("text/plain".toMediaTypeOrNull())
                val cpfBody = cpf.toRequestBody("text/plain".toMediaTypeOrNull())

                val responsavelBody = idUsuarioReal.toString().toRequestBody("text/plain".toMediaTypeOrNull())

                val fotoPart = withContext(Dispatchers.IO) {
                    fotoUri?.let { uri ->
                        val file = uriToFile(uri)
                        val tipoMime = context.contentResolver.getType(uri) ?: "image/jpeg"
                        val requestFile = file.asRequestBody(tipoMime.toMediaTypeOrNull())
                        MultipartBody.Part.createFormData("foto", file.name, requestFile)
                    }
                }

                val response = withContext(Dispatchers.IO) {
                    espectraService.adicionarFamiliar(
                        token = tokenHeader,
                        nome = nomeBody,
                        dataNascimento = dataBody,
                        idSerieEscolar = serieBody,
                        idGrauSuporte = grauBody,
                        diagnostico = diagnosticoBody,
                        cpf = cpfBody,
                        idResponsavel = responsavelBody,
                        foto = fotoPart
                    )
                }

                mensagemStatus = "Familiar cadastrado com sucesso!"
                cadastroSucesso = true

            } catch (e: Exception) {
                android.util.Log.e("API_ESPECTRA", "Erro ao salvar", e)
                mensagemStatus = "Erro ao conectar: ${e.localizedMessage}"
                cadastroSucesso = false
            } finally {
                estaCarregando = false
            }
        }
    }

    //Converter Uri em Arquivo
    private fun uriToFile(uri: Uri): File {
        val contentResolver = context.contentResolver
        val tipoMime = contentResolver.getType(uri)
        val extensao = when (tipoMime){
            "image/png" -> "png"
            "image/webp" -> "webp"
            else -> "jpeg"
        }

        val inputStream = context.contentResolver.openInputStream(uri)
        val file = File(context.cacheDir, "temp_foto_familiar.$extensao")
        val outputStream = FileOutputStream(file)
        inputStream?.use { input -> outputStream.use { output -> input.copyTo(output) } }
        return file
    }
}