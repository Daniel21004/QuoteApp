package com.example.loginmvvm.data.network

import android.util.Log
import com.example.loginmvvm.core.RetrofitHelper
import com.example.loginmvvm.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class QuoteServices @Inject constructor(
    private val quoteApiClient: QuoteApiClient
){

    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            try {
                val response = quoteApiClient.getAllQuotes()
                response.body() ?: emptyList() // Return implicito
            } catch (e: Exception) {
                Log.e("QuoteServices", "$e")
                emptyList()
            }
        }
    }
}