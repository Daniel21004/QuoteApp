package com.example.loginmvvm.domain

import com.example.loginmvvm.core.log.Logger
import com.example.loginmvvm.data.QuoteRepository
import com.example.loginmvvm.domain.model.QuoteItem
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions

class GetQuotesUseCaseTest {

    @RelaxedMockK  // Si la clase accede a algo no preparado. Retorna un valor por defecto. Más Fácil
//    @MockK // Si la clase accede a algo no preparado. Falla. Más recomendable
    private lateinit var quoteRepository: QuoteRepository // Variable que se va a 'falsear'(mockear)

    @RelaxedMockK // Aqui se usa esta anotación para que administre el Log, porque me da pereze administralo en el every
    private lateinit var logger: Logger

    private lateinit var getQuotesUseCase: GetQuotesUseCase // Declaración real del caso de uso


    @Before
    fun onBefore() { // Este método se ejecuta por cada test
        MockKAnnotations.init(this) // Se instancia mock
        getQuotesUseCase =
            GetQuotesUseCase(
                quoteRepository,
                logger
            ) // Se instancia el caso de uso, y se inyecta el repository 'falseado'
    }

    @Test
    // Las comillas inversas permiten poner nombres con espacios
    fun getQuotesUseCase_GetAllQuotesFromApi_ReturnsEmptyList() =
        runBlocking { // Porque se va a ejecutar corutinas
            // Given
            // returns con 's' porque no es un return de verdad, sino uno mockeado
            // coEvery - cada vez que.. se llame . 'co' porque es una corrutina
            // Con la etiqueta MockK, si se llama a una función del repository que no se encuentra mockeada aquí, fallaría, con RelaxedMockK, no.
            coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

            // When - Cuándo debe de suceder que el quoteRepository llame a getAllQuotesFromApi
            getQuotesUseCase()

            // Then - Qué debe de pasar cuando pase esto
            // coVerify, porque se esta llamando a corrutinas.
            // exactly, para indicar que se llama exactamente una sola vez
            coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
            // Se verifica que los siguientes métodos no se llamen, porque no pertenecen a ese flujo
            coVerify(exactly = 0) { quoteRepository.clearQuotes() }
            coVerify(exactly = 0) { quoteRepository.insertAllQuotes(any()) } // any() para omitar el parametro
        }

    @Test
    fun getQuotesUseCase_GetAllQuotesFromApi_ReturnsListOfQuotes() = runBlocking {

        val quoteCurrent = QuoteItem(
            "más vale pajaro en mano",
            "yoxd"
        )
        // Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns listOf(
            quoteCurrent
        )

        // When
        val quotes = getQuotesUseCase() // Devuelve una lista

        // Then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertAllQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }

        Assertions.assertEquals(quotes.first(), quoteCurrent, "Comparación de la quote")
    }
}