package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class PaymentFrequency{
    ONE_TIME,
    MULTIPLE_PAYMENTS,
}