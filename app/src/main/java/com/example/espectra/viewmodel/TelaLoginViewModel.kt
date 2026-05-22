package com.example.espectra.viewmodel

import android.telecom.Call
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TelaLoginViewModel : ViewModel() {

    //Estados que a tela Compose ira observar

    var login by mutableStateOf("")
        private set
    var senha by mutableStateOf("")
        private set
    var emailError by mutableStateOf<String?>(null)
        private set

    var passwordError by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set




}