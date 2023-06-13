package com.example.testespessoais.collections.di

import com.example.testespessoais.collections.GetCoresUseCase
import com.example.testespessoais.collections.GetCoresUseCaseImpl
import com.example.testespessoais.core.repository.CorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class CollectionsModule {

    @Singleton
    @Binds
    abstract fun providesGetCores(impl: GetCoresUseCaseImpl): GetCoresUseCase
}