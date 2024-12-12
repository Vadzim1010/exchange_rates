package com.vadzim.yeumushkou.data.bd.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_database")
data class CurrencyEntity(
    @PrimaryKey
    val id: String,
    val baseCurrency: String,
    val relatedCurrency: String,
)