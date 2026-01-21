package com.abacatepay.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AbacatePayResponse<T>(
    val data: T? = null,
    val error: String? = null
)
