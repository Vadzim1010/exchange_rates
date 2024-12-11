package com.vadzim.yeumushkou.main.root.router

import com.vadzim.yeumushkou.core.presentation.navigation.Router

interface MainRouter : Router {

    fun goCurrencies()
    fun goFavorites()
}