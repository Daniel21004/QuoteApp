package com.example.loginmvvm.data.network

import com.example.loginmvvm.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

// Donde se recuperan los datos
interface QuoteApiClient {

    @GET("/.json") // Base url indicada en la configuración de Retrofit
    suspend fun getAllQuotes(): Response<List<QuoteModel>>

}
