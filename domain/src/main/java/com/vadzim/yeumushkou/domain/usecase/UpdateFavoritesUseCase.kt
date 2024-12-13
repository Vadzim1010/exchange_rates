package com.vadzim.yeumushkou.domain.usecase

import com.vadzim.yeumushkou.domain.repository.api.ExchangeRatesLocalRepository

class UpdateFavoritesUseCase(
    private val localRepository: ExchangeRatesLocalRepository,
) {

    suspend operator fun invoke(
        baseCurrency: String,
        relatedCurrency: String,
        isFavorite: Boolean,
    ) {
        val id = baseCurrency + relatedCurrency

        if (isFavorite) {
            localRepository.deleteExchangeRate(id)
        } else {
            localRepository.insertExchangeRate(
                id = id,
                baseCurrency = baseCurrency,
                relatedCurrency = relatedCurrency,
            )
        }
    }

}