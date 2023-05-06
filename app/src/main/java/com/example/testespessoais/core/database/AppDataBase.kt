package com.example.testespessoais.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testespessoais.core.database.dao.CorDao
import com.example.testespessoais.core.database.entity.Cor

@Database(entities = [Cor::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {

    abstract fun corDao(): CorDao

    companion object{
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            if(instance == null) {
                synchronized(AppDataBase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDataBase::class.java, DATABASE_NAME
                    ).build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "app-coresdatabase.db"
    }
}