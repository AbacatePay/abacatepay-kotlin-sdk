package com.abacatepay.model.response

import kotlinx.serialization.Serializable


@Serializable
data class MRRResponse(
    val mrr: Int,
    val totalActiveSubscriptions: Int,
)
