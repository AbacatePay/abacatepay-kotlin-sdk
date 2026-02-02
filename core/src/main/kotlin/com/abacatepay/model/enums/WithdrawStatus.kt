package com.abacatepay.model.enums

import kotlinx.serialization.Serializable


@Serializable
enum class WithdrawStatus {
    PENDING,
    EXPIRED,
    CANCELLED,
    COMPLETE,
    REFUNDED
}