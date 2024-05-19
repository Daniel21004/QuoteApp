package com.example.loginmvvm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginmvvm.data.database.dao.QuoteDao
import com.example.loginmvvm.data.database.entities.QuoteEntity

// clase abstracta porque no se va a implementar nada
@Database(
    entities = [QuoteEntity::class],
    version = 2,
    exportSchema = false
) // Importante la versión para la actualización de la bd
abstract class QuoteDatabase : RoomDatabase() {

    abstract fun getQuoteDao(): QuoteDao
}