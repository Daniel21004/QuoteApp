package com.example.loginmvvm.core.di

import com.example.loginmvvm.data.network.QuoteApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Alcance a nivel de aplicación
object NetworkModule {

    @Provides // Indica que es un método que provee
    @Singleton // Crea una sola instancia
    // La conveción es 'provide' + lo qué provee
    fun provideRetrofit(): Retrofit { // Importante indicar qué provee
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/") // Url base
            .addConverterFactory(GsonConverterFactory.create()) // Serialization
            .client(OkHttpClient.Builder().build())// Cliente
            .build() // Construcción
    }

    // Es una interfaz, por lo tanto, se la inyecta de esta manera
    @Provides
    @Singleton
    fun provideQuoteApiClient(retrofit: Retrofit): QuoteApiClient {
        return retrofit.create(QuoteApiClient::class.java)
    }
}