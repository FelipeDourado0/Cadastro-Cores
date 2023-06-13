package com.example.testespessoais.collections

import com.example.testespessoais.core.repository.CorRepository
import javax.inject.Inject

class GetCoresUseCaseImpl @Inject constructor (
    private val repositoryCor: CorRepository
): GetCoresUseCase{
    override suspend fun invoke(): List<CorItem> {
        return repositoryCor.getListaCores()
    }
}