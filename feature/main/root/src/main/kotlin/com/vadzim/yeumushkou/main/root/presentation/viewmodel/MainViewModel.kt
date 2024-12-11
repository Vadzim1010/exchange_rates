package com.vadzim.yeumushkou.main.root.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.vadzim.yeumushkou.main.root.router.MainRouter
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val router: MainRouter,
) : ViewModel() {

    fun goFavorite() {
        router.goFavorites()
    }

    fun goCurrencies() {
        router.goCurrencies()
    }
}