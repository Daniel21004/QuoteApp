package com.example.loginmvvm.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginmvvm.data.model.QuoteModel
import com.example.loginmvvm.domain.GetQuotesUseCase
import com.example.loginmvvm.domain.GetRandomQuoteUseCase
import com.example.loginmvvm.domain.model.QuoteItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getRandomQuoteUseCase: GetRandomQuoteUseCase,
    private val getQuotesUseCase: GetQuotesUseCase
) : ViewModel() {
    // Instancia del modelo
    private val _quoteModel = MutableStateFlow(QuoteItem())

    // Construcción de la variable de solo lectura para acceso desde fuera de la clase
    val quoteModel: StateFlow<QuoteItem> = _quoteModel.asStateFlow()

    // Variable para controlar el loading
    var isLoading = MutableStateFlow(false)

    // Funciones
    fun randomQuote() {
        viewModelScope.launch {
            isLoading.value = true
            val quote = getRandomQuoteUseCase()
            if (quote != null) {
                _quoteModel.value = quote
            }
            isLoading.value = false
        }
    }

    init {
        viewModelScope.launch {
            isLoading.value = true
            // Maneja las corrutinas automáticamente, especial para viewModel
            val result =
                getQuotesUseCase() // Porque se esta invocando a la función invoke con el constructor. Ojo con ()

            if (result.isNotEmpty()) {
                _quoteModel.value = result[0]
                isLoading.value = false
            }
        }
    }


}