package com.example.regrade3

import com.example.regrade3.core.HistoricoRepository
import java.util.UUID

object Historico : HistoricoRepository {

    private val historicoItemList: MutableList<HistoricoItem> = mutableListOf()

    override fun fetchHistorico() = historicoItemList.map { it.copy() }

    override fun addHistorico(numberA: String, numberB: String, numberC: String, numberX: String, id: String) {
        historicoItemList.add(
            HistoricoItem(
                id = UUID.randomUUID().toString(),
                numberA = numberA,
                numberB = numberB,
                numberC = numberC,
                numberX = numberX
            )
        )
    }
}