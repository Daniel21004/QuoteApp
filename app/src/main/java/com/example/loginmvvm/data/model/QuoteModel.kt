package com.example.loginmvvm.data.model

import com.google.gson.annotations.SerializedName

// Modelo de respuesta del Back. Nombre de recepción y al lado, nombre de la variabe
data class QuoteModel(
    @SerializedName("quote") val quote: String = "",
    @SerializedName("author") val author: String = ""
)
