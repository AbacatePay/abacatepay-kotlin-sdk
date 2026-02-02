package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class BillingStatus{
    PENDING,
    EXPIRED,
    CANCELLED,
    PAID,
    REFUNDED
}