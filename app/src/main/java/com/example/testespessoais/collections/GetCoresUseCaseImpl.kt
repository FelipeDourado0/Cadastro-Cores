package com.example.testespessoais.collections

import com.example.testespessoais.core.repository.CorRepository

class GetCoresUseCaseImpl (
    private val repositoryCor: CorRepository
): GetCoresUseCase{
    override suspend fun invoke(): List<CorItem> {
        return repositoryCor.getListaCores()
    }
}