package com.example.testespessoais.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.testespessoais.core.database.entity.Cor
//10:19 - aonde parei
//IMPLEMENTAR PADRAO USECASE + REPOSITORY PRIMEIRO
//O ROOM GERA UMA CLASSE A PARTIR DESSA INTERFACE.
@Dao
interface CorDao {

    @Insert
    suspend fun insert(cor: Cor)

    @Query("SELECT * FROM Cor")
    suspend fun getAllCores(): List<Cor>

    @Query("SELECT * FROM Cor WHERE nomeCor == :corNome")
    suspend fun getCorPorNome(corNome: String): Cor

    @Query("DELETE FROM Cor WHERE nomeCor == :corNome")
    suspend fun deleteCor(corNome: String)

    @Query("DELETE FROM Cor")
    suspend fun deleteAllCor()

    @Query("SELECT * FROM Cor " +
            "ORDER BY random() " +
            "LIMIT 1"
    )
    suspend fun getCorRandom(): Cor
}