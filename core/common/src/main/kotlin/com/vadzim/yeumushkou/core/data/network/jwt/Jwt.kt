package com.vadzim.yeumushkou.core.data.network.jwt

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser

class Jwt(private val token: String) {

    private val userData: JsonObject by lazy {
        val userData = String(Base64.decode(token.split(".")[1], Base64.DEFAULT))
        JsonParser.parseString(userData).asJsonObject
    }

    fun getUserData(): JwtPayload {
        return Gson().fromJson(userData, JwtPayload::class.java)
    }
}