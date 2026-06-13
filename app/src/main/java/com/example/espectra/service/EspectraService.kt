package com.example.espectra.service

import com.example.espectra.service.tentativa.AtividadeService
import com.example.espectra.service.tentativa.TentativaService
import com.example.espectra.service.PerfilFamiliar.PerfilFamiliarService

interface EspectraService : TentativaService, AtividadeService, PerfilFamiliarService, EspectraApiService