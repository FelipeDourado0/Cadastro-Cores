package com.example.testespessoais.core.repository

import com.example.testespessoais.collections.CorItem
import com.example.testespessoais.core.database.AppDataBase
import com.example.testespessoais.core.database.entity.Cor
import java.util.*

class CorRepositoryImpl(appDataBase: AppDataBase) : CorRepository {
    private val dao = appDataBase.corDao()

    override suspend fun getListaCores(): MutableList<CorItem> {
        val listaCores = dao.getAllCores().map {
            CorItem(
                idCor = it.uuid.toString(),
                hexCor = it.hexCor,
                nomeCor = it.nomeCor
            )
        }.toMutableList()
        return listaCores
    }

    override suspend fun getRandomColor(): CorItem {
        val corLista = dao.getAllCores()
        val cor = corLista.random()
        val corItem = CorItem(
            nomeCor = cor.nomeCor,
            hexCor = cor.hexCor,
            idCor = cor.uuid.toString()
        )
        return corItem
    }

    override suspend fun postNewColor(corItem: CorItem) {
        val cor = Cor(
            hexCor = corItem.hexCor,
            nomeCor = corItem.nomeCor,
            uuid = UUID.randomUUID()
        )
        dao.insert(cor)
    }
}