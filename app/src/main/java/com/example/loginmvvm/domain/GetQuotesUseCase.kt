package com.example.loginmvvm.domain

import android.util.Log
import com.example.loginmvvm.core.log.Logger
import com.example.loginmvvm.data.QuoteRepository
import com.example.loginmvvm.domain.model.QuoteItem
import javax.inject.Inject

private const val TAG: String = "GetQuotesUseCase"

// Los casos de uso realizan UNA sola cosa
class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository,
    private val logger : Logger
) {

    suspend operator fun invoke(): List<QuoteItem> {
        val response = repository.getAllQuotesFromApi()

        return if (response.isNotEmpty()) {
                repository.clearQuotes() // Para que se limpie la bd antes de insertar
                repository.insertAllQuotes(response)
                response
        } else {
            logger.d(TAG, "Devuelve from db")
//            Log.d(TAG, "Devuelve from db")
            repository.getAllQuotesFromDatabase()
        }

    } // Es llamada automáticamente cuando ponemos GetQuotesUseCase()
}