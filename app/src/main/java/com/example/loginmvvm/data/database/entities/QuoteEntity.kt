package com.example.loginmvvm.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.loginmvvm.domain.model.QuoteItem

/// Entidad solo para la base de datos
@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") val id : Int = 0,
    @ColumnInfo(name = "quote") val quote: String,
    @ColumnInfo(name = "author") val author: String
)

fun QuoteItem.toQuoteEntity() = QuoteEntity(quote = quote, author = author)