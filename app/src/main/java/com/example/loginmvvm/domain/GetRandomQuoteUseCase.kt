package com.example.loginmvvm.domain

import com.example.loginmvvm.data.QuoteRepository
import com.example.loginmvvm.domain.model.QuoteItem
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteRepository: QuoteRepository) {

    // Por el momento se hace así, pero se debe acceder a bd en una corrutina
    suspend operator fun invoke(): QuoteItem? {
        val quotes = quoteRepository.getAllQuotesFromDatabase()

        if (quotes.isNotEmpty()) {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }

        return null // Se puede devolver una cita por defecto
    }
}