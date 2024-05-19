package com.example.loginmvvm.domain.model

import com.example.loginmvvm.data.database.entities.QuoteEntity
import com.example.loginmvvm.data.model.QuoteModel

// Modelo que va a ser utilizado por la capa de dominio y UI
data class QuoteItem(val quote: String = "", val author: String = "")

// Mappers
fun QuoteModel.toQuoteItem() = QuoteItem(quote, author)
fun QuoteEntity.toQuoteItem() = QuoteItem(quote, author)