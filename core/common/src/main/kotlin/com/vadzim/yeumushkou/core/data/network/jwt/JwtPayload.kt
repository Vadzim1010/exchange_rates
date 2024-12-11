package com.vadzim.yeumushkou.core.data.network.jwt

import com.google.gson.annotations.SerializedName

data class JwtPayload(

    @SerializedName("preferred_username")
    val username: String,
)