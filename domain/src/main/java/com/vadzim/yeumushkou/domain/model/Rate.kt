package com.vadzim.yeumushkou.domain.model

data class Rate(
    val currency: Currency,
    val value: Double,
    val isFavorite: Boolean,
)