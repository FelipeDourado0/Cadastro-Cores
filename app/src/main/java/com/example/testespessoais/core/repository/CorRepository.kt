package com.example.testespessoais.core.repository

import com.example.testespessoais.collections.CorItem

interface CorRepository {
    suspend fun getListaCores(): MutableList<CorItem>

    suspend fun getRandomColor() : CorItem

    suspend fun postNewColor(corItem: CorItem)
}