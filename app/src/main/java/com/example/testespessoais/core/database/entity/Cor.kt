package com.example.testespessoais.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "cor")
data class Cor (
    @PrimaryKey val uuid: UUID,
    @ColumnInfo(name = "nomeCor") val nomeCor: String,
    @ColumnInfo(name = "HexCor") val hexCor: String
)