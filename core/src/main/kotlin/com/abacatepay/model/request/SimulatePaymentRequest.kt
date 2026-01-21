package com.abacatepay.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SimulatePaymentRequest(
    val metadata: Map<String, String> = emptyMap(),
)
