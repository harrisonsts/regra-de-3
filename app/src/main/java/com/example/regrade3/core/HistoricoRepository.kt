package com.example.regrade3.core

import com.example.regrade3.HistoricoItem

interface HistoricoRepository {

    fun fetchHistorico(): List<HistoricoItem>

    fun addHistorico(numberA: String, numberB: String, numberC: String, numberX: String, id: String)
}