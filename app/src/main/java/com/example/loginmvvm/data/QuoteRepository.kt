package com.example.loginmvvm.data

import android.util.Log
import com.example.loginmvvm.data.database.dao.QuoteDao
import com.example.loginmvvm.data.database.entities.QuoteEntity
import com.example.loginmvvm.data.database.entities.toQuoteEntity
import com.example.loginmvvm.data.model.QuoteModel
import com.example.loginmvvm.data.model.QuoteProvider
import com.example.loginmvvm.data.network.QuoteServices
import com.example.loginmvvm.domain.model.QuoteItem
import com.example.loginmvvm.domain.model.toQuoteItem
import javax.inject.Inject


private const val TAG: String = "QuoteRepository"

// Clase a la que se llama para devolver las citas
// Lo que devuelve o acepta, siempre son modelos 'Item' porque vienen de la capa de dominio
class QuoteRepository @Inject constructor(
    private val api: QuoteServices,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<QuoteItem> {
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toQuoteItem() }
    }

    suspend fun getAllQuotesFromDatabase(): List<QuoteItem> {
        val quotesDb: List<QuoteEntity> = quoteDao.getAllQuotes()
        return quotesDb.map { it.toQuoteItem() }
    }

    suspend fun insertAllQuotes(quotes: List<QuoteItem>) =
        quoteDao.insertAllQuotes(quotes.map { it.toQuoteEntity() })


    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }

}