package com.example.loginmvvm.core.di

import android.content.Context
import androidx.room.Room
import com.example.loginmvvm.data.database.QuoteDatabase
import com.example.loginmvvm.data.database.dao.QuoteDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val ROOM_QUOTE_DATABASE_NAME = "quote_db"

    // @AplicationContext la provee room
    @Singleton
    @Provides
    fun providesRoomDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QuoteDatabase::class.java, ROOM_QUOTE_DATABASE_NAME)
            .fallbackToDestructiveMigration() // Cuando se cambie la version, ocurre una migración destructiva(se pierden los datos)
            .build()

    @Singleton
    @Provides
    fun provideQuoteDao(quoteDatabase: QuoteDatabase): QuoteDao = quoteDatabase.getQuoteDao()

}