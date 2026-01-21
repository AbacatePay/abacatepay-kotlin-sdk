package com.abacatepay.model.request

import com.abacatepay.model.enums.PixKeyType
import kotlinx.serialization.Serializable

@Serializable
data class PixRequest(
    val type: PixKeyType,
    val key: String,
)