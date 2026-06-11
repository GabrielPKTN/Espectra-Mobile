package com.example.espectra.service

import com.example.espectra.service.familiar.FamiliarService
import com.example.espectra.service.tentativa.AtividadeService
import com.example.espectra.service.tentativa.TentativaService
import com.example.espectra.service.PerfilFamiliar.PerfilFamiliarService
import com.example.espectra.service.editarFamiliar.DiagnosticoService
import com.example.espectra.service.editarFamiliar.EditarFamiliarService


interface EspectraService : TentativaService, AtividadeService, PerfilFamiliarService, FamiliarService, EditarFamiliarService, DiagnosticoService
