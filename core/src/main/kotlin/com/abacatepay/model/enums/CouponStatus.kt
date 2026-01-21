package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class CouponStatus {
    ACTIVE,
    INACTIVE,
    EXPIRED,
}