package com.example.loginmvvm.domain

import com.example.loginmvvm.data.QuoteRepository
import com.example.loginmvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

class GetRandomQuoteUseCaseTest {

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun getRandomQuoteUseCase_GetAllQuotesFromApi_ReturnsEmptyList() =
        runBlocking {
            // Give
            coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()
            // When
            val result = getRandomQuoteUseCase()
            // Then
            Assertions.assertNull(result, "Result is null for get all quotes of Database")
        }

    @Test
    fun getRandomQuoteUseCase_GetAllQuotesFromApi_ReturnListOfQuotes() =
        runBlocking {
            // Give
                var quote = QuoteItem("xd", "yo")
                coEvery { quoteRepository.getAllQuotesFromDatabase() } returns listOf(quote)
            // When
                val result = getRandomQuoteUseCase()
            // Then
            Assertions.assertEquals(quote, result, "Expected a quote (random quote)")

        }

}