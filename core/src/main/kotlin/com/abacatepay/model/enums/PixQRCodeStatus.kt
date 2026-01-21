package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class PixQRCodeStatus{
    PENDING,
    EXPIRED,
    CANCELLED,
    PAID,
    REFUNDED
}