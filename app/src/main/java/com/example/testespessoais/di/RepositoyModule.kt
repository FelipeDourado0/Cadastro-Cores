package com.example.testespessoais.di

import com.example.testespessoais.core.repository.CorRepository
import com.example.testespessoais.core.repository.CorRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoyModule {

    @Singleton
    @Binds
    abstract fun providesCorRepository(impl: CorRepositoryImpl): CorRepository
}