package com.example.loginmvvm.ui.viewmodel

import com.example.loginmvvm.domain.GetQuotesUseCase
import com.example.loginmvvm.domain.GetRandomQuoteUseCase
import com.example.loginmvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestWatcher
import org.junit.runner.Description


@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
        }
}

class QuoteViewModelTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetQuotesUseCase

    @RelaxedMockK
    private lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getRandomQuoteUseCase, getQuotesUseCase)
    }

    @Test
    fun randomQuote_getRandomQuoteUseCase_returnAnyQuote() = runBlocking {
        // Give
        val quote = QuoteItem("xd", "yo")
        coEvery { getRandomQuoteUseCase() } returns quote

        // When
        quoteViewModel.randomQuote()
        val quoteValueViewModel = quoteViewModel.quoteModel.value

        // Then
        Assertions.assertEquals(
            quote,
            quoteValueViewModel,
            "Comparation of currentQuote with randomQuote"
        )
    }

    @Test
    fun randomQuote_getRandomQuoteUseCase_returnSameQuote() = runBlocking {
        // Give
        coEvery { getRandomQuoteUseCase() } returns null

        // When
        quoteViewModel.randomQuote()
        val quoteValueViewModel = quoteViewModel.quoteModel.value

        // Then
        Assertions.assertEquals(
            QuoteItem("", ""),
            quoteValueViewModel,
            "Comparation between the quote not change by null"
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun viewModel_init_noQuotesFound() = runTest {
        // Given
        coEvery { getQuotesUseCase() } returns emptyList()

        // When
        quoteViewModel = QuoteViewModel(getRandomQuoteUseCase, getQuotesUseCase)
        advanceUntilIdle()

        // Then
        Assertions.assertEquals(
            QuoteItem(),
            quoteViewModel.quoteModel.value,
            "Comparation quote with value of quoteModel, result expected is null"
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun viewModel_init_returnAListOfQuotes() = runTest {
        // Given
        val quote = QuoteItem("xd", "xd")
        coEvery { getQuotesUseCase() } returns listOf(quote)

        // When
        quoteViewModel = QuoteViewModel(getRandomQuoteUseCase, getQuotesUseCase)
        advanceUntilIdle() // Simplemente avanza el tiempo a un punto en especifico, solo funciona dentro de runTest

        // Then
        Assertions.assertEquals(
            quote,
            quoteViewModel.quoteModel.value,
            "Comparation of quote with quoteModel of the viewModel, result expected is the first element of the returned list"
        )
    }

}