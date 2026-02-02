package com.abacatepay.model.enums

import kotlinx.serialization.Serializable

@Serializable
enum class TransactionKind {
    PAYMENT,
    WITHDRAW,
}