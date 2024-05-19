package com.example.loginmvvm.ui.view

import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.loginmvvm.ui.viewmodel.QuoteViewModel

@Composable
fun QuoteScreen(
    modifier: Modifier = Modifier,
    quoteViewModel: QuoteViewModel = viewModel()
) {
    val quoteModel by quoteViewModel.quoteModel.collectAsState()
    val isLoading by quoteViewModel.isLoading.collectAsState() // Trae un observable dentro de si, y refresca los componentes

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Gray)
            .padding(horizontal = 16.dp, vertical = 0.dp)
            .clickable(
                // Para deshabilitar el toque de pantalla
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { quoteViewModel.randomQuote() }
            )
    ) {
        if (isLoading) {
            LoadingComponent()
        }
        QuoteText(quote = quoteModel.quote)
        AuthorText(author = quoteModel.author)
    }
}

@Composable
fun QuoteText(quote: String, modifier: Modifier = Modifier) {
    Text(
        text = quote,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun AuthorText(author: String, modifier: Modifier = Modifier) {
    Text(
        text = author,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Italic,
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun LoadingComponent(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = Modifier.width(64.dp),
        color = MaterialTheme.colorScheme.secondary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview
@Composable
fun QuoteScreenPreview() {
    QuoteScreen()
}