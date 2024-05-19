package com.example.loginmvvm


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.loginmvvm.ui.view.QuoteScreen
import com.example.loginmvvm.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inyección del viewModel en la activity
        val viewModel by viewModels<QuoteViewModel>()

        setContent {
            QuoteScreen(quoteViewModel = viewModel)
//            QuoteScreen()
        }
    }
}

