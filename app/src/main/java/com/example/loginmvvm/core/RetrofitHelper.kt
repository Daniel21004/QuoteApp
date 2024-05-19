package com.example.loginmvvm.core

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    // Ya no se usa por la inyección de dependencias
    fun getRetrofit(): Retrofit { // Configuración de Retrofit
        return Retrofit.Builder()
            .baseUrl("https://drawsomething-59328-default-rtdb.europe-west1.firebasedatabase.app/") // Url base
            .addConverterFactory(GsonConverterFactory.create()) // Serialization
            .client(OkHttpClient.Builder().build())// Cliente
            .build() // Construcción
    }

}