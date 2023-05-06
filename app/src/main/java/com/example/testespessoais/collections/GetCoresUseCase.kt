package com.example.testespessoais.collections

interface GetCoresUseCase {
    suspend fun invoke() : List<CorItem>
}