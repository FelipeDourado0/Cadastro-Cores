package com.example.testespessoais.di

import android.app.Application
import com.example.testespessoais.core.database.AppDataBase
import com.example.testespessoais.core.database.dao.CorDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
//Pode-se usar como 'class', mas por questão de performance, utilizaremos object
object RoomModule {

    @Singleton
    @Provides
    fun providesRoomDatabase(application: Application): AppDataBase{
        return AppDataBase.getInstance(application)
    }

    @Singleton
    @Provides
    fun provideCorDao(dataBase: AppDataBase): CorDao{
        return dataBase.corDao()
    }
}